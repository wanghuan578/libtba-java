package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */

public class TbaBase64Utils {
    public static String encode(byte[] data) throws Exception {
        return new sun.misc.BASE64Encoder().encode(data);
    }

    public static byte[] decode(String content) throws Exception {
        return new sun.misc.BASE64Decoder().decodeBuffer(content);
    }
}
