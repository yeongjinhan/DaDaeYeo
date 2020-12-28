package com.hanyj96.dadaeyeo.presentation.splash;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SplashViewModel extends ViewModel {
    private SplashRepository splashRepository;
    @Inject
    SplashViewModel(SplashRepository splashRepository){
        this.splashRepository = splashRepository;
    }
}
