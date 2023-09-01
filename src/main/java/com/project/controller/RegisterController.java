package com.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        User user;
        try {
            user = registerProcess.ReadData(tfE_mail, tfUsername, pfPassword);
            
            user_CRUD = new User_CRUD();
            user_CRUD.registration(user);

            //Below: Optimization then
            tfE_mail.setText("");
            tfUsername.setText("");
            pfPassword.setText("");
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | InvalidKeySpecException | IOException e) {
            e.printStackTrace();
        }
    }

}