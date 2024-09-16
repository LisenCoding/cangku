package com.utils;

public class StringUtil {

    public  static boolean isEmpty(String s){
        if(s==null || s.equals("") || s.equals("null")){
            return true;
        }
        return false;
    }

    public  static boolean isNotEmpty(String s){
        return !StringUtil.isEmpty(s);
    }
}
