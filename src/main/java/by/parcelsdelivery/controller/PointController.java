package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PointController {

    private PointService pointService;

    public PointController() {
        System.out.println("PointController Constructor");
    }

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public String getPoints(Model model) {
        model.addAttribute("point", new PointEntity());
        model.addAttribute("listOfPoints", pointService.getAllPoints());
        return "points";
    }

    @RequestMapping(value = "/points/add", method = RequestMethod.POST)
    public String addPoint(@ModelAttribute("point") PointEntity pointEntity) {
        if (pointEntity.getId() == 0) {
            // Therefore new point and we add it
            pointService.createPoint(pointEntity);
        } else {
            // Therefore existing and we update it
            pointService.updatePoint(pointEntity);
        }
        return "redirect:/points";
    }

    @RequestMapping(value = "/points/{id}/update", method = RequestMethod.GET)
    public String updatePoint(@PathVariable("id") int pointId, Model model) {
        model.addAttribute("point", pointService.getPoint(pointId));
        model.addAttribute("listOfPoints", pointService.getAllPoints());
        return "points";
    }

    @RequestMapping(value = "/points/{id}/delete", method = RequestMethod.GET)
    public String deletePoint(@PathVariable("id") int pointId) {
        pointService.deletePoint(pointId);
        return "redirect:/points";
    }

    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }
}
