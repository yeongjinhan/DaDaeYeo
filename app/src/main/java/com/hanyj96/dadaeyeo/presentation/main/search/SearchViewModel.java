package com.hanyj96.dadaeyeo.presentation.main.search;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.data.repository.KeywordRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;
import com.hanyj96.dadaeyeo.database.remote.ProductsDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_COLLECTION;
import static com.hanyj96.dadaeyeo.utils.Constants.SEARCH_TYPE_NAME;
import static com.hanyj96.dadaeyeo.utils.HelperClass.getCurrentDate;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCT_HISTORY;

public class SearchViewModel extends ViewModel{
    private static String TAG = "SearchViewModel";

    private CollectionReference productsRef;
    private PagedList.Config config;
    private ProductsDataSourceFactory sourceFactory;
    LiveData<PagedList<Product>> pagedListLiveData;

    private ProductRepository productRepository;
    private KeywordRepository keywordRepository;
    // 검색기록 + 추천검색어 리스트
    private MediatorLiveData<List<Keyword>> keywordList = new MediatorLiveData<>();


    @Inject
    SearchViewModel(ProductRepository productRepository,
                    KeywordRepository keywordRepository,
                    PagedList.Config config,
                    @Named(PRODUCTS_COLLECTION) CollectionReference productsRef){
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;

        // 키워드
        keywordList.addSource(keywordRepository.getHistoryKeywordList(),
                value-> keywordList.setValue(value));
        keywordList.addSource(keywordRepository.getAutoKeywordList(),
                value-> keywordList.setValue(value));
        // ProductsDataSourceFactory
        this.config = config;
        this.productsRef = productsRef;
        sourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,null,0,0, productsRef);
        pagedListLiveData = new LivePagedListBuilder<>(sourceFactory, config).build();
    }

    /*******************************************
     *  ProductsDataSourceFactory
     *******************************************/

    public void searchProductByText(LifecycleOwner lifecycleOwner, String text){
        pagedListLiveData.removeObservers(lifecycleOwner);
        if (text == null) {
            sourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,null,0,0, productsRef);
        } else {
            sourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,text,0,0, productsRef);
        }
        pagedListLiveData = new LivePagedListBuilder<>(sourceFactory, config).build();
    }

    /*******************************************
     *  ProductRepository
     *******************************************/

    public void insertUserProduct(String productId){
        productRepository.insertUserProduct(new userProduct(PRODUCT_HISTORY,productId,getCurrentDate()));
    }

    /*******************************************
     *  KeywordRepository
     *******************************************/

    // DB CRUD

    public void insertKeyword(String word){
        for(Keyword keyword : keywordRepository.loadHistoryKeywordList()){
            if(keyword.getKeyword().equals(word)){
                Log.d(TAG,"키워드 날짜 업데이트");
                keyword.setDate(getCurrentDate());
                keywordRepository.updateKeyword(keyword);
                return;
            }
        }
        keywordRepository.insertKeyword(new Keyword(word,false,getCurrentDate()));
    }

    public void deleteKeyword(Keyword keyword){
        keywordRepository.deleteKeyword(keyword);
    }

    // findKeywords

    public void findKeywords(String keyword){
        keywordRepository.findKeywords(keyword);
    }

    public void loadHistoryKeywordList(){
        keywordList.setValue(keywordRepository.loadHistoryKeywordList());
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<PagedList<Product>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<List<Keyword>> getKeywordList() {
        return keywordList;
    }
}
