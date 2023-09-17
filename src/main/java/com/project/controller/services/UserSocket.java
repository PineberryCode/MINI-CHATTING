package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.project.controller.FacingController;

import javafx.scene.control.TextArea;

public class UserSocket extends Handler implements Runnable {
    
    private String message;
    private TextArea txa;

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
                /*Platform.runLater(() -> {
                    /*if (txa.getId().equals("txaConversation")) {
                        txa.appendText(message);
                    }*/
                    //System.out.println(facingController.getMessaging());
                /* });*/
                //System.out.println(message);
                System.out.println(message);
            }
        } catch (IOException e) {e.getMessage();shutdown();}
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
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //try {
                while (!done) {
                    String msg = reader.readLine();
                    msg = FacingController.getInstance().getMessaging();
                    writer.println(msg);
                }
            } catch (Exception e) {
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
