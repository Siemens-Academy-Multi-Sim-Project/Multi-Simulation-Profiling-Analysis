package com.example.multisimulationprofilinganalysisbackend.utils;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class StringUtils {
    public static boolean containsIgnoreCase(String str, String substr) {
        return str.toLowerCase().contains(substr.toLowerCase());
    }

    private static Boolean isIgnored(char c, boolean ignoreWhiteSpace){ 
        return 
        (Character.isWhitespace(c) && ignoreWhiteSpace)
            || c == '"';
    }

    public static String trim(String in, boolean ignoreWhiteSpace){
        StringBuilder returnStr = new StringBuilder("");
        for(int i = 0; i < in.length(); i++){
            if(isIgnored(in.charAt(i), ignoreWhiteSpace)) continue;
            
            returnStr.append(in.charAt(i));
        }

        return returnStr.toString();
    }

    public static String[] trim(String[] arr, boolean ignoreWhiteSpace){
        ArrayList<String> trimmedArr = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i].isEmpty() || arr[i].isBlank()) continue;
            trimmedArr.add(trim(arr[i], ignoreWhiteSpace));
        }
        return trimmedArr.toArray(new String[0]);
    }

    public static String[] splitAndTrim(String line, String delimiter, boolean ignoreWhiteSpace){
        return trim(line.split(Pattern.quote(delimiter)), ignoreWhiteSpace);
    }
}
