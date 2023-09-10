package com.project.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.project.controller.services.handler;

public class connectionHandler extends handler implements Runnable {

    public connectionHandler (Socket user) {this.user = user;}

    @Override
    public void run() {
        try {
            out = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            String msg;
            while ((msg = reader.readLine()) != null) {}
        } catch (IOException e) {e.getMessage();shutdown();}
    }
    
}
