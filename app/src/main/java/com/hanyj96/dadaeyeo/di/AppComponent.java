package com.hanyj96.dadaeyeo.di;


import android.app.Application;

import com.hanyj96.dadaeyeo.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppViewModelModule.class,
                FragmentBuilderModule.class,
                DatabaseModule.class,
                AppMoule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
