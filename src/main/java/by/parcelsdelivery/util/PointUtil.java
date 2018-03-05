package by.parcelsdelivery.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Класс для работы с путём посылки.
 */
class PointUtil
{
	public PointUtil()
	{
		System.out.println("PointUtil Constructor");
	}

	/**
	 * Определяет, является ли узел последним в пути.
	 * @param path
	 *            путь посылки
	 * @return true/false
	 */
	boolean isLast(String path)
	{
		return getPointIndex(path) == pathParser(path).size() - 1;
	}

	/**
	 * Получает имя следующего узла в пути.
	 * @param path
	 *            путь посылки
	 * @return имя следующего узла
	 */
	String getNextPointName(String path)
	{
		return pathParser(path).get(getPointIndex(path) + 1);
	}

	/**
	 * Получает имя предыдущего узла в пути.
	 * @param path
	 *            путь посылки
	 * @return имя предыдущего узла
	 */
	String getPreviousPointName(String path)
	{
		return pathParser(path).get(getPointIndex(path) - 1);
	}

	/**
	 * Получает номер текущего узла в пути.
	 * @param path
	 *            путь посылки
	 * @return индекс узла
	 */
	int getPointIndex(String path)
	{
		List<String> pointsFromPath = pathParser(path);
		String currentPointName = getPointName();
		return pointsFromPath.indexOf(currentPointName);
	}

	/**
	 * Получает имя узла из файла point.json.
	 * @return Имя узла
	 */
	private static String getPointName()
	{
		JSONParser parser = new JSONParser();
		String pathToFile = "src/main/resources/point.json";
		String name = "";
		try
		{
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(pathToFile));
			name = (String) jsonObject.get("name");
		}
		catch (IOException | ParseException ex)
		{
			ex.printStackTrace();
		}
		return name;
	}

	/**
	 * Возвращает список узлов в пути посылки.
	 * @param path
	 *            путь посылки
	 * @return ArrayList с именами узлов
	 */
	private List<String> pathParser(String path)
	{
		String delimiter = "-";
		return Arrays.asList(path.split(delimiter));
	}
}
