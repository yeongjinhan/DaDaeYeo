package com.hanyj96.dadaeyeo.di;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_COLLECTION;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_PER_PAGE;

@Module
class AppMoule {

    /*******************************************
     *  Firebase
     *******************************************/

    @Singleton
    @Provides
    static FirebaseFirestore provideFirebaseFireStore(){
        return FirebaseFirestore.getInstance();
    }

    @Singleton
    @Provides
    static PagedList.Config providePagedListConfig() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PRODUCTS_PER_PAGE)
                .build();
    }

    @Singleton
    @Provides
    @Named(PRODUCTS_COLLECTION)
    static CollectionReference provideProductsCollectionReference(FirebaseFirestore rootRef){
        return rootRef.collection(PRODUCTS_COLLECTION);
    }
}
