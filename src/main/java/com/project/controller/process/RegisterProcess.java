package com.project.controller.process;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.project.cryptogph.RSA;
import com.project.model.User;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterProcess extends Overview {

    private User user;

    public User ReadData (TextField e_mail, TextField username, 
                            PasswordField password) 
                            throws InvalidKeyException, NoSuchAlgorithmException, 
                            NoSuchPaddingException, IllegalBlockSizeException, 
                            BadPaddingException, IOException, InvalidKeySpecException {
        RSA rsa = RSA.getInstance();
        
        user = new User();
        user.setE_mail(e_mail.getText());
        user.setUsername(username.getText());
        user.setPassword(rsa.Encrypt(password.getText()).toString());

        return user;
    }

}
