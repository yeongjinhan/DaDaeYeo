package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.hanyj96.dadaeyeo.data.model.contents.HomeItem;
import com.hanyj96.dadaeyeo.data.repository.AppDataRepository;
import com.hanyj96.dadaeyeo.data.repository.ContentsRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;
import com.hanyj96.dadaeyeo.database.remote.HomeItemsDataSourceFactory;

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
    private LiveData<PagedList<HomeItem>> homeItemList;
    private MutableLiveData<Integer> scrollY = new MutableLiveData<>();


    @Inject
    public HomeViewModel(ProductRepository productRepository,
                         ContentsRepository contentsRepository,
                         @Named(HOME_ITEMS_PAGED_LIST_CONFIG)PagedList.Config config,
                         @Named(HOME_ITEMS_COLLECTION)CollectionReference homeItemsRef){
        this.productRepository = productRepository;
        this.contentsRepository = contentsRepository;
        productHistoryList = productRepository.getUserProductHistoryList();
        eventIDList = contentsRepository.getEventIds();
        homeItemList = new LivePagedListBuilder<>(new HomeItemsDataSourceFactory(homeItemsRef), config).build();
    }

    @Override
    protected void onCleared() {
        Log.d("HomeViewModel","onCleared()");
        super.onCleared();
    }

    public void setScrollY(int y){
        scrollY.setValue(y);
    }

    public LiveData<Integer> getScrollY(){
        return scrollY;
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

    public LiveData<PagedList<HomeItem>> getHomeItemList() {
        return homeItemList;
    }

}
