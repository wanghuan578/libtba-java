package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.Exception.TbaException;
import java.io.IOException;
import static com.spirit.tba.Exception.ErrorType.UNEXPECTED_EXCEPTION;

public class TbaBase64Utils {
    public static String encode(byte[] data) throws TbaException {
        return new sun.misc.BASE64Encoder().encode(data);
//        try {
//            return Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
//        }
    }

    public static byte[] decode(String content) throws TbaException {
        try {
            return new sun.misc.BASE64Decoder().decodeBuffer(content);
        } catch (IOException e) {
            throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
        }
    }
}
