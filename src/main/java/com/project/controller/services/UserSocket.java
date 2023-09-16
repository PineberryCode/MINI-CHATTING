package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.project.controller.FacingController;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class UserSocket extends Handler implements Runnable {
    
    private String message;
    private TextArea txa;
    private FacingController facingController;

    //public UserSocket (String message) {this.message = message;}

    public UserSocket() {facingController = FacingController.getInstance();}

    @Override
    public void run() {
        try {
            user = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread thread = new Thread(inputHandler);
            thread.start();

            while ((message = reader.readLine()) != null) {
                Platform.runLater(() -> {
                    /*if (txa.getId().equals("txaConversation")) {
                        txa.appendText(message);
                    }*/
                    System.out.println(message);
                    System.out.println(facingController.getMessaging());
                });
                //System.out.println(message);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            reader.close();
            writer.close();
            if (!user.isClosed()) {
                user.close();
            }
        } catch (IOException e) {}
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try (BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!done) {
                    String msg = inReader.readLine();
                    if (msg.equals("--quit")) {
                        writer.println(msg);
                        shutdown();
                    } else {
                        writer.println(msg); // THIS ONE
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public static void main (String[] args) {
        //UserSocket u = new UserSocket();
        //u.run();
        //u.run();
    }
    
}
