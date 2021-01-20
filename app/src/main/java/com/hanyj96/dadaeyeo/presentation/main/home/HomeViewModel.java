package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.repository.ContentsRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<HomeItem>> homeItemList = new MutableLiveData<>();
    private LiveData<List<String>> productHistoryList;
    private LiveData<ArrayList<Product>> productByIdList;
    private ProductRepository productRepository;
    private ContentsRepository contentsRepository;
    private LiveData<List<String>> eventIDList;

    @Inject
    public HomeViewModel(ProductRepository productRepository, ContentsRepository contentsRepository){
        Log.d("HomeViewModel", "홈뷰모델 생성");
        this.productRepository = productRepository;
        this.contentsRepository = contentsRepository;
        productHistoryList = productRepository.getUserProductHistoryList();
        productByIdList = productRepository.getUserProductList();
        eventIDList = contentsRepository.getEventIds();
    }

    /*******************************************
     *  ProductRepository
     *******************************************/

    public void searchProductByID(List<String> productID){
        productRepository.searchProductByID(productID);
    }

    /*******************************************
     *  ContentsRepository
     *******************************************/

    public void getAllEventIds(){
        contentsRepository.getAllEventIds();
    }

    /*******************************************
     *  observeData
     *******************************************/

    public MutableLiveData<ArrayList<HomeItem>> getHomeItemList() {
        return homeItemList;
    }

    public void setHomeItemList(ArrayList<HomeItem> homeItemList){
        this.homeItemList.setValue(homeItemList);
    }

    public LiveData<List<String>> getProductHistoryList() {
        return productHistoryList;
    }

    public LiveData<ArrayList<Product>> getProductByIdList() {
        return productByIdList;
    }

    public LiveData<List<String>> getEventIDList() {
        return eventIDList;
    }
}
