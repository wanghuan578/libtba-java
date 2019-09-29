package com.spirit.tba.core;

import com.spirit.tba.Exception.TbaException;
import java.security.MessageDigest;
import static com.spirit.tba.Exception.ErrorType.UNEXPECTED_EXCEPTION;

public class TbaMd5 {
    private static final String slat = "*#spirit@#";
    public static String md5Hex(String dataStr) throws TbaException {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }


}
