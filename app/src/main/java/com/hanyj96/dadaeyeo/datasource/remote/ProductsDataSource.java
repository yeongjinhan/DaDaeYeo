package com.hanyj96.dadaeyeo.datasource.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.data.model.ProductDocument;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("ConstantConditions")
public class ProductsDataSource {
    private static final String TAG = "ProductsDataSource";

    private MutableLiveData<ArrayList<Product>> ProductList = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<ArrayList<Product>> findAll() { return ProductList; }

    public ProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        getProductData();
    }

    public void SearchProduct(String word){
        Log.d(TAG,"Search Start");
        //CollectionReference productCollection = firebaseFirestore.collection("Products");
        CollectionReference Ref = firebaseFirestore.collection("ProductList");
        Ref.document("ElectronicProducts").collection("Tablet")//.whereIn("ProductID", Arrays.asList("10000002", "10000001"))
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    private void getProductData(){
        /*DocumentReference doc = firebaseFirestore.collection("Products").document("1");
        doc.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot snapshot = task.getResult();
                if(snapshot.exists()){
                    ArrayList<Product> products = snapshot.toObject(ProductDocument.class).AllProduct;
                    Log.d(TAG,"size : " + products.size());
                    ProductList.setValue(products);
                }
            }
        });*/
    }
}
