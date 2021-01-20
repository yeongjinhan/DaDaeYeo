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
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.databinding.FragmentSearchBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import javax.inject.Inject;



public class SearchFragment extends BaseFragment<FragmentSearchBinding>
        implements
        SearchRecyclerAdapter.OnProductClickListener,
        SearchListAdapter.OnKeywordClickListener,
        SearchListAdapter.OnDeleteKeywordClickListener
{
    @Inject SearchViewModel searchViewModel;
    private InputMethodManager imm;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private SearchListAdapter searchListAdapter;

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
        observeProductList();
        observeKeywordList();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 프래그먼트를 벗어나면 키보드 숨김
        imm.hideSoftInputFromWindow(dataBinding.editTextInputWord.getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.editTextInputWord.setFocusableInTouchMode(true);
        dataBinding.editTextInputWord.requestFocus();
    }

    /*******************************************
     *  initViews
     *******************************************/

    // 검색결과 RecyclerView 주입
    private void initSearchRecyclerView(){
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        dataBinding.mainSearchRecyclerview.setLayoutManager(gridLayoutManager);
        dataBinding.mainSearchRecyclerview.setHasFixedSize(true);
        dataBinding.mainSearchRecyclerview.setAdapter(searchRecyclerAdapter);
    }

    // 검색창 EditText 설정
    private void initEditText(){
        // 검색 프래그먼트가 호출되면 자동으로 검색창에 포커스 주입
        dataBinding.editTextInputWord.requestFocus();

        // 포커스 주입과 동시에 키보드 호출
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

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
                dataBinding.mainSearchLayout.setVisibility(View.INVISIBLE);
                dataBinding.mainSearchKeywordList.setVisibility(View.VISIBLE);
                Log.d("키워드 입력","포커스 받음");
            }else{
                imm.hideSoftInputFromWindow(dataBinding.editTextInputWord.getWindowToken(), 0);
                dataBinding.mainSearchLayout.setVisibility(View.VISIBLE);
                dataBinding.mainSearchKeywordList.setVisibility(View.INVISIBLE);
                Log.d("키워드 입력","포커스 없음");
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
                observe(getViewLifecycleOwner(), products -> searchRecyclerAdapter.submitList(products));
    }

    private void observeKeywordList(){
        searchViewModel.getKeywordList().
                observe(getViewLifecycleOwner(), keywords -> searchListAdapter.updateItems(keywords));
    }

    private void reObserveProductList(){
        searchViewModel.searchProductByText(this, null);
        observeProductList();
    }

    private void Search(String keyword){
        searchViewModel.searchProductByText(this, keyword.toLowerCase());
        observeProductList();
        // 검색결과 레이아웃에 포커스 삽입
        dataBinding.mainSearchLayout.setFocusableInTouchMode(true);
        dataBinding.mainSearchLayout.requestFocus();
    }

    /*******************************************
     *  Listener
     *******************************************/

    // 제품 클릭 리스너
    @Override
    public void onProductClick(Product product) {
        Log.d("Product",product.getProductName());
        searchViewModel.insertUserProduct(product.getProductID());
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        searchViewModel.deleteKeyword(keyword);
    }

    @Override
    public void ClickKeyword(Keyword keyword) {
        dataBinding.editTextInputWord.setText(keyword.getKeyword());
        Search(keyword.getKeyword());
    }
}
