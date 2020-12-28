package com.hanyj96.dadaeyeo;

import com.hanyj96.dadaeyeo.di.AppComponent;
import com.hanyj96.dadaeyeo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
