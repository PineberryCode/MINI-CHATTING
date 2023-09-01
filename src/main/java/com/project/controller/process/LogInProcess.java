package com.project.controller.process;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.project.cryptogph.RSA;

public class LogInProcess extends Overview {

    public String DecryptData (String data) {
        String decrypting = "";
        try {
            RSA rsa = RSA.getInstance();

            decrypting = rsa.Decrypt(data);
        } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | 
                BadPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return decrypting;
    }

}
