package com.hanyj96.dadaeyeo.di;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.presentation.main.MainAcitivity;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import static com.hanyj96.dadaeyeo.utils.Constants.BASE_TRACK;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_COLLECTION;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_PER_PAGE;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_PAGED_LIST_CONFIG;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_COLLECTION;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_PER_PAGE;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_PAGED_LIST_CONFIG;
import static com.hanyj96.dadaeyeo.utils.Constants.TRACKER;

import org.matomo.sdk.Matomo;
import org.matomo.sdk.QueryParams;
import org.matomo.sdk.TrackMe;
import org.matomo.sdk.Tracker;
import org.matomo.sdk.TrackerBuilder;
import org.matomo.sdk.extra.TrackHelper;

import java.util.Calendar;
import java.util.Date;

@Module
class AppModule {

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
    @Named(PRODUCTS_PAGED_LIST_CONFIG)
    static PagedList.Config provideProductsPagedListConfig() {
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

    @Singleton
    @Provides
    @Named(HOME_ITEMS_PAGED_LIST_CONFIG)
    static PagedList.Config provideHomeItemsPagedListConfig(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(HOME_ITEMS_PER_PAGE)
                .build();
    }

    @Singleton
    @Provides
    @Named(HOME_ITEMS_COLLECTION)
    static CollectionReference provideHomeItemsCollectionReference(FirebaseFirestore rootRef){
        return rootRef.collection(HOME_ITEMS_COLLECTION);
    }

    @Singleton
    @Provides
    @Named(BASE_TRACK)
    static TrackHelper.Dimension provideBaseTrack(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        TrackMe trackMe = new TrackMe();
        trackMe.set(QueryParams.HOURS, Long.toString(calendar.get(calendar.HOUR)));
        trackMe.set(QueryParams.MINUTES, Long.toString(calendar.get(calendar.MINUTE)));
        trackMe.set(QueryParams.SECONDS, Long.toString(calendar.get(calendar.SECOND)));
        return TrackHelper.track(trackMe)
                .dimension(1, Long.toString(calendar.get(calendar.MILLISECOND)));
    }
}
