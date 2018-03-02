package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;

import com.google.gson.Gson;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpRequester {

    public HttpRequester() {
        System.out.println("HttpRequester Constructor");
    }

    void doPost(String url, ParcelEntity parcelEntity) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Gson gson = new Gson();
        StringEntity postStringEntity = null;

        httpPost.setHeader("User-Agent", "Mozilla/5.0");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept-Charset", "utf-8");

        try {
            postStringEntity = new StringEntity(gson.toJson(parcelEntity));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        httpPost.setEntity(postStringEntity);

        try {
            httpClient.execute(httpPost);
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void doPost(String url, String uuid) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        HttpEntity postParams = null;

        httpPost.setHeader("User-Agent", "Mozilla/5.0");
        urlParameters.add(new BasicNameValuePair("UUID", uuid));

        try {
            postParams = new UrlEncodedFormEntity(urlParameters);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        httpPost.setEntity(postParams);

        try {
            httpClient.execute(httpPost);
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void repeatRequest(String url) {
        boolean pointAccess;
        int interval = 5000;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss': '");
        do {
            pointAccess = doHead(url);
            if (pointAccess) {
                break;
            } else {
                System.out.print(formatDate.format(new Date()));
                System.out.println("Resource with the address " + url + " not responding.\nSend re-request after " + interval/1000 + " seconds");
                delay(interval);
            }
        } while (!pointAccess);
    }

    private boolean doHead(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHead httpHead = new HttpHead(url);
        CloseableHttpResponse httpResponse;
        int responseStatus;

        try {
            httpResponse = httpClient.execute(httpHead);
            responseStatus = httpResponse.getStatusLine().getStatusCode();
            httpClient.close();
        } catch (IOException ex) {
            return false;
        }

        return responseStatus == HttpStatus.SC_OK;
    }

    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
