package com.spirit.tba.core;

import com.spirit.tba.Exception.TbaException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.spirit.tba.Exception.ErrorType.INPUT_PARAMETER_EMPTY;
import static com.spirit.tba.Exception.ErrorType.UNEXPECTED_EXCEPTION;


public class TbaAes {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "ISO8859-1";
    private static final Integer SECRET_KEY_LENGTH = 128;
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String content, String key) throws TbaException {
        if (StringUtils.isAnyEmpty(content, key)) {
            throw new TbaException(INPUT_PARAMETER_EMPTY);
        }
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(CHAR_SET);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            byte[] encryptByte = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION);
        }
    }

    public static String decrypt(String encryptContent, String key) throws TbaException {
        if (StringUtils.isAnyEmpty(encryptContent, key)) {
            throw new TbaException(INPUT_PARAMETER_EMPTY);
        }
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION);
        }
    }

    private static SecretKeySpec getSecretKey(final String key) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(SECRET_KEY_LENGTH, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }
}