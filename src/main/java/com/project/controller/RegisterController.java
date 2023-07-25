package com.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import com.project.controller.process.RegisterProcess;

public class RegisterController {

    private RegisterProcess registerProcess;

    /*
     * Components
     */
    @FXML
    TextField tfE_mail;
    @FXML
    TextField tfUsername;
    @FXML
    PasswordField pfPassword;

    @FXML
    private void RegisterUser () {
        String email = tfE_mail.getText();
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        System.out.println("Email: "+email+"\n"+
                            "Username: "+username+"\n"+
                            "Password: "+password);
    }

}