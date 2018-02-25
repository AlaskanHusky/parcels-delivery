package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.service.PointService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ParcelSender {

    private HttpRequester httpRequester;
    private ParcelService parcelService;
    private PointService pointService;

    public ParcelSender() {
        System.out.println("ParcelSender Constructor");
    }

    public void sendParcel(ParcelEntity parcelEntity) {
        String nextPointName;
        String nextPointAddress;
        String parcelPath = parcelEntity.getPath();

        if (isLast(parcelPath)) {
            parcelEntity.setStatus("Delivered");
            parcelService.updateParcel(parcelEntity);
            // TODO Add status update in the database nodes that were previous in path
        } else {
            nextPointName = getNextPointName(parcelPath);
            nextPointAddress = pointService.getPointByName(nextPointName).getUri();
            boolean isAvailable = httpRequester.doHead(nextPointAddress);
            if (isAvailable) {
                parcelEntity.setStatus("On Next Point");
                parcelService.updateParcel(parcelEntity);
                nextPointAddress = nextPointAddress + "/parcels/get";
                httpRequester.doPost(nextPointAddress, parcelEntity);
            } else {
                // TODO Add a timer to send a constant request for the next node performance
            }
        }
    }

    private boolean isLast(String path) {
        String currentPointName = getPointName();
        List<String> pointsFromPath = pathParser(path);
        boolean bit = false;

        for (String pointName : pointsFromPath) {
            if (Objects.equals(currentPointName, pointName)) {
                int i = pointsFromPath.indexOf(pointName);
                bit = i == (pointsFromPath.size() - 1);
            }
        }

        return bit;
    }

    private String getNextPointName(String path) {
        List<String> pathPoints = pathParser(path);
        String currentPoint = getPointName();
        String name = "";

        for (String pointName : pathPoints) {
            if (Objects.equals(currentPoint, pointName)) {
                name = pathPoints.get(pathPoints.indexOf(currentPoint) + 1);
            }
        }

        return name;
    }

    private String getPointName() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        String pathToFile = "src/main/resources/point.json";
        String name = "";

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(pathToFile));
            name = (String) jsonObject.get("name");
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    private List<String> pathParser(String path) {
        String delimiter = "-";
        return Arrays.asList(path.split(delimiter));
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }

    public void setHttpRequester(HttpRequester httpRequester) {
        this.httpRequester = httpRequester;
    }
}
