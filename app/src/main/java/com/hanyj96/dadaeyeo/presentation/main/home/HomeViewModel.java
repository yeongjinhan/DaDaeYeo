package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanyj96.dadaeyeo.data.HomeItem;
import com.hanyj96.dadaeyeo.data.Product;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private ArrayList<HomeItem> homeItems;

    @Inject
    public HomeViewModel(){
        if(homeItems == null){

        }
    }
    public ArrayList<HomeItem> getHomeItems(){
        return homeItems;
    }
}
