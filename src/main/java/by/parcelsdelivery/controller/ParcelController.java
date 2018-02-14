package by.parcelsdelivery.controller;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ParcelController {

    private ParcelService parcelService;

    public ParcelController() {
        System.out.println("ParcelController Constructor");
    }

    @RequestMapping(value = "/parcels", method = RequestMethod.GET)
    public String getParcels(Model model) {
        model.addAttribute("parcel", new ParcelEntity());
        model.addAttribute("listOfParcels", parcelService.getAllParcels());
        return "parcels";
    }

    @RequestMapping(value = "/parcels/add", method = RequestMethod.POST)
    public String addParcel(@ModelAttribute("parcel") ParcelEntity parcelEntity) {
        if (parcelEntity.getId() == 0) {
            parcelService.createParcel(parcelEntity);
        } else {
            parcelService.updateParcel(parcelEntity);
        }
        return "redirect:/parcels";
    }

    @RequestMapping(value = "/parcels/{id}/update", method = RequestMethod.GET)
    public String updateParcel(@PathVariable("id") int parcelId, Model model) {
        model.addAttribute("parcel", parcelService.getParcel(parcelId));
        model.addAttribute("listOfParcels", parcelService.getAllParcels());
        return "parcels";
    }

    @RequestMapping(value = "/parcels/{id}/delete", method = RequestMethod.GET)
    public String deletePoint(@PathVariable("id") int parcelId) {
        parcelService.deleteParcel(parcelId);
        return "redirect:/parcels";
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
}
