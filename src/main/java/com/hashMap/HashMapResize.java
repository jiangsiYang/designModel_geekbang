package com.hashMap;

import java.util.HashMap;
import java.util.Map;

public class HashMapResize {
    public static void main(String args[]) {
        Map<Integer, Integer> map = new HashMap(2);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
    }
}
