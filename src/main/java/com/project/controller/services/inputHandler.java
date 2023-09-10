package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class inputHandler extends handler implements Runnable {

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!done) {
                String msg = reader.readLine();
                if (msg.equals("--quit")) {
                    shutdown();
                }
            }
        } catch (IOException e) {e.getMessage();shutdown();}
    }
    
}
