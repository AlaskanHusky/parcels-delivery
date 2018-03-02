package by.parcelsdelivery.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class PointUtil {

    public PointUtil() {
        System.out.println("PointUtil Constructor");
    }

    boolean isLast(String path) {
        return getPointIndex(path) == pathParser(path).size() - 1;
    }

    String getNextPointName(String path){
        return pathParser(path).get(getPointIndex(path) + 1);
    }

    String getPreviousPointName(String path){
        return pathParser(path).get(getPointIndex(path) - 1);
    }

    int getPointIndex(String path) {
        List<String> pointsFromPath = pathParser(path);
        String currentPointName = getPointName();
        return pointsFromPath.indexOf(currentPointName);
    }

    private static String getPointName() {
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

}