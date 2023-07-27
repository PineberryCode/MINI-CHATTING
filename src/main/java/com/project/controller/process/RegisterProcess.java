package com.project.controller.process;

import com.project.model.User;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterProcess extends Overview {

    private User user;

    public User ReadData (TextField e_mail, TextField username, 
                            PasswordField password) {
        user = new User();
        user.setE_mail(e_mail.getText());
        user.setUsername(username.getText());
        user.setPassword(password.getText());

        return user;
    }

}
