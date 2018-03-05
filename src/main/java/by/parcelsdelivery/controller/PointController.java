package by.parcelsdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import static by.parcelsdelivery.controller.MainController.REDIRECT_PATH;

/**
 * Класс для обработки запросов связанных с операциями CRUD для узла {@link PointEntity}
 */
@Controller
public class PointController
{
	private static final String POINT_ATTRIBUTE = "point";
	private static final String LIST_OF_POINTS_ATTRIBUTE = "listOfPoints";
	private static final String POINTS_PAGE = "points";
	private PointService pointService;

	public PointController()
	{
		System.out.println("PointController Constructor");
	}

	/**
	 * Обрабатывает запрос пользователя и отдаёт страницу. На странице присутствует форма для добавления и редактирования узла
	 * {@link PointEntity}. Если таблица в базе данных не пуста, то выводит список всех узлов.
	 * @param model
	 *            объект слоя Model в Spring MVC
	 * @return имя jsp-страницы, которое отдаёт сервер
	 */
	@RequestMapping(value = "/points", method = RequestMethod.GET)
	public String getPoints(Model model)
	{
		model.addAttribute(POINT_ATTRIBUTE, new PointEntity());
		model.addAttribute(LIST_OF_POINTS_ATTRIBUTE, pointService.getAll());
		return POINTS_PAGE;
	}

	/**
	 * Обрабатывает запрос с формы для добавления или редактирования узла. Заполняет поля посылки данными с формы. Сохраняет
	 * посылку в базе данных.
	 * @param pointEntity
	 *            объект класса {@link PointEntity}
	 * @return перенаправление на страницу со списком узлов
	 */
	@RequestMapping(value = "/points/add", method = RequestMethod.POST)
	public String addPoint(@ModelAttribute(POINT_ATTRIBUTE) PointEntity pointEntity)
	{
		if (pointEntity.getId() == 0)
		{
			pointService.save(pointEntity);
		}
		else
		{
			pointService.update(pointEntity);
		}
		return REDIRECT_PATH + POINTS_PAGE;
	}

	/**
	 * Заполняет форму данными выбранного узла. После редактирования данных обновляет поля узла в базе данных.
	 * @param pointId
	 *            id посылки {@link PointEntity#id}
	 * @param model
	 *            объект слоя Model в Spring MVC
	 * @return перенаправление на страницу со списком узлов
	 */
	@RequestMapping(value = "/points/{id}/update", method = RequestMethod.GET)
	public String updatePoint(@PathVariable("id") int pointId, Model model)
	{
		model.addAttribute(POINT_ATTRIBUTE, pointService.getById(pointId));
		model.addAttribute(LIST_OF_POINTS_ATTRIBUTE, pointService.getAll());
		return POINTS_PAGE;
	}

	/**
	 * Удаляет выбранный узел из базы данных.
	 * @param pointId
	 *            id посылки {@link PointEntity#id}
	 * @return перенаправление на страницу с посылками
	 */
	@RequestMapping(value = "/points/{id}/delete", method = RequestMethod.GET)
	public String deletePoint(@PathVariable("id") int pointId)
	{
		pointService.delete(pointId);
		return REDIRECT_PATH + POINTS_PAGE;
	}

	public void setPointService(PointService pointService)
	{
		this.pointService = pointService;
	}
}
