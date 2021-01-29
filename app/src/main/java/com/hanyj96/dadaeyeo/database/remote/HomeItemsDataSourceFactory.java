package com.hanyj96.dadaeyeo.database.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.google.firebase.firestore.CollectionReference;
import com.hanyj96.dadaeyeo.data.model.contents.HomeItem;

public class HomeItemsDataSourceFactory extends DataSource.Factory<Integer, HomeItem> {
    private MutableLiveData<PageKeyedDataSource<Integer, HomeItem>> HomeItemData = new MutableLiveData<>();
    private CollectionReference homeItemRef;

    public HomeItemsDataSourceFactory(CollectionReference homeItemRef) {
        this.homeItemRef = homeItemRef;
    }

    @NonNull
    @Override
    public DataSource<Integer, HomeItem> create() {
        HomeItemsDataSource homeItemsDataSource = new HomeItemsDataSource(homeItemRef);
        HomeItemData.postValue(homeItemsDataSource);
        return homeItemsDataSource;
    }
}
