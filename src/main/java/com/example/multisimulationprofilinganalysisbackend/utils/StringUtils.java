package com.example.multisimulationprofilinganalysisbackend.utils;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class StringUtils {
    public static boolean containsIgnoreCase(String str, String substr) {
        return str.toLowerCase().contains(substr.toLowerCase());
    }


    public static String trim(String in){
        StringBuilder returnStr = new StringBuilder("");
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '"') continue;
            
            returnStr.append(in.charAt(i));
        }

        return returnStr.toString().trim();
    }

    public static String[] trim(String[] arr){
        ArrayList<String> trimmedArr = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i].isEmpty() || arr[i].isBlank()) continue;
            trimmedArr.add(trim(arr[i]));
        }
        return trimmedArr.toArray(new String[0]);
    }

    public static String[] splitAndTrim(String line, String delimiter){
        return trim(line.split(Pattern.quote(delimiter)));
    }
}
