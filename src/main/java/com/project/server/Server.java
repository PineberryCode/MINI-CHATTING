package com.project.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ArrayList<ConnectionHandler> userConnections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public Server () {
        userConnections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(5000,0,InetAddress.getByName("127.0.0.1")); 
            pool = Executors.newCachedThreadPool();
            while (!done) {
                Socket user = server.accept();
                ConnectionHandler handler = new ConnectionHandler(user);
                userConnections.add(handler);
                pool.execute(handler);
            }
        } catch (Exception e) {shutdown();}
    }

    public void broadcast(String msg) {
        for (ConnectionHandler connectionHandler : userConnections) {
            if (connectionHandler != null) {
                connectionHandler.sendMessage(msg);
            }
        }
    }

    public void shutdown() { // Improve
        done = true;
        try {
            pool.shutdown();
            if (server != null && !server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler connectionHandler : userConnections) {
                if (connectionHandler != null) {
                    connectionHandler.shutdown();
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    public class ConnectionHandler implements Runnable {

        private Socket user;
        private BufferedReader reader;
        private PrintWriter writer;

        public ConnectionHandler (Socket user) {this.user = user;}

        @Override
        public void run() {
            try {
                writer = new PrintWriter(user.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(user.getInputStream()));
                
                System.out.println("Anonymous user connected");
                /*for (ConnectionHandler c : userConnections) {
                    System.out.println("User: "+c);
                }*/
                
                String message;

                while ((message = reader.readLine()) != null) {
                    broadcast(message);
                }

            } catch (IOException e) {e.printStackTrace();shutdown();}
        }

        public void sendMessage(String msg) {
            writer.println(msg);
        }

        public void shutdown() {
            try {
                reader.close();
                writer.close();
                if (!user.isClosed()) {
                    user.close();
                }
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
