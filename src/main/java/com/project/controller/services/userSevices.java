package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class userSevices extends handler {
    
    private int PORT = 5000;

    public String socketUser(String msg) {
        try {
            user = new Socket("192.168.100.9", PORT); //sending to the server.
            out = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            inputHandler input = new inputHandler();
            Thread thread = new Thread(input);
            thread.start();
            while ((msg = reader.readLine()) == null) {
                System.out.println("You cannot send a null such as a message.");
            }

        } catch (IOException e) {e.getMessage(); shutdown();}
        return msg;
    }
    
}
