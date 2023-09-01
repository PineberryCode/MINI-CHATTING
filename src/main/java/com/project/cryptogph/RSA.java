package com.project.cryptogph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.project.controller.api.Getting;

public class RSA {

    private static RSA instance;
    
    private static String name_file = "rsa".concat(".pub");
    private static String __dirname__ = System.getProperty("user.dir");
    public static String path_file = __dirname__.concat("/src/main/java/com/project/static/"+name_file);

    private PublicKey KEY_PUBLIC;
    private PrivateKey KEY_PRIVATE;
    private String priv = Getting.PRIVATE_KEY();
    private String pub = Getting.PUBLIC_KEY();

    private RSA () {
        setUpPrivateKey(priv);
        setUpPublicKey(pub);
    }

    public static RSA getInstance() {
        if (instance == null) {
            instance = new RSA();
        }
        return instance;
    }

    private String bytesToString(byte[] byt) {
        byte[] secondByt = new byte[byt.length+1];
        secondByt[0] = 1;
        System.arraycopy(byt, 0, secondByt, 1, byt.length);
        return new BigInteger(secondByt).toString(36);
    }

    private byte[] stringToBytes (String str) {
        byte[] secondByt = new BigInteger(str,36).toByteArray();
        return Arrays.copyOfRange(secondByt, 1, secondByt.length);
    }

    private void setUpPrivateKey (String key) {
        try {
            byte[] privateKeyEncoded = stringToBytes(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKeyEncoded);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8);
            this.KEY_PRIVATE = privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private void setUpPublicKey (String key) {
        try {
            byte[] publicKeyEncoded = stringToBytes(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKeyEncoded);
            PublicKey publicKey = keyFactory.generatePublic(x509);
            this.KEY_PUBLIC = publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public String getPrivateKeyString () {
        PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(this.KEY_PRIVATE.getEncoded());
        return bytesToString(pkcs8.getEncoded());
    }

    public String getPublicKeyString () {
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(this.KEY_PUBLIC.getEncoded());
        return bytesToString(x509.getEncoded());
    }

    public String Encrypt (String str) throws NoSuchAlgorithmException, NoSuchPaddingException, 
                                        InvalidKeyException, IllegalBlockSizeException, 
                                        BadPaddingException {
        byte[] bytesEncrypted;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.KEY_PUBLIC);
        bytesEncrypted = cipher.doFinal(str.getBytes());
        
        return bytesToString(bytesEncrypted);
    }

    public String Decrypt (String str) throws NoSuchAlgorithmException, NoSuchPaddingException, 
                                        InvalidKeyException, IllegalBlockSizeException, 
                                        BadPaddingException {
        byte[] bytesDecrypted;

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, this.KEY_PRIVATE);
        bytesDecrypted = cipher.doFinal(stringToBytes(str));

        return new String (bytesDecrypted);
    }

}
