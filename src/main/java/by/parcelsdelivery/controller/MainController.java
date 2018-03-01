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
        String parcelUUID = UUID.randomUUID().toString();
        parcelEntity.setUuid(parcelUUID);
        parcelService.createParcel(parcelEntity);
        parcelSender.runTasks(parcelEntity);
        return "redirect:/";
    }

    @RequestMapping(value = "/parcels/receive", method = RequestMethod.POST)
    public String getParcel(@RequestBody ParcelEntity parcelEntity) {
        parcelService.createParcel(parcelEntity);
        parcelSender.runTasks(parcelEntity);
        return "redirect:/";
    }

    @RequestMapping(value = "/parcels/delivered", method = RequestMethod.POST)
    public String updateParcelStatus(@RequestBody String uuid) {
        parcelSender.updateStatusCallback(uuid.split("=")[1]);
        return "redirect:/";
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    public void setParcelSender(ParcelSender parcelSender) {
        this.parcelSender = parcelSender;
    }
}