package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.service.PointService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

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

    public void handleParcel(ParcelEntity parcelEntity) {
        String parcelPath = parcelEntity.getPath();

        if (isLast(parcelPath)) {
            updateStatusCallback(parcelEntity);
        } else {
            sendParcel(parcelEntity, parcelPath);
        }
    }

    private void sendParcel(ParcelEntity parcelEntity, String parcelPath) {
        String nextPointName = pathParser(parcelPath).get(getPointIndex(parcelPath) + 1);
        String nextPointAddress = pointService.getPointByName(nextPointName).getUri() + "/parcels/receive";
        boolean available = httpRequester.doPost(nextPointAddress, parcelEntity);

        if (available) {
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
        updateStatusCallback(parcelService.getParcelByUUID(uuid));
    }

    private void updateStatusCallback(ParcelEntity parcelEntity) {
        String path = parcelEntity.getPath();

        parcelEntity.setStatus("Delivered");
        parcelService.updateParcel(parcelEntity);

        if (getPointIndex(path) == 0)
        {
            return;
        }

        String previousPointName = pathParser(path).get(getPointIndex(path) - 1);
        String nextPointAddress = pointService.getPointByName(previousPointName).getUri() + "/parcels/delivered";
        boolean isAvailable = httpRequester.doPost(nextPointAddress, parcelEntity.getUuid());

        if (!isAvailable) {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            updateStatusCallback(parcelEntity);
        }

    }

    private boolean isLast(String path) {
        return getPointIndex(path) == pathParser(path).size() - 1;
    }

    private int getPointIndex(String path) {
        List<String> pointsFromPath = pathParser(path);
        String currentPointName = getPointName();
        return pointsFromPath.indexOf(currentPointName);
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
