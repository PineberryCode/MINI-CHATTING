package com.project.controller.services;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Handler {

    protected Socket user;
    protected BufferedReader reader;
    protected PrintWriter writer;
    protected boolean done;

}
