package by.parcelsdelivery.util;

public class UUIDUtil {
    private static final String DELIMITER = "=";

    public static String getUUID(String uuid) {

        return uuid.split(DELIMITER)[1];
    }
}
