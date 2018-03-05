package by.parcelsdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import by.parcelsdelivery.entity.ParcelEntity;

/**
 * Главный контроллер, который обрабатывает запросы приложения.
 */
@Controller
public class MainController
{
	public static final String REDIRECT_PATH = "redirect:/";

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
		String parcel = "parcel";
		model.addAttribute(parcel, new ParcelEntity());
		return "index";
	}
}
