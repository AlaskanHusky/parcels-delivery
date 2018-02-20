package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StatusChanger {

    private ParcelService parcelService;

    public StatusChanger() {
        System.out.println("StatusChanger Constructor");
    }

    private String getPointName(String filename) {
        JSONParser parser = new JSONParser();
        String name = "";
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(filename));
            name = (String) jsonObject.get("name");
        } catch (IOException |ParseException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    private List<String> pathParser(String path) {
        String delimiter = "-";
        return Arrays.asList(path.split(delimiter));
    }

    private boolean isLast(String path, String filename) {
        String currentPointName = getPointName(filename);
        List<String> pointsFromPath = pathParser(path);
        boolean bit = false;

        for (String pointName : pointsFromPath) {
            if (Objects.equals(currentPointName, pointName)) {
                int i = pointsFromPath.indexOf(pointName);
                if (i == (pointsFromPath.size()-1)) {
                    bit = true;
                } else {
                    bit = false;
                }
            }
        }

        return bit;
    }

    public void updateStatus(ParcelEntity parcelEntity, boolean isNextActive) {
        String status;
        String pathToFile = "src/main/resources/point.json";

        if (isLast(parcelEntity.getPath(), pathToFile)){
            status = "Delivered";
        } else if (isNextActive) { // The variable 'isNextActive' should be obtained from the Requester Class
            status = "On Next Point";
        } else {
            status = "Transit";
        }
        parcelEntity.setStatus(status);
        parcelService.updateParcel(parcelEntity);
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
}
