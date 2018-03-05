package by.parcelsdelivery.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import by.parcelsdelivery.entity.ParcelEntity;

/**
 * Класс для отправки http-запросов на другие узлы.
 */
public class HttpRequester
{
	public HttpRequester()
	{
		System.out.println("HttpRequester Constructor");
	}

	/**
	 * Отправляет POST-запрос на заданный url. В теле запроса содержится посылка, преобразованная в JSON.
	 * @param url
	 *            адрес на который посылается запрос
	 * @param parcelEntity
	 *            объект класса {@link ParcelEntity}, который передаётся в теле запроса
	 */
	void doPost(String url, ParcelEntity parcelEntity)
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		Gson gson = new Gson();
		StringEntity postStringEntity = null;
		httpPost.setHeader("User-Agent", "Mozilla/5.0");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept-Charset", "utf-8");
		try
		{
			postStringEntity = new StringEntity(gson.toJson(parcelEntity));
		}
		catch (UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		httpPost.setEntity(postStringEntity);
		try
		{
			httpClient.execute(httpPost);
			httpClient.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Отправляет POST-запрос на заданный url. В заголовке запроса содержится параметр с UUID посылки.
	 * @param url
	 *            адрес на который посылается запрос
	 * @param uuid
	 *            UUID посылки {@link ParcelEntity#uuid}, передаваемый в качестве параметра запроса
	 */
	void doPost(String url, String uuid)
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<>();
		HttpEntity postParams = null;
		httpPost.setHeader("User-Agent", "Mozilla/5.0");
		urlParameters.add(new BasicNameValuePair("UUID", uuid));
		try
		{
			postParams = new UrlEncodedFormEntity(urlParameters);
		}
		catch (UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		httpPost.setEntity(postParams);
		try
		{
			httpClient.execute(httpPost);
			httpClient.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Повторяет HEAD запрос для проверки доступности ресура.
	 * @param url
	 *            адрес ресурса
	 */
	void repeatRequest(String url)
	{
		boolean pointAccess;
		int interval = 5000;
		SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss': '");
		do
		{
			pointAccess = doHead(url);
			if (pointAccess)
			{
				break;
			}
			else
			{
				System.out.print(formatDate.format(new Date()));
				System.out.println("Resource with the address " + url + " not responding.\nSend re-request after "
					+ interval / 1000 + " seconds");
				delay(interval);
			}
		}
		while (!pointAccess);
	}

	/**
	 * Отправляет HEAD запрос с пустым телом для проверки доступности ресурса. Если ресурс доступен, то возвращает true. Если не
	 * удалось установить соединение, то возвращает false.
	 * @param url
	 *            адрес ресурса
	 * @return true/false
	 */
	private boolean doHead(String url)
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpHead httpHead = new HttpHead(url);
		CloseableHttpResponse httpResponse;
		int responseStatus;
		try
		{
			httpResponse = httpClient.execute(httpHead);
			responseStatus = httpResponse.getStatusLine().getStatusCode();
			httpClient.close();
		}
		catch (IOException ex)
		{
			return false;
		}
		return responseStatus == HttpStatus.SC_OK;
	}

	/**
	 * Останавливает выполнение основного потока на заданное время.
	 * @param delay
	 *            время задержки
	 */
	private void delay(int delay)
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}
