package com.hanyj96.dadaeyeo.presentation.main.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
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

    public ProductRepository productRepository;

    private KeywordRepository keywordRepository;
    private LiveData<List<Keyword>> keywordList;

    private LiveData<ArrayList<Product>> productLiveData;

    @Inject
    SearchViewModel(ProductRepository productRepository, KeywordRepository keywordRepository){
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        productLiveData = productRepository.getProducts();
        keywordList = keywordRepository.getKeywords();
    }

    /*******************************************
     *  ProductRepository
     *******************************************/


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

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<ArrayList<Product>> getProductList(){
        return productLiveData;
    }

    public LiveData<List<Keyword>> getKeywordList() {
        return keywordList;
    }
}
