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
import javafx.event.ActionEvent;

public class LogInController {
        
    private RegisterProcess registerProcess;

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
        stage.close();
        
        registerProcess = new RegisterProcess();
        registerProcess.ShowFXML(stage, "view/Register", 356, 340);
    }

}
