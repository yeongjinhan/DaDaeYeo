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
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";

    private MutableLiveData<ArrayList<Product>> ProductList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Product>> UserProductList = new MutableLiveData<>();

    private FirebaseFirestore firebaseFirestore;
    public LiveData<ArrayList<Product>> findAll() { return ProductList; }
    public LiveData<ArrayList<Product>> getUserProductList() { return UserProductList; }

    public ProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void SearchProductByName(String word){
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

    public void searchProductByID(List<String> productID){
        ArrayList<Product> result = new ArrayList<>();
        CollectionReference Ref = firebaseFirestore.collection("Products");
        Log.d("제품검색", productID.toString());
        Ref.whereIn("productID",productID)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            result.add(document.toObject(Product.class));
                        }
                        if(result != null){
                            UserProductList.setValue(result);
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
