package com.hanyj96.dadaeyeo.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityEvent {
    public static void ChangeActivity(Activity activity, Intent intent){
        activity.startActivity(intent);
        activity.finish();
    }
}
