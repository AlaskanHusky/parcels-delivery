package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PointController {

    private PointService pointService;

    public PointController() {
        System.out.println("PointController Constructor");
    }

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public String listPoints(Model model) {
        model.addAttribute("point", new PointEntity());
        model.addAttribute("listPoints", this.pointService.getAllPoints());
        return "points";
    }

    @RequestMapping(value = "/point/add", method = RequestMethod.POST)
    public String addPoint(@ModelAttribute("point") PointEntity pointEntity) {
        this.pointService.createPoint(pointEntity);
        return "redirect:/points";

    }

    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }
}
