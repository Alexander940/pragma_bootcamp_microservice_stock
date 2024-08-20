package com.pragma.emazon.application.util;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean assessHigherLength(String string, int length) {
        return string.length() > length;
    }
}
