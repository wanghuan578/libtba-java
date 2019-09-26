package com.spirit.tba.core;

import org.apache.commons.codec.digest.DigestUtils;

public class TbaMd5 {
    public static String md5Hex(String content) {
        return DigestUtils.md5Hex(content);
    }
}
