package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hanyj96.dadaeyeo.data.model.contents.HomeItem;
import java.util.ArrayList;
import java.util.List;

import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_FILED;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_PER_PAGE;


@SuppressWarnings("ConstantConditions")
public class HomeItemsDataSource extends PageKeyedDataSource<Integer, HomeItem>{
    private static final String TAG = "HomeItemsDataSource";
    private Query initialQuery;
    private DocumentSnapshot lastVisible;
    private boolean lastPageReached;
    private int pageNumber = 1;

    HomeItemsDataSource(CollectionReference productsRef){
        this.initialQuery = productsRef.orderBy(HOME_ITEMS_FILED, Query.Direction.ASCENDING).limit(HOME_ITEMS_PER_PAGE);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, HomeItem> callback) {
        Log.d(TAG, "loadInitial");
        initialQuery.get().addOnCompleteListener(task ->{
            List<HomeItem> initialHomeItemList = new ArrayList<>();
            if(task.isSuccessful()){
                QuerySnapshot initialQuerySnapshot = task.getResult();
                for(QueryDocumentSnapshot document : initialQuerySnapshot){
                    initialHomeItemList.add(document.toObject(HomeItem.class));
                }
                callback.onResult(initialHomeItemList, null, pageNumber);
                int initialQuerySnapshotSize = initialQuerySnapshot.size() - 1;
                if(initialQuerySnapshotSize != -1) {
                    lastVisible = initialQuerySnapshot.getDocuments().get(initialQuerySnapshotSize);
                }
            } else{
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeItem> callback) {
        Log.d(TAG, "loadBefore");
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeItem> callback) {
        Log.d(TAG, "loadAfter");
        Query nextQuery = initialQuery.startAfter(lastVisible);
        nextQuery.get().addOnCompleteListener(task -> {
            List<HomeItem> nextHomeItemList = new ArrayList<>();
            if (task.isSuccessful()) {
                QuerySnapshot nextQuerySnapshot = task.getResult();
                if (!lastPageReached) {
                    for (QueryDocumentSnapshot document : nextQuerySnapshot) {
                        nextHomeItemList.add(document.toObject(HomeItem.class));
                    }
                    callback.onResult(nextHomeItemList, pageNumber);
                    pageNumber++;
                    int nextQuerySnapshotSize = nextQuerySnapshot.size() - 1;
                    if (nextHomeItemList.size() < HOME_ITEMS_PER_PAGE) {
                        lastPageReached = true;
                    } else {
                        lastVisible = nextQuerySnapshot.getDocuments().get(nextQuerySnapshotSize);
                    }
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }
}
