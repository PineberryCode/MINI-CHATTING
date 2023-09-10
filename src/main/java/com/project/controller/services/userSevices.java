package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class userSevices extends handler /*implements Runnable*/ {
    
    private int PORT = 5000;

    //@Override
    public void socketUser() {
        try {
            user = new Socket("192.168.100.9", PORT);
            out = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            inputHandler input = new inputHandler();
            Thread thread = new Thread(input);
            thread.start();

            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg); // This will showed in the TextArea.
            }
        } catch (IOException e) {e.getMessage(); shutdown();}
    }
    
}
