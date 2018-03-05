package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Класс для обработки запросов связанных с операциями CRUD для узла {@link PointEntity}
 */
@Controller
public class PointController
{
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
		model.addAttribute("point", new PointEntity());
		model.addAttribute("listOfPoints", pointService.getAllPoints());
		return "points";
	}

	/**
	 * Обрабатывает запрос с формы для добавления или редактирования узла. Заполняет поля посылки данными с формы. Сохраняет
	 * посылку в базе данных.
	 * @param pointEntity
	 *            объект класса {@link PointEntity}
	 * @return перенаправление на страницу со списком узлов
	 */
	@RequestMapping(value = "/points/add", method = RequestMethod.POST)
	public String addPoint(@ModelAttribute("point") PointEntity pointEntity)
	{
		if (pointEntity.getId() == 0)
		{
			// Therefore new point and we add it
			pointService.createPoint(pointEntity);
		}
		else
		{
			// Therefore existing and we update it
			pointService.updatePoint(pointEntity);
		}
		return "redirect:/points";
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
		model.addAttribute("point", pointService.getPoint(pointId));
		model.addAttribute("listOfPoints", pointService.getAllPoints());
		return "points";
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
		pointService.deletePoint(pointId);
		return "redirect:/points";
	}

	public void setPointService(PointService pointService)
	{
		this.pointService = pointService;
	}
}
