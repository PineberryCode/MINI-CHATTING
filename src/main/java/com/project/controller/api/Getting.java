package com.project.controller.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Getting {
    
    public static String PUBLIC_KEY () {
        /*quarkus-api-rsa*/
        final String urlString = "http://localhost:8080/api/v1/priv";
        StringBuffer strBuffer = null;
        try {
            URL url = new URL(urlString);
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

    public String PRIVATE_KEY () {
        return null;
    }

}
