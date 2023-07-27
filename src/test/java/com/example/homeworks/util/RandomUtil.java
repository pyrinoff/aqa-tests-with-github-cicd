package com.example.homeworks.util;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public interface RandomUtil {

    static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static int getRandomIndex(Object[] array) {
        if(array == null || array.length == 0) return -1;
        return getRandomInt(0, array.length);
    }

    static <T> T getRandomElement(T[] array) {
        if(array == null || array.length == 0) return null;
        return array[getRandomIndex(array)];
    }

    static <K,V> K getRandomKey(Map<K, V> map) {
        if(map == null || map.isEmpty()) return null;
        return (K) map.keySet().toArray()[getRandomInt(0, map.size())];
    }

}
