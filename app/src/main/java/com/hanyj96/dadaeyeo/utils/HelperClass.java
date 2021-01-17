package com.hanyj96.dadaeyeo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {

    // MM-dd 형식의 현재 날짜 반환
    public static String getCurrentDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        return dateFormat.format(date);
    }
}
