package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.products.Product;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";

    private MutableLiveData<ArrayList<Product>> ProductList = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<ArrayList<Product>> findAll() { return ProductList; }

    public ProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void SearchProduct(String word){
        ArrayList<Product> result = new ArrayList<>();
        CollectionReference Ref = firebaseFirestore.collection("Products");
        Ref.orderBy("productName").startAt(word).endAt(word + '\uf8ff')
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            result.add(document.toObject(Product.class));
                        }
                        if(result != null){
                            ProductList.setValue(result);
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
