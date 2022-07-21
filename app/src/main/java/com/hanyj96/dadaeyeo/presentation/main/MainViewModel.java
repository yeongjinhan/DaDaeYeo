package com.hanyj96.dadaeyeo.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanyj96.dadaeyeo.data.model.products.Product;

import org.matomo.sdk.Matomo;
import org.matomo.sdk.Tracker;
import org.matomo.sdk.TrackerBuilder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;

public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;
    private MutableLiveData<Integer> currentFragment = new MutableLiveData<>();

    @Inject
    MainViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    public void InitData(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Products");
        Product p1 = new Product(6,1,"60000000","갤럭시워치3",425700);
        Product p2 = new Product(6,1,"60000001","갤럭시워치 액티브2",223270);
        collectionReference.add(p1);
        collectionReference.add(p2);
    }

    public void setCurrentFragment(int currentFragmentID){
        currentFragment.setValue(currentFragmentID);
    }

    public LiveData<Integer> getCurrentFragment(){
        return currentFragment;
    }
}
