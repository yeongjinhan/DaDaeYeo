package com.hanyj96.dadaeyeo.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {
    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // MM-dd 형식의 현재 날짜 반환
    public static String getCurrentDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }
}
