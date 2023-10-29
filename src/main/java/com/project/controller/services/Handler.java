package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Handler {

    protected Socket user;
    protected BufferedReader reader;
    protected PrintWriter writer;
    protected boolean done;
    protected String message;

    protected void shutdown() {
        done = true;
        try {
            reader.close();
            writer.close();
            if (!user.isClosed()) {
                user.close();
            }
        } catch (IOException e) {}
    }

}
