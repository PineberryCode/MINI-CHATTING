package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.project.controller.FacingController;

public class UserSocket extends Handler implements Runnable {

    //private String message;

    @Override
    public void run() {
        try {
            user = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            Thread thread = new Thread(new InputHandler());
            thread.start();

            while (message == null) {
                Thread.sleep(10);
            }

            //message = FacingController.getInstance().getMessaging();

            System.out.println("UserSocket: "+message);
            
            while ((message = reader.readLine()) != null) {
                //message = reader.readLine();
                System.out.println(message);
                FacingController.getInstance().txaConversation.appendText(message+"\n");
            }
        } catch (IOException | InterruptedException e) {e.getMessage();shutdown();}
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            /*try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!done) {
                    message = reader.readLine();
                    message = FacingController.getInstance().getMessaging();
                    writer.println(message);
                }
            } catch (Exception e) {shutdown();}*/
            try {
                while (!done) {
                    //System.out.println("hola: "+FacingController.getInstance().isEnterPressed());
                    while (!FacingController.getInstance().isEnterPressed()) {
                        Thread.sleep(10);
                    }

                    message = FacingController.getInstance().getMessaging();
                    
                    if (!message.isEmpty()) { //Improve up
                        writer.println(message);
                        System.out.println("writer: "+message);
                        //message = reader.readLine();
                        Thread.sleep(100);
                        System.out.println("Writer after: "+message);
                        //FacingController.getInstance().txaConversation.appendText(msg + "\n");
                        FacingController.getInstance().setEnterPressed(false);
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("InputHandlerClass: "+e);
                shutdown();
            }
        }
    }
    
}
