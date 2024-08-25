package com.pragma.emazon.infrastructure.util;

import java.util.List;

import static java.lang.reflect.Array.newInstance;

public class ListUtil {

    private ListUtil() {
    }

    public static <T> T[] toArray(List<T> list) {
        T[] array = (T[]) newInstance(list.get(0).getClass(), list.size());
        return list.toArray(array);
    }
}
