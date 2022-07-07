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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.BaseApplication;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.databinding.FragmentSearchBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;

import org.matomo.sdk.Tracker;
import org.matomo.sdk.extra.TrackHelper;

import javax.inject.Inject;



public class SearchFragment extends BaseFragment<FragmentSearchBinding>
        implements
        SearchRecyclerAdapter.OnProductClickListener,
        SearchListAdapter.OnKeywordClickListener,
        SearchListAdapter.OnDeleteKeywordClickListener
{
    private static final String TAG = "SearchFragment";
    @Inject SearchViewModel searchViewModel;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private SearchListAdapter searchListAdapter;
    Tracker myTracker;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSearchRecyclerView();
        initEditText();
        initSearchKeywordListView();
        observeKeywordList();

        // Matomo SDK
        myTracker = ((BaseApplication) getActivity().getApplication()).getTracker();
    }

    /*******************************************
     *  initViews
     *******************************************/

    // 검색결과 RecyclerView 주입
    private void initSearchRecyclerView(){
        Log.d(TAG,"initSearchRecyclerView");
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        dataBinding.mainSearchRecyclerview.setLayoutManager(gridLayoutManager);
        dataBinding.mainSearchRecyclerview.setHasFixedSize(true);
        dataBinding.mainSearchRecyclerview.setAdapter(searchRecyclerAdapter);
    }

    // 검색창 EditText 설정
    private void initEditText(){
        Log.d(TAG,"initEditText");
        // 검색버튼 클릭 리스너
        dataBinding.editTextInputWord.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId){
                case EditorInfo.IME_ACTION_SEARCH:
                    Search(v.getText().toString());
                    searchViewModel.insertKeyword(v.getText().toString());
                    break;
                default:
                    // 기본 엔터키 입력시
                    return false;
            }
            return true;
        });

        // 검색창 포커스에 따른 키워드 리스트 Visibility 조절
        dataBinding.editTextInputWord.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                dataBinding.setProductsResult(false);
            }else{
                dataBinding.setProductsResult(true);
            }
        });

        // 텍스트 입력 리스너
        dataBinding.editTextInputWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty() && s.toString().length() >= 1){
                    searchViewModel.findKeywords(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty() || s.toString().length() <= 1){
                    searchViewModel.loadHistoryKeywordList();
                }
            }
        });
    }

    // 검색리스트 KeywordListView 주입
    private void initSearchKeywordListView(){
        searchListAdapter = new SearchListAdapter(this,this);
        dataBinding.mainSearchKeywordList.setAdapter(searchListAdapter);
    }

    /*******************************************
     *  observeDataList
     *******************************************/

    private void observeProductList(){
        searchViewModel.getPagedListLiveData().
                observe(getViewLifecycleOwner(), products -> {
                    searchRecyclerAdapter.submitList(products);
                });
    }

    private void observeKeywordList(){
        searchViewModel.getKeywordList().
                observe(getViewLifecycleOwner(), keywords -> {
                    searchListAdapter.updateItems(keywords);
                });
    }

    private void Search(String keyword){
       /* InputMethodManager manager = (InputMethodManager)requireActivity().getSystemService(requireActivity().INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(requireActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);*/

        searchViewModel.searchProductByText(this, keyword.toLowerCase());
        observeProductList();
        // 검색결과 레이아웃에 포커스 삽입
        dataBinding.mainSearchLayout.setFocusableInTouchMode(true);
        dataBinding.mainSearchLayout.requestFocus();
    }

    /*******************************************
     *  Event Listener
     *******************************************/

    // 제품 클릭 리스너
    @Override
    public void onProductClick(Product product) {
        searchViewModel.insertUserProduct(product.getProductID());
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        searchViewModel.deleteKeyword(keyword);
    }

    @Override
    public void ClickKeyword(Keyword keyword) {
        // matomoSDK 검색어 수집
        TrackHelper.track().search(keyword.getKeyword()).with(myTracker);
        dataBinding.editTextInputWord.setText(keyword.getKeyword());
        Search(keyword.getKeyword());
    }
}
