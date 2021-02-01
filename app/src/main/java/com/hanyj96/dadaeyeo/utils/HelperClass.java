package com.hanyj96.dadaeyeo.utils;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.compose.ui.geometry.Rect;

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

    public static Rect getScrollPosition(View view){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Rect(location[0],location[1],location[0]+view.getMeasuredWidth(),location[1]+view.getMeasuredHeight());
    }
}
