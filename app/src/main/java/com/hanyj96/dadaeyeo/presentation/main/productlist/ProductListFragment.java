package com.hanyj96.dadaeyeo.presentation.main.productlist;

import android.os.Bundle;
import android.os.Trace;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hanyj96.dadaeyeo.BaseApplication;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.FragmentProductListBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.search.SearchRecyclerAdapter;

import org.matomo.sdk.Tracker;
import org.matomo.sdk.extra.TrackHelper;

import javax.inject.Inject;

public class ProductListFragment extends BaseFragment<FragmentProductListBinding> implements SearchRecyclerAdapter.OnProductClickListener{
    private static final String TAG = "ProductListFragment";
    private SearchRecyclerAdapter searchRecyclerAdapter;
    @Inject ProductListViewModel productListViewModel;

    private Tracker myTracker;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_product_list;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBinding.productListTitle.setText(ProductListFragmentArgs.fromBundle(getArguments()).getProductListTitle());
        initBackButton();
        initSearchRecyclerView();
        LoadProductListData(
                Integer.parseInt(ProductListFragmentArgs.fromBundle(getArguments()).getProductListProductCategory()),
                Integer.parseInt(ProductListFragmentArgs.fromBundle(getArguments()).getProductListProductSubCategory()));

        myTracker = ((BaseApplication)getActivity().getApplication()).getTracker();

        //((BaseApplication)getActivity().getApplication()).getBaseTrack().screens(getActivity().getApplication()).with(myTracker);
        /*((BaseApplication)getActivity().getApplication())
                .getBaseTrack()
                .screen("MainActivity/ProductListFragment")
                .title("ProductListView")
                .with(myTracker);*/
    }

    private void initSearchRecyclerView(){
        Log.d(TAG,"initSearchRecyclerView");
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        dataBinding.productListRecyclerView.setLayoutManager(gridLayoutManager);
        dataBinding.productListRecyclerView.setHasFixedSize(true);
        dataBinding.productListRecyclerView.setAdapter(searchRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("제품리스트","onDestroyView");
    }

    @Override
    public void onDetach() {
        Log.d("제품리스트","onDetach");
        super.onDetach();
    }

    private void initBackButton(){
        dataBinding.productListBtnBack.setOnClickListener(v ->
            NavHostFragment.findNavController(this).popBackStack()
        );
    }

    private void LoadProductListData(int main, int sub){
        productListViewModel.loadProductsListByCategory(main,sub);
        observeProductList();
    }

    @Override
    public void onProductClick(Product product) {

    }

    private void observeProductList(){
        productListViewModel.getProductList().observe(getViewLifecycleOwner(),products -> searchRecyclerAdapter.submitList(products));
    }

}
