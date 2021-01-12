package com.hanyj96.dadaeyeo.presentation.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.datasource.remote.ProductsDataSource;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private LiveData<ArrayList<HomeItem>> homeItemLiveData;
    private ProductsDataSource productsDataSource = new ProductsDataSource();

    @Inject
    public HomeViewModel(){
        if(homeItemLiveData == null){
            this.homeItemLiveData = productsDataSource.findAll();
        }
    }

    public LiveData<ArrayList<HomeItem>> getHomeItemLiveData(){
        return homeItemLiveData;
    }
}
