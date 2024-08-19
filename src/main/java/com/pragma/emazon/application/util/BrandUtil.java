package com.pragma.emazon.application.util;

public class BrandUtil {

    private BrandUtil() {
    }

    public static boolean isBrandNameTooLong(String name) {
        return name.length() > 50;
    }

    public static boolean isBrandDescriptionTooLong(String description) {
        return description.length() > 120;
    }
}
