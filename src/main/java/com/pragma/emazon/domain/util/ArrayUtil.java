package com.pragma.emazon.domain.util;

public class ArrayUtil {

    private ArrayUtil() {
    }

    public static boolean assessEqualContent(Long [] array){
        Long evaluated;

        for (int i = 0; i < array.length; i++) {
            evaluated = array[i];
            for (int j = i + 1; j < array.length - i; j++) {
                if(evaluated.equals(array[j])){
                    return true;
                }
            }
        }

        return false;
    }
}
