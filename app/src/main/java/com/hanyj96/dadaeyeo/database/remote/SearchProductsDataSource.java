package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class SearchProductsDataSource {
    private static final String TAG = "SearchProductsData";

    private MutableLiveData<ArrayList<Product>> UserProductList = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;
    public LiveData<ArrayList<Product>> getUserProductList() { return UserProductList; }
    public SearchProductsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
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
