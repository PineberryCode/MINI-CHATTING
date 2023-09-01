package com.project.controller.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Getting {
    
    private final static String urlString = "http://localhost:8080/api/v1";
    
    /*quarkus-api-rsa :: GET*/
    public static String PRIVATE_KEY () {
        String priv = urlString.concat("/priv");
        StringBuffer strBuffer = null;
        try {
            URL url = new URL(priv);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int response = connection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                strBuffer = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    strBuffer.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("GET request failed. Response Code: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return strBuffer.toString();
    }

    public static String PUBLIC_KEY () {
        final String pub = urlString.concat("/pub");
        StringBuffer strBuffer = null;
        try {
            URL url = new URL(pub);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int response = connection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                strBuffer = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    strBuffer.append(inputLine);
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuffer.toString();
    }

}
