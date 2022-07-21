package com.hanyj96.dadaeyeo.database.remote;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hanyj96.dadaeyeo.data.model.products.Product;

import java.util.ArrayList;
import java.util.List;

import static com.hanyj96.dadaeyeo.utils.Constants.*;

@SuppressWarnings("ConstantConditions")
public class ProductsDataSource extends PageKeyedDataSource<Integer, Product>{
    private static final String TAG = ProductsDataSource.class.getName();
    private Query initialQuery;             // 검색 쿼리
    private DocumentSnapshot lastVisible;
    private boolean lastPageReached;
    private int pageNumber = 1;

    ProductsDataSource(int searchType, String searchText, int mainCategory, int subCategory, CollectionReference productsRef){
        this.initialQuery = productsRef.orderBy(PRODUCTS_NAME_FILED, Query.Direction.ASCENDING).limit(PRODUCTS_PER_PAGE);
        switch (searchType){
            case SEARCH_TYPE_ALL:
                // 모든 제품 불러오기
                break;
            case SEARCH_TYPE_NAME:
                // 이름으로 제품 검색
                if(searchText != null){
                    initialQuery = initialQuery.startAt(searchText).endAt(searchText + ESCAPE_CHARACTER);
                }
                break;
            case SEARCH_TYPE_CATEGORY:
                    initialQuery = productsRef.limit(PRODUCTS_PER_PAGE).whereEqualTo("mainCategory",mainCategory).whereEqualTo("subCategory",subCategory);
                break;
        }
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {
        initialQuery.get().addOnCompleteListener(task ->{
            List<Product> initialProductList = new ArrayList<>();
            if(task.isSuccessful()){
                QuerySnapshot initialQuerySnapshot = task.getResult();
                for(QueryDocumentSnapshot document : initialQuerySnapshot){
                    initialProductList.add(document.toObject(Product.class));
                }
                callback.onResult(initialProductList, null, pageNumber);
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
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {
        Query nextQuery = initialQuery.startAfter(lastVisible);
        nextQuery.get().addOnCompleteListener(task -> {
            List<Product> nextProductList = new ArrayList<>();
            if (task.isSuccessful()) {
                QuerySnapshot nextQuerySnapshot = task.getResult();
                if (!lastPageReached) {
                    for (QueryDocumentSnapshot document : nextQuerySnapshot) {
                        Product product = document.toObject(Product.class);
                        nextProductList.add(product);
                    }
                    callback.onResult(nextProductList, pageNumber);
                    pageNumber++;
                    int nextQuerySnapshotSize = nextQuerySnapshot.size() - 1;
                    if (nextProductList.size() < PRODUCTS_PER_PAGE) {
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
