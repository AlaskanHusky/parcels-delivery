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

@Controller
public class MainController {

    private ParcelService parcelService;
    private ParcelSender parcelSender;

    public MainController() {
        System.out.println("MainController Constructor");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("parcel", new ParcelEntity());
        return "index";
    }

    @RequestMapping(value = "/parcels/send", method = RequestMethod.POST)
    public String addParcel(@ModelAttribute("parcel") ParcelEntity parcelEntity) {
        parcelService.createParcel(parcelEntity);
        parcelSender.sendParcel(parcelEntity);
        return "redirect:/";
    }

    @RequestMapping(value = "/parcels/get", method = RequestMethod.POST)
    public String getParcel(@RequestBody ParcelEntity parcelEntity) {
        parcelService.createParcel(parcelEntity);
        parcelSender.sendParcel(parcelEntity);
        return "redirect:/";
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    public void setParcelSender(ParcelSender parcelSender) {
        this.parcelSender = parcelSender;
    }
}
