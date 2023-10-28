package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.project.controller.FacingController;

public class UserSocket extends Handler implements Runnable {

    private String message;

    @Override
    public void run() {
        try {
            user = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(user.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(user.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread thread = new Thread(inputHandler);
            thread.start();
            while (( message = reader.readLine()) != null) {
                System.out.println(message);
                FacingController.getInstance().txaConversation.appendText(message+"\n");
            }
        } catch (IOException e) {e.getMessage();shutdown();}
    }

    public void shutdown() { //isShutdown
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
            /*try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!done) {
                    message = reader.readLine();
                    message = FacingController.getInstance().getMessaging();
                    writer.println(message);
                }
            } catch (Exception e) {shutdown();}*/
            try {
                while (!done) {
                    System.out.println("hola: "+FacingController.getInstance().isEnterPressed());
                    while (!FacingController.getInstance().isEnterPressed()) {
                        Thread.sleep(10);
                    }

                    String msg = FacingController.getInstance().getMessaging();

                    if (!msg.isEmpty()) {
                        writer.println(msg);
                        FacingController.getInstance().txaConversation.appendText(msg + "\n");
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
