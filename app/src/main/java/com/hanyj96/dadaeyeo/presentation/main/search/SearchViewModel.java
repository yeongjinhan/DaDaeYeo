package com.hanyj96.dadaeyeo.presentation.main.search;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.data.repository.KeywordRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import static com.hanyj96.dadaeyeo.utils.HelperClass.getCurrentDate;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCT_HISTORY;

public class SearchViewModel extends ViewModel{
    private static final String TAG = "SearchViewModel";

    LiveData<PagedList<Product>> pagedListLiveData;

    private ProductRepository productRepository;
    private KeywordRepository keywordRepository;
    // 검색기록 + 추천검색어 리스트
    private MediatorLiveData<List<Keyword>> keywordList = new MediatorLiveData<>();


    @Inject
    SearchViewModel(ProductRepository productRepository,
                    KeywordRepository keywordRepository){
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        // 키워드
        keywordList.addSource(keywordRepository.getHistoryKeywordList(),
                value-> keywordList.setValue(value));
        keywordList.addSource(keywordRepository.getAutoKeywordList(),
                value-> keywordList.setValue(value));
        pagedListLiveData = productRepository.getProductsByText(null);
    }

    /*******************************************
     *  ProductsDataSourceFactory
     *******************************************/

    public void searchProductByText(LifecycleOwner lifecycleOwner, String text){
        Log.d(TAG,"searchProductByText -> " + text);
        if(pagedListLiveData.hasObservers()){
            pagedListLiveData.removeObservers(lifecycleOwner);
        }
        pagedListLiveData = productRepository.getProductsByText(text);
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
