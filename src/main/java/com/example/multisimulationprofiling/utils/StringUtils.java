package com.example.multisimulationprofiling.utils;

public class StringUtils {
    public static boolean containsIgnoreCase(String str, String substr) {
        return str.toLowerCase().contains(substr.toLowerCase());
    }

    public static String[] trim(String[] arr){
        String[] trimmedArr = new String[arr.length];
        for(int i = 0; i < arr.length; i++){
            trimmedArr[i] = arr[i].trim();
        }
        return trimmedArr;
    }
}
