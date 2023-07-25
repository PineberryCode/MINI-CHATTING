package com.project;

import java.io.IOException;

import com.project.controller.LogInController;
import com.project.controller.process.LogInProcess;
import com.project.routes.Route;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Stage primaryStage;
    private LogInProcess loginProcess;
    private LogInController logInController;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws IOException {
        this.primaryStage = arg0;
        arg0.initStyle(StageStyle.UNDECORATED);
        arg0.initStyle(StageStyle.TRANSPARENT);

        loginProcess = new LogInProcess();
        loginProcess.ShowFXML(arg0, "view/LogIn", 572, 431);
        /*Route.scene = new Scene(Route.loadFXML("view/LogIn"), 572,431);
        Route.scene.setFill(Color.TRANSPARENT);
        arg0.setResizable(false);
        arg0.setScene(Route.scene);
        arg0.show();*/
        //logInController = new LogInController(arg0);
    }

    //fx:controller="com.project.controller.LogInController"
    
}
