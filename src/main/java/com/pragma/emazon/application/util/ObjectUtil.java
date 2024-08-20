package com.pragma.emazon.application.util;

public class ObjectUtil {

    public static String getClassName(Object object){
        if(object == null){
            return "null";
        }

        return object.getClass().getSimpleName();
    }
}
