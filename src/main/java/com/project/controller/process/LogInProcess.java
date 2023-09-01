package com.project.controller.process;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.project.cryptogph.RSA;

public class LogInProcess extends Overview {

    public String DecryptData (String data) {
        String decrypting = "";
        try {
            RSA rsa = RSA.getInstance();
            //rsa.openFromDiskPrivateKey(rsa.FILE_PRIVATE);
            //rsa.openFromDiskPublicKey(RSA.path_file);
            decrypting = rsa.Decrypt(data);
        } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | 
                BadPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return decrypting;
    }

    //You have to iterate username and password from database then decrypt.

}
