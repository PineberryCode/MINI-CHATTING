package com.project.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.controller.services.connectionHandler;
import com.project.controller.services.handler;

public class myServer extends handler implements Runnable {

    private ArrayList<connectionHandler> userConnection;
    private ServerSocket server;
    private ExecutorService pool;
    private int PORT = 5000;

    public myServer () {
        userConnection = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(PORT,0,InetAddress.getByName("192.168.100.9"));
            pool = Executors.newCachedThreadPool();

            while (!done) {
                user = server.accept();
                connectionHandler handler = new connectionHandler(user);
                userConnection.add(handler); // limit 2 users
                pool.execute(handler);
            }

        } catch (Exception e) {e.getMessage();shutdown();}
    }
    
    //Please remember turn on the server
}
