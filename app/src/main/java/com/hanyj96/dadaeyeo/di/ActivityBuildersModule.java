package com.hanyj96.dadaeyeo.di;

import com.hanyj96.dadaeyeo.presentation.main.MainAcitivity;
import com.hanyj96.dadaeyeo.presentation.splash.SplashAcitivty;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/* 액티비티 빌더 모듈 */
@Module
@SuppressWarnings("unused")
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract SplashAcitivty contributeSplashActivity();

    @ContributesAndroidInjector
    abstract MainAcitivity contributeMainActivity();
}

