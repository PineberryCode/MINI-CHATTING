package com.project;

import java.io.IOException;

import com.project.controller.process.LogInProcess;
import com.project.database.MongoClientConnection;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Stage primaryStage;
    private LogInProcess loginProcess;
    private static MongoClientConnection mongoConnection;

    public static void main (String[] args) {
        /*mongoConnection = new MongoClientConnection();
        mongoConnection.getConnection();*/
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws IOException {
        this.primaryStage = arg0;
        arg0.initStyle(StageStyle.UNDECORATED);
        arg0.initStyle(StageStyle.TRANSPARENT);

        loginProcess = new LogInProcess();
        loginProcess.ShowFXML(arg0, "view/LogIn", 572, 431);
    }
    
}
