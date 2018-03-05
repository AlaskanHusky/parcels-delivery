package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.service.PointService;

/**
 * Класс для отправки посылок на другие узлы, а также обновлении статуса при доставке посылки.
 */
public class ParcelSender
{
	private HttpRequester httpRequester;
	private ParcelService parcelService;
	private PointService pointService;
	private PointUtil pointUtil;

	public ParcelSender()
	{
		System.out.println("ParcelSender Constructor");
	}

	/**
	 * Определяет следующее действие с посылкой. Если узел последний, то вызывает функцию обратного вызова на изменения статуса в
	 * предыдущих узлах пути. Если узел не последний, то отправляет посылку на следующий узел.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}.
	 */
	public void handleParcel(ParcelEntity parcelEntity)
	{
		if (pointUtil.isLast(parcelEntity.getPath()))
		{
			updateStatusCallback(parcelEntity);
		}
		else
		{
			sendParcel(parcelEntity);
		}
	}

	/**
	 * Отправляет посылку на следующий узел в пути. Проверяет доступность узла. Если он доступен, то отправляет на него
	 * POST-запрос с посылкой в теле. Если узел недоступен, то через определённое время отправляет повторный запрос. При
	 * доступности узла меняет статус посылки 'On Next Point'.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}.
	 * @see HttpRequester
	 */
	public void sendParcel(ParcelEntity parcelEntity)
	{
		String parcelPath = parcelEntity.getPath();
		String nextPointName = pointUtil.getNextPointName(parcelPath);
		String nextPointAddress = pointService.getPointByName(nextPointName).getUri();
		httpRequester.repeatRequest(nextPointAddress);
		parcelEntity.setStatus("On Next Point");
		parcelService.updateParcel(parcelEntity);
		httpRequester.doPost(nextPointAddress + "/parcels/receive", parcelEntity);
	}

	/**
	 * Перегрузка метода {@link ParcelSender#updateStatusCallback(ParcelEntity)}. Сделано с целью оптимизации и отсутствия
	 * избыточных запросов в базу данных.
	 * @param uuid
	 *            UUID посылки {@link ParcelEntity#uuid}
	 * @see ParcelSender#updateStatusCallback(ParcelEntity)
	 */
	public void updateStatusCallback(String uuid)
	{
		updateStatusCallback(parcelService.getParcelByUUID(UUIDUtil.getUUID(uuid)));
	}

	/**
	 * Функция обратного вызова по изменению статуса посылки на 'Delivered'. Если данный узел тот, с которого изначально
	 * отправлялась посылка, то выполнение метода прекращается. Если нет, то отправляется POST-запрос с UUID посылки в качестве
	 * параетров запроса.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}.
	 * @see HttpRequester
	 */
	private void updateStatusCallback(ParcelEntity parcelEntity)
	{
		parcelEntity.setStatus("Delivered");
		parcelService.updateParcel(parcelEntity);
		String parcelPath = parcelEntity.getPath();
		if (pointUtil.getPointIndex(parcelPath) == 0)
		{
			return;
		}
		String previousPointName = pointUtil.getPreviousPointName(parcelPath);
		String previousPointAddress = pointService.getPointByName(previousPointName).getUri();
		httpRequester.repeatRequest(previousPointAddress);
		httpRequester.doPost(previousPointAddress + "/parcels/delivered", parcelEntity.getUuid());
	}

	public void setParcelService(ParcelService parcelService)
	{
		this.parcelService = parcelService;
	}

	public void setPointService(PointService pointService)
	{
		this.pointService = pointService;
	}

	public void setHttpRequester(HttpRequester httpRequester)
	{
		this.httpRequester = httpRequester;
	}

	public void setPointUtil(PointUtil pointUtil)
	{
		this.pointUtil = pointUtil;
	}
}
