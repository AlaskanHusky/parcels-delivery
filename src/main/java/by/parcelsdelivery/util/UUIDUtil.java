package by.parcelsdelivery.util;

class UUIDUtil
{
	private static final String DELIMITER = "=";

	/**
	 * Получает UUID посылки из строки параметров запроса.
	 * @param uuid
	 *            строка параметров запроса
	 * @return UUID посылки
	 */
	static String getUUID(String uuid)
	{
		return uuid.split(DELIMITER)[1];
	}
}
