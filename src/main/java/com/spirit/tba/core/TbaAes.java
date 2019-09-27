package com.spirit.tba.core;

import com.spirit.tba.Exception.TbaException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import static com.spirit.tba.Exception.ErrorType.UNEXPECTED_EXCEPTION;
import static java.security.spec.PSSParameterSpec.DEFAULT;


public class TbaAes {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "ISO8859-1";
    private static final Integer SECRET_KEY_LENGTH = 128;
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String content, String key) throws TbaException {

//        if (StringUtils.isEmpty(content)) {
//            throw new TbaException(INPUT_PARAMETER_EMPTY);
//        }
//
//        if (StringUtils.isEmpty(key)) {
//            throw new TbaException(INPUT_PARAMETER_EMPTY);
//        }

        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(CHAR_SET);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            byte[] encryptByte = cipher.doFinal(byteContent);
            for (int i = 0; i < encryptByte.length; i++) {
                System.out.println(encryptByte[i]);
            }
            System.out.println("encryptByte len: " + encryptByte.length);
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

    public static String decrypt(String encryptContent, String key) throws TbaException {
//        if (StringUtils.isEmpty(encryptContent)) {
//            throw new TbaException(INPUT_PARAMETER_EMPTY);
//        }
//
//        if (StringUtils.isEmpty(key)) {
//            throw new TbaException(INPUT_PARAMETER_EMPTY);
//        }

        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            byte[] b64 = Base64.decodeBase64(encryptContent);
            for (int i = 0; i < b64.length; i++) {
                System.out.println(b64[i]);
            }
            System.out.println("decrypt byte len: " + b64.length);
            byte[] result = cipher.doFinal(b64);
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

    public static String decryptEx(String encryptContent, String key) throws TbaException {

        try {
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            byte[] b64 = Base64.decodeBase64(encryptContent);
            byte[] result = cipher.doFinal(b64);
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

    private static SecretKeySpec getSecretKey(final String key) throws NoSuchAlgorithmException {
        System.out.println("AES key: " + key);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(SECRET_KEY_LENGTH, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        //System.out.println("secretKey:" + secretKey.toString());
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }

    public static String encode(String content, String encodeRules) throws TbaException {
        try {
            byte[] rawKey = encodeRules.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IllegalBlockSizeException
                | BadPaddingException
                e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

    public static String decode(String content, String encodeRules) throws TbaException {
        try {
            byte[] rawKey = encodeRules.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(Base64.decodeBase64(content));
            return new String(decrypted, "UTF-8");
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IOException
                | IllegalBlockSizeException
                | BadPaddingException
                e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

}
