package com.pragma.emazon.application.util;

public class CategoryUtil {

    private CategoryUtil() {
    }

    public static boolean isCategoryNameTooLong(String name) {
        return name.length() > 50;
    }

    public static boolean isCategoryDescriptionTooLong(String description) {
        return description.length() > 90;
    }
}
