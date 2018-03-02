package by.parcelsdelivery.util;

class UUIDUtil {
    private static final String DELIMITER = "=";

    static String getUUID(String uuid) {

        return uuid.split(DELIMITER)[1];
    }
}
