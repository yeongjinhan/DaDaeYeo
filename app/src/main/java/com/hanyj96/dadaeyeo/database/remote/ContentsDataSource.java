package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class ContentsDataSource {
    private static final String TAG = "ContentsDataSource";

    private MutableLiveData<List<String>> eventIDList = new MutableLiveData<>();
    private FirebaseFirestore firebaseFirestore;

    public ContentsDataSource(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<List<String>> getEventIDList() {
        return eventIDList;
    }

    public void getAllEventIDList(){
        Log.d(TAG, "이벤트 아이디 불러오기");
        List<String> result = new ArrayList<>();
        CollectionReference Ref = firebaseFirestore.collection("Contents");
        Ref.whereEqualTo("type",1)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.get("id").toString());
                            result.add(document.get("id").toString());
                        }
                        if(result != null){
                            eventIDList.setValue(result);
                        }
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
