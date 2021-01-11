package com.hanyj96.dadaeyeo.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hanyj96.dadaeyeo.presentation.main.MainViewModel;
import com.hanyj96.dadaeyeo.presentation.main.home.HomeViewModel;
import com.hanyj96.dadaeyeo.presentation.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.multibindings.IntoMap;

@Module
@SuppressWarnings("unused")
abstract class AppViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindAppViewModelFactory(AppViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel provideMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel provideSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel provideHomeViewModel(HomeViewModel viewModel);
}
