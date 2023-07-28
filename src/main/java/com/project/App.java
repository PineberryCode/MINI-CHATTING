package com.project;

import java.io.IOException;

import com.project.controller.process.LogInProcess;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Stage primaryStage;
    private LogInProcess loginProcess;
    //IMPORTANT! You have to use Redis for the login and conversation.

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, Exception {
        primaryStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        loginProcess = new LogInProcess();
        loginProcess.ShowFXML(stage, "view/LogIn", 572, 431);
    }
    
}
