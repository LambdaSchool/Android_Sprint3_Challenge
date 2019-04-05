package com.jakeesveld.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {

    public static String httpRequest(String urlString){
        String result = "";
        HttpURLConnection connection = null;
        InputStream stream = null;

        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                stream = connection.getInputStream();
                if(stream != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String line = reader.readLine();
                    while(line != null){
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                connection.disconnect();
            }
        }
        return result;
    }


    public static Bitmap bitmapFromUrl(String urlString){
        Bitmap result = null;
        InputStream stream = null;
        HttpURLConnection connection = null;

        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                stream = connection.getInputStream();
                if(stream != null){
                    result = BitmapFactory.decodeStream(stream);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return result;
    }
}
