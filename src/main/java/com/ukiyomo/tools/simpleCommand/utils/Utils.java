package com.ukiyomo.tools.simpleCommand.utils;


public class Utils {

    public static String[] implodeArray(String value) {
        if(value.equals("")) return new String[]{};
        return value.split(" ");
    }

    public static String explodeArray(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String s : array) {
            result.append(s).append(" ");
        }

        return result.toString().trim();
    }

    public static String[] deleteFirst(String[] array) {
        if(array.length == 0) return array;
        String[] newS = new String[array.length - 1];
        System.arraycopy(array, 1, newS, 0, array.length - 1);
        return newS;
    }

    public static String[] deleteLast(String[] array) {
        if(array.length == 0) return array;
        String[] newS = new String[array.length - 1];
        System.arraycopy(array, 0, newS, 0, array.length - 1);
        return newS;
    }

}
