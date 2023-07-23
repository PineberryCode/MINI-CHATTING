package com.project.controller;
import java.net.URL;

import com.project.App;
import com.project.controller.process.RegisterProcess;
import com.project.routes.Route;

import java.awt.Desktop;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
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
        stage.close();

        Parent parent = Route.loadFXML("view/Register");
        Scene scene = new Scene(parent,631,431);
        stage.setScene(scene);
        stage.setResizable(false);
        registerProcess = new RegisterProcess();
        registerProcess.movile(scene, stage);
        stage.show();
    }

    private static void openWebPage (String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
