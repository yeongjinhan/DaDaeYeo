package com.hanyj96.dadaeyeo;


import com.hanyj96.dadaeyeo.di.DaggerAppComponent;

import org.matomo.sdk.Matomo;
import org.matomo.sdk.QueryParams;
import org.matomo.sdk.TrackMe;
import org.matomo.sdk.Tracker;
import org.matomo.sdk.TrackerBuilder;
import org.matomo.sdk.extra.TrackHelper;

import java.util.Calendar;
import java.util.Date;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class BaseApplication extends DaggerApplication {
    private Tracker tracker;
    private TrackMe trackMe;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public synchronized Tracker getTracker() {
        if (tracker == null){
            tracker = TrackerBuilder.createDefault("http://192.168.20.51:81/obzenTagWorks", 4).build(Matomo.getInstance(this));
            tracker.setDispatchInterval(0);
        }
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
                .dimension(1, Long.toString(calendar.get(calendar.MILLISECOND)));
    }
}
