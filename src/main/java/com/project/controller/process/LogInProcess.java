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

    /*public String Verificate (String data) { // data == username || password
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

    public String DecryptData (String data) {
        String decrypting = "";
        try {
            RSA rsa = new RSA();
        rsa.openFromDiskPrivateKey(rsa.FILE_PRIVATE);
        rsa.openFromDiskPublicKey(rsa.FILE_PUBLIC);
        decrypting = rsa.Decrypt(data);
        } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | 
                BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
                    e.printStackTrace();
        }

        return decrypting;
    }

    //You have to iterate username and password from database then decrypt.

}
