package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.exception.TbaException;
import java.security.MessageDigest;
import static com.spirit.tba.exception.ErrorType.UNEXPECTED_EXCEPTION;

public class TbaMd5Utils {
    private static String _slat = "*#spirit@#";
    public static void setSlat(String slat) {
        _slat = slat;
    }
    public static String md5Hex(String dataStr) throws TbaException {
        try {
            dataStr = dataStr + _slat;
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
