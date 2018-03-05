package by.parcelsdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.util.ParcelSender;

/**
 * Класс для обработки запросов связанных с операциями CRUD для посылки {@link ParcelEntity}
 */
@Controller
public class ParcelController
{
	private static final String PARCEL_ATTRIBUTE = "parcel";
	private static final String PARCELS_PAGE = "parcels";
	private static final String REDIRECT_PATH = "redirect:/";
	private ParcelService parcelService;
	private ParcelSender parcelSender;

	public ParcelController()
	{
		System.out.println("ParcelController Constructor");
	}

	/**
	 * Обрабатывает запрос пользователя и отдаёт страницу. На странице присутствует форма для добавления и редактирования посылки
	 * {@link ParcelEntity}. Если таблица в базе данных не пуста, то выводит список всех посылок.
	 * @param model
	 *            объект слоя Model в Spring MVC
	 * @return имя jsp-страницы, которое отдаёт сервер
	 */
	@RequestMapping(value = "/parcels", method = RequestMethod.GET)
	public String getParcels(Model model)
	{
		model.addAttribute(PARCEL_ATTRIBUTE, new ParcelEntity());
		model.addAttribute("listOfParcels", parcelService.getAll());
		return PARCELS_PAGE;
	}

	/**
	 * Обрабатывает запрос с формы для добавления или редактирования посылки. Заполняет поля посылки данными с формы. Сохраняет
	 * посылку в базе данных.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}
	 * @return перенаправление на страницу с посылками
	 */
	@RequestMapping(value = "/parcels/add", method = RequestMethod.POST)
	public String addParcel(@ModelAttribute(PARCEL_ATTRIBUTE) ParcelEntity parcelEntity)
	{
		if (parcelEntity.getId() == 0)
		{
			parcelService.create(parcelEntity);
		}
		else
		{
			parcelService.update(parcelEntity);
		}
		return REDIRECT_PATH + PARCELS_PAGE;
	}

	/**
	 * Заполняет форму данными выбранной посылки. После редактирования данных обновляет поля посылки в базе данных.
	 * @param parcelId
	 *            id посылки {@link ParcelEntity#id}
	 * @param model
	 *            объект слоя Model в Spring MVC
	 * @return перенаправление на страницу с посылками
	 */
	@RequestMapping(value = "/parcels/{id}/update", method = RequestMethod.GET)
	public String updateParcel(@PathVariable("id") int parcelId, Model model)
	{
		model.addAttribute(PARCEL_ATTRIBUTE, parcelService.getById(parcelId));
		model.addAttribute("listOfParcels", parcelService.getAll());
		return PARCELS_PAGE;
	}

	/**
	 * Удаляет выбранную посылку из базы данных.
	 * @param parcelId
	 *            id посылки {@link ParcelEntity#id}
	 * @return перенаправление на страницу с посылками
	 */
	@RequestMapping(value = "/parcels/{id}/delete", method = RequestMethod.GET)
	public String deleteParcel(@PathVariable("id") int parcelId)
	{
		parcelService.delete(parcelId);
		return REDIRECT_PATH + PARCELS_PAGE;
	}

	/**
	 * Обрабатывает запрос с формы для отправки посылки. Заполняет поля посылки данными с формы. Сохраняет посылку в базе данных.
	 * Перед сохранением, для посылки генерируется уникальный UUID. После этого отправляет на следующую точку в пути посылки.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}
	 * @return перенаправление на главную страницу
	 * @see ParcelSender
	 */
	@RequestMapping(value = "/parcels/send", method = RequestMethod.POST)
	public String sendParcel(@ModelAttribute(PARCEL_ATTRIBUTE) ParcelEntity parcelEntity)
	{
		parcelService.create(parcelEntity);
		parcelSender.sendParcel(parcelEntity);
		return REDIRECT_PATH;
	}

	/**
	 * Обрабатывает запрос от другой почтовой точки с посылкой в теле запроса Устанавливает ей статус "Transit", так как посылка
	 * приходит со статусом "On Next Point". Если почтовая точка последняя в пути, то вызывается функция обратного вызова
	 * {@link ParcelSender#updateStatusCallback(ParcelEntity)} изменения статуса посылки на предыдущих точках. Если точка не
	 * последняя, то посылка передаётся следующей по очереди с помощью вызова функции
	 * {@link ParcelSender#sendParcel(ParcelEntity)}.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}
	 * @return перенаправление на главную страницу
	 * @see ParcelSender
	 */
	@RequestMapping(value = "/parcels/receive", method = RequestMethod.POST)
	public String receiveParcel(@RequestBody ParcelEntity parcelEntity)
	{
		parcelService.save(parcelEntity);
		parcelSender.handleParcel(parcelEntity);
		return REDIRECT_PATH;
	}

	/**
	 * Обрабатывает запрос от другой почтовой точки с UUID посылки в теле запроса. Вызывает функцию
	 * {@link ParcelSender#updateStatusCallback(ParcelEntity)} на изменение статуса посылки.
	 * @param uuid
	 *            уникальный идентификатор посылки {@link ParcelEntity#uuid}
	 * @return перенаправление на главную страницу
	 * @see ParcelSender
	 */
	@RequestMapping(value = "/parcels/delivered", method = RequestMethod.POST)
	public String updateParcelStatus(@RequestBody String uuid)
	{
		parcelSender.updateStatusCallback(uuid);
		return REDIRECT_PATH;
	}

	public void setParcelService(ParcelService parcelService)
	{
		this.parcelService = parcelService;
	}

	public void setParcelSender(ParcelSender parcelSender)
	{
		this.parcelSender = parcelSender;
	}
}
