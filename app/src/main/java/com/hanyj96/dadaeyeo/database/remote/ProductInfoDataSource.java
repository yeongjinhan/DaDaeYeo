package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.products.ProductInfo;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class ProductInfoDataSource {
    private static final String TAG = ProductInfoDataSource.class.getName();

    private FirebaseFirestore firebaseFirestore;
    private MutableLiveData<ProductInfo> productInfo = new MutableLiveData<>();

    public ProductInfoDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void getProductInfoByID(String productID){
        CollectionReference Ref = firebaseFirestore.collection("ProductInfo");
        Ref.whereEqualTo("productID", productID)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(document == null){
                                Log.d(TAG, "document is Null");
                            }else{
                                productInfo.setValue(document.toObject(ProductInfo.class));
                            }

                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
