package com.project.controller;
import com.project.App;
import com.project.controller.process.RegisterProcess;
import com.project.routes.Route;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

public class LogInController {
        
    public RegisterProcess registerProcess;

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;

    @FXML
    private void bLogIn (ActionEvent event) throws IOException {
        Stage stage = App.primaryStage;
        stage.close();
        
        Parent parent = Route.loadFXML("view/Facing");
        Scene scene = new Scene(parent,631,431);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void linkRegister () throws IOException {
        Stage stage = App.primaryStage;
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.close();

        Parent parent = Route.loadFXML("view/Register");
        Scene scene = new Scene(parent,356,340);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        
        registerProcess = new RegisterProcess();
        registerProcess.moving(scene, stage);
        stage.show();
    }

}
