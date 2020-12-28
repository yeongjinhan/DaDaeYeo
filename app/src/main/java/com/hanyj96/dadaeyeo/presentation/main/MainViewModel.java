package com.hanyj96.dadaeyeo.presentation.main;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;
    @Inject
    MainViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }
}
