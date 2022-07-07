package com.hanyj96.dadaeyeo;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.hanyj96.dadaeyeo.di.DaggerAppComponent;

import org.matomo.sdk.Matomo;
import org.matomo.sdk.QueryParams;
import org.matomo.sdk.TrackMe;
import org.matomo.sdk.Tracker;
import org.matomo.sdk.TrackerBuilder;
import org.matomo.sdk.extra.CustomDimension;
import org.matomo.sdk.extra.DownloadTracker;
import org.matomo.sdk.extra.TrackHelper;
import org.matomo.sdk.tools.ActivityHelper;

import java.util.Calendar;
import java.util.Date;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class BaseApplication extends DaggerApplication {
    private Tracker tracker;
    private TrackMe trackMe;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Calendar calendar;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        if (tracker == null){
            tracker = TrackerBuilder.createDefault("http://192.168.20.51:81/matomo.php", 4).build(Matomo.getInstance(this));
            tracker.setDispatchInterval(0);
        }

        if(sharedPreferences == null){
            sharedPreferences = getSharedPreferences("tracker", MODE_PRIVATE);
        }

        if(editor == null) {
            editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public synchronized Tracker getTracker() {
        return tracker;
    }
    
    public TrackHelper.Dimension getBaseTrack(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        trackMe = new TrackMe();
        trackMe.set(QueryParams.HOURS, Long.toString(calendar.get(calendar.HOUR)));
        trackMe.set(QueryParams.MINUTES, Long.toString(calendar.get(calendar.MINUTE)));
        trackMe.set(QueryParams.SECONDS, Long.toString(calendar.get(calendar.SECOND)));

        return TrackHelper.track(trackMe)
                .dimension(1, Long.toString(calendar.get(calendar.MILLISECOND)))
                .dimension(2, getString("OBZ_CAMP_ID"))
                .dimension(3, getString("OBZ_CUST_ID"));
    }

    public void setCustomDimension(@NonNull String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void setString(@NonNull String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(@NonNull String key){
        return sharedPreferences.getString("key","");
    }

    public String getSystemTimeHours(){
        return Long.toString(calendar.get(calendar.HOUR));
    }

    public String getSystemTimeMinutes(){
        return Long.toString(calendar.get(calendar.MINUTE));
    }

    public String getSystemTimeSeconds(){
        return Long.toString(calendar.get(calendar.SECOND));
    }

    public String getSystemTimeMilliSeconds(){
        return Long.toString(calendar.get(calendar.MILLISECOND));
    }
}
