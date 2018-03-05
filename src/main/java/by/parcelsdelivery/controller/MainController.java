package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.util.ParcelSender;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Главный контроллер, который обрабатывает запросы, связанные с отправкой посылок {@link ParcelEntity}.
 */
@Controller
public class MainController
{
	private ParcelService parcelService;
	private ParcelSender parcelSender;

	public MainController()
	{
		System.out.println("MainController Constructor");
	}

	/**
	 * Обрабатывает запрос пользователя по корневому пути. Отображает главную страницу сервиса.
	 * @param model
	 *            объект слоя Model в Spring MVC
	 * @return имя jsp-страницы, которое отдаёт сервер
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model)
	{
		model.addAttribute("parcel", new ParcelEntity());
		return "index";
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
	public String addParcel(@ModelAttribute("parcel") ParcelEntity parcelEntity)
	{
		parcelEntity.setUuid(UUID.randomUUID().toString());
		parcelService.createParcel(parcelEntity);
		parcelSender.sendParcel(parcelEntity);
		return "redirect:/";
	}

	/**
	 * Обрабатывает запрос от другой точки с посылкой в теле запроса Устанавливает ей статус "Transit", так как посылка приходит
	 * со статусом "On Next Point". Если точка последняя в пути, то вызывается функция обратного вызова
	 * {@link ParcelSender#updateStatusCallback(ParcelEntity)} изменения статуса посылки на предыдущих точках. Если точка не
	 * последняя, то посылка передаётся следующей по очереди с помощью вызова функции
	 * {@link ParcelSender#sendParcel(ParcelEntity)}.
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}
	 * @return перенаправление на главную страницу
	 * @see ParcelSender
	 */
	@RequestMapping(value = "/parcels/receive", method = RequestMethod.POST)
	public String getParcel(@RequestBody ParcelEntity parcelEntity)
	{
		parcelEntity.setStatus("Transit");
		parcelService.createParcel(parcelEntity);
		parcelSender.handleParcel(parcelEntity);
		return "redirect:/";
	}

	/**
	 * Обрабатывает запрос от другой точки с UUID посылки в теле запроса. Вызывает функцию
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
		return "redirect:/";
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
