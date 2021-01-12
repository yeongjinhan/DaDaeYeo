package com.hanyj96.dadaeyeo.datasource.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanyj96.dadaeyeo.BuildConfig;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.data.model.ProductDocument;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";

    private MutableLiveData<ArrayList<Product>> ProductList = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<ArrayList<Product>> findAll() { return ProductList; }

    public ProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        getProductData();
    }

    private void getProductData(){
        DocumentReference doc = firebaseFirestore.collection("Products").document("1");
        doc.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot snapshot = task.getResult();
                if(snapshot.exists()){
                    ArrayList<Product> products = snapshot.toObject(ProductDocument.class).AllProduct;
                    Log.d(TAG,"size : " + products.size());
                    ProductList.setValue(products);
                }
            }
        });
    }
}
