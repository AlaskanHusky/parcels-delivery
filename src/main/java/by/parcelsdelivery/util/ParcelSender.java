package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.service.PointService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParcelSender {

    private HttpRequester httpRequester;
    private ParcelService parcelService;
    private PointService pointService;

    public ParcelSender() {
        System.out.println("ParcelSender Constructor");
    }

    public void runTasks(ParcelEntity parcelEntity) {
        String parcelPath = parcelEntity.getPath();

        if (isLast(parcelPath)) {
            updateStatusCallback(parcelEntity.getUuid());
        } else {
            sendParcel(parcelEntity, parcelPath);
        }
    }

    private void sendParcel(ParcelEntity parcelEntity, String parcelPath) {
        String nextPointName = getNextPointName(parcelPath);
        String nextPointAddress = pointService.getPointByName(nextPointName).getUri() + "/parcels/receive";
        boolean isAvailable = httpRequester.doPost(nextPointAddress, parcelEntity);

        if (isAvailable) {
            parcelEntity.setStatus("On Next Point");
            parcelService.updateParcel(parcelEntity);
        } else {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            sendParcel(parcelEntity, parcelPath);
        }
    }

    public void updateStatusCallback(String uuid) {
        ParcelEntity parcelEntity = parcelService.getParcelByUUID(uuid);
        String path = parcelEntity.getPath();

        parcelEntity.setStatus("Delivered");
        parcelService.updateParcel(parcelEntity);

        if (!isFirst(path)) {

            String previousPointName = getPreviousPointName(path);
            String nextPointAddress = pointService.getPointByName(previousPointName).getUri() + "/parcels/delivered";
            boolean isAvailable = httpRequester.doPost(nextPointAddress, uuid);

            if (!isAvailable) {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                updateStatusCallback(uuid);
            }
        }
    }

    private boolean isFirst(String path) {
        String currentPointName = getPointName();
        List<String> pointsFromPath = pathParser(path);
        return pointsFromPath.indexOf(currentPointName) == 0;
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

    private String getPreviousPointName(String path) {
        List<String> pathPoints = pathParser(path);
        String currentPoint = getPointName();
        String name = "";

        for (String pointName : pathPoints) {
            if (Objects.equals(currentPoint, pointName)) {
                name = pathPoints.get(pathPoints.indexOf(currentPoint) - 1);
            }
        }

        return name;
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
        String pathToFile = "src/main/resources/point.json";
        String name = "";

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(pathToFile));
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
