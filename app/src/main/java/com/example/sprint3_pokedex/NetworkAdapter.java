package com.example.sprint3_pokedex;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {

    public static final int READ_TIMEOUT = 3000;
    public static final int CONNECT_TIMEOUT = 3000;

    static String httpRequest(String urlString){
        return httpRequest(urlString, null);
    }

    static String httpRequest(String urlString, Map<String, String> headerProperties) {
        String result = "";
        InputStream inputStream = null;
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(urlString);

            Log.i("test2", url.toString());
            connection = (HttpsURLConnection) url.openConnection();
           // connection.setConnectTimeout(CONNECT_TIMEOUT);
           // connection.setReadTimeout(READ_TIMEOUT);

            if(headerProperties!=null) {
                for(Map.Entry<String, String> entry : headerProperties.entrySet()){
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.connect();

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                if (inputStream != null) {
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder builder = new StringBuilder();

                    String line = reader.readLine();
                    while(line != null){
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();

                }
            } else {
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = "MALFORMED EXCEPTION";
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOEXCEPTION";
        } finally {
            if(inputStream != null) {
                try{
                    inputStream.close();}
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }}
        return result;
    }
}