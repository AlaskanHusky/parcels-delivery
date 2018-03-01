package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequester {

    public HttpRequester() {
        System.out.println("HttpRequester Constructor");
    }

    boolean doPost(String url, ParcelEntity parcelEntity) {
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
            return false;
        }

        return true;
    }

    boolean doPost(String url, String uuid) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        HttpEntity postParams = null;

        httpPost.setHeader("User-Agent", "Mozilla/5.0");
        urlParameters.add(new BasicNameValuePair("UUID", uuid)); // After sending get string as status=Delivered in request body

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
            return false;
        }

        return true;
    }
}
