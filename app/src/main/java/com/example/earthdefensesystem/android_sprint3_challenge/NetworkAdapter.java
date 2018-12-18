package com.example.earthdefensesystem.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final int TIMEOUT = 3000;

    public static String httpGetRequest(String urlString, String requestType) {
        String result = "";
        InputStream stream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            try {
                connection.setRequestMethod(requestType);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            if(requestType.equals(GET) || requestType.equals(DELETE)) {
                try {
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestType.equals(POST) || requestType.equals(PUT)) {
                OutputStream outputStream = null;
                try {
                    outputStream = connection.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = connection.getInputStream();
                    if (stream != null) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        result = builder.toString();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Bitmap httpImageRequest(String urlString) {
        Bitmap resultImage = null;
        InputStream stream = null;
        HttpURLConnection connection = null;

        URL url = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                stream = connection.getInputStream();
                if (stream != null) {
                    resultImage = BitmapFactory.decodeStream(stream);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return resultImage;
    }


}
