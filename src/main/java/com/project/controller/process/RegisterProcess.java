package com.project.controller.process;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.project.cryptogph.RSA;
import com.project.model.User;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterProcess extends Overview {

    private User user;

    /*private String EncryptData (String data) {
        RSA keygph = RSA.getInstance();
        byte[] dataEncrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keygph.getPublicKey());
            dataEncrypted = cipher.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(dataEncrypted);
    }*/

    public User ReadData (TextField e_mail, TextField username, 
                            PasswordField password) 
                            throws InvalidKeyException, NoSuchAlgorithmException, 
                            NoSuchPaddingException, IllegalBlockSizeException, 
                            BadPaddingException, IOException {
        RSA rsa = new RSA();
        rsa.genKeyPair(1024);
        
        user = new User();
        user.setE_mail(e_mail.getText());
        user.setUsername(username.getText());
        user.setPassword(rsa.Encrypt(password.getText()).toString());

        rsa.saveToDiskPrivateKey(rsa.FILE_PRIVATE);
        rsa.saveToDiskPublicKey(rsa.FILE_PUBLIC);

        return user;
    }

}
