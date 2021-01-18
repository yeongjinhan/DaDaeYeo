package com.hanyj96.dadaeyeo.presentation.main.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.data.repository.KeywordRepository;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.hanyj96.dadaeyeo.utils.HelperClass.getCurrentDate;

public class SearchViewModel extends ViewModel{
    private static String TAG = "SearchViewModel";

    private ProductRepository productRepository;
    private KeywordRepository keywordRepository;
    // 검색기록 + 추천검색어 리스트
    private MediatorLiveData<List<Keyword>> keywordList = new MediatorLiveData<>();
    // 제품 리스트
    private LiveData<ArrayList<Product>> ProductList;

    @Inject
    SearchViewModel(ProductRepository productRepository, KeywordRepository keywordRepository){
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        ProductList = productRepository.getProducts();
        // 키워드
        keywordList.addSource(keywordRepository.getHistoryKeywordList(),
                value-> keywordList.setValue(value));
        keywordList.addSource(keywordRepository.getAutoKeywordList(),
                value-> keywordList.setValue(value));
    }

    /*******************************************
     *  ProductRepository
     *******************************************/

    public void searchProduct(String keyword){
        productRepository.searchProductforName(keyword);
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

    public LiveData<ArrayList<Product>> getProductList(){
        return ProductList;
    }

    public LiveData<List<Keyword>> getKeywordList() {
        return keywordList;
    }
}
