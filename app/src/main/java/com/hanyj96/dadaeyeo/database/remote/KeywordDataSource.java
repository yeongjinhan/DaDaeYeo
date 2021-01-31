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
public class KeywordDataSource {
    private static final String TAG = "KeywordDataSource";

    private MutableLiveData<List<Keyword>> Keywords = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public LiveData<List<Keyword>> getAutoKeywords() {
        return Keywords;
    }

    public KeywordDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void findAutoKeywords(String keyword){
        Log.d(TAG,"키워드 찾기실행");
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
                            Keywords.setValue(result);
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
