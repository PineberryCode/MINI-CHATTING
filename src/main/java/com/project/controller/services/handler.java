package com.project.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class handler {
    protected Socket user;
    protected BufferedReader reader;
    protected PrintWriter out;
    protected boolean done;

    protected void shutdown () {
        done = true;
        try {
            reader.close();
            out.close();
            if (!user.isClosed()) {
                user.close();
            }
        } catch (IOException e) {e.getMessage();}
    }
}
