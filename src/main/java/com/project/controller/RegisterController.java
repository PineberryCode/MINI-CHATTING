package com.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import com.project.controller.process.RegisterProcess;
import com.project.database.crud.User_CRUD;
import com.project.model.User;

public class RegisterController {

    private User user;
    private User_CRUD user_CRUD;
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
        registerProcess = new RegisterProcess();
        User user =  registerProcess.ReadData(tfE_mail, tfUsername, pfPassword);

        user_CRUD = new User_CRUD();
        user_CRUD.registration(user);
    }

}