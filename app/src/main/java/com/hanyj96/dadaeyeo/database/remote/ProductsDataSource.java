package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";

    private MutableLiveData<ArrayList<Product>> ProductList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Keyword>> autoKeywords = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<ArrayList<Product>> findAll() { return ProductList; }

    public LiveData<ArrayList<Keyword>> findAutoKeywords() {
        return autoKeywords;
    }

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

    public void getAutoKeywords(String keyword){
        ArrayList<Keyword> result = new ArrayList<>();
        CollectionReference Ref = firebaseFirestore.collection("Products");
        Ref.orderBy("productName").startAt(keyword).endAt(keyword + '\uf8ff')
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.get("productName").toString());
                            result.add(new Keyword(document.get("productName").toString(),true,""));
                        }
                        if(result != null){
                            autoKeywords.setValue(result);
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
