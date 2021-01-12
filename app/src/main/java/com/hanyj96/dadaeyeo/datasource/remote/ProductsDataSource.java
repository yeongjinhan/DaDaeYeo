package com.hanyj96.dadaeyeo.datasource.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";
    private MutableLiveData<ArrayList<HomeItem>> homeItemMutableLiveData = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<ArrayList<HomeItem>> findAll() { return homeItemMutableLiveData; }

    public ProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        getProductData();
    }

    private void getProductData(){
        DocumentReference doc = firebaseFirestore.collection("HomeItem").document("HomeItemList");
        doc.addSnapshotListener((snapshot, e)->{
            if(e != null){
                Log.w(TAG, "Listen Failed.",e);
                return;
            }
            if(snapshot != null && snapshot.exists()){
                // 수정필요

            }else{
                Log.d(TAG, "Current data : null");
            }
        });
    }
}
