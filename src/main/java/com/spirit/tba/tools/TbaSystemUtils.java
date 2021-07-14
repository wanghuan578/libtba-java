package com.spirit.tba.tools;

public class TbaSystemUtils {

    TbaSystemUtils() {

    }
    public static boolean win32() {
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().startsWith("win")){
            return true;
        }
        return false;
    }
}
