package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.project.controller.FacingController;

public class UserSocket extends Handler implements Runnable {

    @Override
    public void run() {
        try {
            user = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            Thread thread = new Thread(new InputHandler());
            thread.start();

            System.out.println("UserSocket: "+message);
            
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                FacingController.getInstance().txaConversation.appendText(message+"\n");
            }
        } catch (IOException e) {e.getMessage();shutdown();}
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                while (!done) {
                    
                    while (!FacingController.getInstance().isEnterPressed()) {
                        Thread.sleep(10);
                    }

                    message = FacingController.getInstance().getMessaging();
                    
                    if (!message.isEmpty()) { //Improve up
                        writer.println(message);
                        
                        Thread.sleep(100);
                        
                        
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
