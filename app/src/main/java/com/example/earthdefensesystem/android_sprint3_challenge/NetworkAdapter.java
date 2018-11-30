package com.example.earthdefensesystem.android_sprint3_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {
    public static String httpGetRequest(String urlString) {
        String result = "";
        HttpsURLConnection connection = null;
        InputStream stream = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                stream = connection.getInputStream();
                if(stream != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null){
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();
                }

            } else {
                result = String.valueOf(responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (stream != null){
                connection.disconnect();
            }
        }
        return result;
    }
}
