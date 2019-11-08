package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.Exception.TbaException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static com.spirit.tba.Exception.ErrorType.UNEXPECTED_EXCEPTION;

public class TbaAesUtils {

//    private static final String KEY_ALGORITHM = "AES";
//    private static final String CHAR_SET = "ISO8859-1";
//    private static final Integer SECRET_KEY_LENGTH = 128;
//    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encode(String content, String key) throws TbaException {
        try {
            String checksum = TbaMd5Utils.md5Hex(key);
            String tmp = checksum.substring(0, 16);
            byte[] rawKey = tmp.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            return TbaBase64Utils.encode(encrypted);
            //return Base64.encodeBase64String(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }

    public static String decode(String content, String key) throws TbaException {
        try {
            String checksum = TbaMd5Utils.md5Hex(key);
            String tmp = checksum.substring(0, 16);
            byte[] rawKey = tmp.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(TbaBase64Utils.decode(content));
            //byte[] decrypted = cipher.doFinal(Base64.decodeBase64(content));
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
