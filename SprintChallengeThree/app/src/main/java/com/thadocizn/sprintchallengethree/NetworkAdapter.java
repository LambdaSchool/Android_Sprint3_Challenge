package com.thadocizn.sprintchallengethree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {
    public static String httpGetRequest(String strUrl){
        String result = "";
        HttpURLConnection connection = null;
        InputStream stream = null;

        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                //start parsing our value
                stream = connection.getInputStream();
                if (stream != null){
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null){
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();
                }
            }else {
                result = String.valueOf(responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
