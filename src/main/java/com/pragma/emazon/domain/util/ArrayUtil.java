package com.pragma.emazon.domain.util;

import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {

    private ArrayUtil() {
    }

    public static boolean hasDuplicates(Long [] array){
        Set<Long> seen = new HashSet<>();
        for (Long value : array) {
            if (!seen.add(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDuplicates(String [] array){
        Set<String> seen = new HashSet<>();
        for (String value : array) {
            if (!seen.add(value)) {
                return true;
            }
        }
        return false;
    }
}
