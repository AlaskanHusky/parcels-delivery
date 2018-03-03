package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Класс для обработки запросов связанных
 * с операциями CRUD для посылки {@link ParcelEntity}
 */
@Controller
public class ParcelController {

    private ParcelService parcelService;

    public ParcelController() {
        System.out.println("ParcelController Constructor");
    }

    /**
     * Обрабатывает запрос пользователя и отдаёт страницу.
     * На странице присутствует форма для добавления
     * и редактирования посылки {@link ParcelEntity}.
     * Если таблица в базе данных не пуста, то
     * выводит список всех посылок.
     *
     * @param   model  объект слоя Model в Spring MVC
     * @return         имя jsp-страницы, которое отдаёт сервер
     */
    @RequestMapping(value = "/parcels", method = RequestMethod.GET)
    public String getParcels(Model model) {
        model.addAttribute("parcel", new ParcelEntity());
        model.addAttribute("listOfParcels", parcelService.getAllParcels());
        return "parcels";
    }

    /**
     * Обрабатывает запрос с формы для добавления
     * или редактирования посылки.
     * Заполняет поля посылки данными с формы.
     * Сохраняет посылку в базе данных.
     *
     * @param   parcelEntity  объект класса {@link ParcelEntity}
     * @return                перенаправление на страницу с посылками
     */
    @RequestMapping(value = "/parcels/add", method = RequestMethod.POST)
    public String addParcel(@ModelAttribute("parcel") ParcelEntity parcelEntity) {
        if (parcelEntity.getId() == 0) {
            parcelService.createParcel(parcelEntity);
        } else {
            parcelService.updateParcel(parcelEntity);
        }
        return "redirect:/parcels";
    }

    /**
     * Заполняет форму данными выбранной посылки.
     * После редактирования данных обновляет поля посылки
     * в базе данных.
     *
     * @param   parcelId  id посылки {@link ParcelEntity#id}
     * @param   model     объект слоя Model в Spring MVC
     * @return            перенаправление на страницу с посылками
     */
    @RequestMapping(value = "/parcels/{id}/update", method = RequestMethod.GET)
    public String updateParcel(@PathVariable("id") int parcelId, Model model) {
        model.addAttribute("parcel", parcelService.getParcel(parcelId));
        model.addAttribute("listOfParcels", parcelService.getAllParcels());
        return "parcels";
    }

    /**
     * Удаляет выбранную посылку из базы данных.
     *
     * @param   parcelId  id посылки {@link ParcelEntity#id}
     * @return            перенаправление на страницу с посылками
     */
    @RequestMapping(value = "/parcels/{id}/delete", method = RequestMethod.GET)
    public String deletePoint(@PathVariable("id") int parcelId) {
        parcelService.deleteParcel(parcelId);
        return "redirect:/parcels";
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
}
