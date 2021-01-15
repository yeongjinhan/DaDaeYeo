package com.hanyj96.dadaeyeo.presentation.main.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.KeywordHistory;
import com.hanyj96.dadaeyeo.database.local.AppDatabase;
import com.hanyj96.dadaeyeo.databinding.FragmentSearchBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.OnProductClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SearchFragment extends BaseFragment<FragmentSearchBinding>
        implements
        Observer<ArrayList<Product>>,
        OnProductClickListener,
        OnKeywordClickListener,
        OnDeleteKeywordClickListener
{
    @Inject SearchViewModel searchViewModel;
    private InputMethodManager imm;
    private SearchListAdapter searchListAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadProducts();
        initEditText();
        initSearchKeywordListView();
        loadKeywordHistory();
    }

    private void Search(String keyword){
        searchViewModel.productRepository.searchProductforName(keyword);
        // 검색버튼을 클릭하면 키보드 숨기기
        imm.hideSoftInputFromWindow(dataBinding.editTextInputWord.getWindowToken(), 0);
        dataBinding.mainSearchKeywordList.setVisibility(View.INVISIBLE);
        dataBinding.mainSearchLayout.setVisibility(View.VISIBLE);
    }

    private void initEditText(){
        // 검색 프래그먼트가 호출되면 자동으로 검색창에 포커스 주입
        dataBinding.editTextInputWord.requestFocus();
        // 포커스 주입과 동시에 키보드 호출
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        // 키보드 클릭 리스너
        dataBinding.editTextInputWord.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId){
                case EditorInfo.IME_ACTION_SEARCH:
                    Search(v.getText().toString());
                    addKeywordHistory(v.getText().toString());
                    break;
                default:
                    // 기본 엔터키 입력시
                    return false;
            }
            return true;
        });
        dataBinding.editTextInputWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                dataBinding.mainSearchKeywordList.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataBinding.mainSearchLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initSearchKeywordListView(){
        ArrayList<KeywordHistory> keywords = new ArrayList<>();
        searchListAdapter = new SearchListAdapter(keywords,this,this);
        dataBinding.mainSearchKeywordList.setAdapter(searchListAdapter);
    }

    private void loadKeywordHistory(){
        AppDatabase
                .getInstance(getContext())
                .keywordHistoryDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    Log.d("키워드","size : " + item.size());
                    searchListAdapter.UpdateItems(item);
                });

    }

    private void addKeywordHistory(String keyword){
        AppDatabase
                .getInstance(getContext())
                .keywordHistoryDao()
                .insert(new KeywordHistory(keyword,getCurrentDate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void loadProducts(){
        searchViewModel.getAll().observe(getViewLifecycleOwner(),this);
    }

    public String getCurrentDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        return dateFormat.format(date);
    }

    @Override
    public void onProductClick(Product product) {
        Log.d("Product",product.getProductName());
    }

    @Override
    public void onChanged(ArrayList<Product> products) {
        if(products != null){
            SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(products, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(dataBinding.mainSearchRecyclerview.getContext(),2);
            dataBinding.mainSearchRecyclerview.setLayoutManager(gridLayoutManager);
            dataBinding.mainSearchRecyclerview.setHasFixedSize(true);
            dataBinding.mainSearchRecyclerview.setAdapter(searchRecyclerAdapter);
        }
    }

    @Override
    public void deleteKeyword(KeywordHistory keyword) {
        AppDatabase
                .getInstance(getContext())
                .keywordHistoryDao()
                .delete(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void ClickKeyword(KeywordHistory keyword) {
        Search(keyword.getKeyword());
    }
}
