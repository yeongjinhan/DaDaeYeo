package com.hanyj96.dadaeyeo.presentation.main.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
    private MutableLiveData<List<Keyword>> keywordList = new MutableLiveData<>();
    private LiveData<ArrayList<Product>> productLiveData;

    // 검색기록
    private LiveData<List<Keyword>> keywordHistroy;
    // 추천검색어
    private LiveData<List<Keyword>> keywordAuto;

    @Inject
    SearchViewModel(ProductRepository productRepository, KeywordRepository keywordRepository){
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        productLiveData = productRepository.getProducts();
        keywordHistroy = keywordRepository.getHistoryKeywordList();
        keywordAuto = keywordRepository.getAutoKeywordList();
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
        keywordRepository.insertKeyword(new Keyword(word,false,getCurrentDate()));
    }

    public void deleteKeyword(Keyword keyword){
        keywordRepository.deleteKeyword(keyword);
    }

    public void updateKeyword(Keyword keyword){
        keywordRepository.updateKeyword(keyword);
    }

    // findKeywords

    public void findKeywords(String keyword){
        keywordRepository.findKeywords(keyword);
    }

    /*******************************************
     *  observeData
     *******************************************/

    public void addAutoKeywords(List<Keyword> autoKeywords){
        Log.d("검색","검색결과있음");
        List<Keyword> temp = new ArrayList<>();
        if(keywordList.getValue() != null){
            temp = keywordList.getValue();
        }
        temp.addAll(autoKeywords);
        keywordList.setValue(temp);
    }

    public LiveData<ArrayList<Product>> getProductList(){
        return productLiveData;
    }

    public LiveData<List<Keyword>> getKeywordList() {
        return keywordList;
    }

    public LiveData<List<Keyword>> getKeywordHistroy() {
        return keywordHistroy;
    }

    public LiveData<List<Keyword>> getKeywordAuto() {
        return keywordAuto;
    }
}
