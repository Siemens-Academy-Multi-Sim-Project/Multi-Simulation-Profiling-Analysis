package com.example.multisimulationprofiling.utils;

import java.util.regex.Pattern;

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

    public static String[] splitAndTrim(String line, String delimiter){
        return trim(line.split(Pattern.quote(delimiter)));
    }
}
