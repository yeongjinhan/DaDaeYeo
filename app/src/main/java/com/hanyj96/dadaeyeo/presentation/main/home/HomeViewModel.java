package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.hanyj96.dadaeyeo.data.model.contents.HomeItem;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.repository.ContentsRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;
import com.hanyj96.dadaeyeo.database.remote.HomeItemsDataSource;
import com.hanyj96.dadaeyeo.database.remote.HomeItemsDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_COLLECTION;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_PAGED_LIST_CONFIG;

public class HomeViewModel extends ViewModel {
    private ProductRepository productRepository;
    private ContentsRepository contentsRepository;

    private LiveData<List<String>> productHistoryList;
    private LiveData<List<String>> eventIDList;

    private LiveData<PagedList<HomeItem>> homItemList;
    private PagedList.Config config;
    private CollectionReference homeItemsRef;
    private HomeItemsDataSourceFactory homeItemsDataSourceFactory;

    @Inject
    public HomeViewModel(ProductRepository productRepository,
                         ContentsRepository contentsRepository,
                         @Named(HOME_ITEMS_PAGED_LIST_CONFIG)PagedList.Config config,
                         @Named(HOME_ITEMS_COLLECTION)CollectionReference homeItemsRef){
        Log.d("HomeViewModel", "홈뷰모델 생성");
        this.productRepository = productRepository;
        this.contentsRepository = contentsRepository;
        productHistoryList = productRepository.getUserProductHistoryList();
        eventIDList = contentsRepository.getEventIds();

        this.config = config;
        this.homeItemsRef = homeItemsRef;

        homeItemsDataSourceFactory = new HomeItemsDataSourceFactory(homeItemsRef);
        homItemList = new LivePagedListBuilder<>(homeItemsDataSourceFactory, config).build();
    }

    /*******************************************
     *  ProductRepository
     *******************************************/



    /*******************************************
     *  ContentsRepository
     *******************************************/

    public void getAllEventIds(){
        contentsRepository.getAllEventIds();
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<List<String>> getProductHistoryList() {
        return productHistoryList;
    }

    public LiveData<List<String>> getEventIDList() {
        return eventIDList;
    }

    public LiveData<PagedList<HomeItem>> getHomItemList() {
        return homItemList;
    }
}
