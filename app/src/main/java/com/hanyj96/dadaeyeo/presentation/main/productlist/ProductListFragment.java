package com.hanyj96.dadaeyeo.presentation.main.productlist;

import static com.hanyj96.dadaeyeo.utils.Constants.BASE_TRACK;

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
import com.hanyj96.dadaeyeo.presentation.main.home.HomeFragmentDirections;
import com.hanyj96.dadaeyeo.presentation.main.search.SearchFragment;
import com.hanyj96.dadaeyeo.presentation.main.search.SearchRecyclerAdapter;

import org.matomo.sdk.Tracker;
import org.matomo.sdk.extra.TrackHelper;

import javax.inject.Inject;
import javax.inject.Named;

public class ProductListFragment extends BaseFragment<FragmentProductListBinding> implements SearchRecyclerAdapter.OnProductClickListener{
    private static final String TAG = ProductListFragment.class.getSimpleName();
    private SearchRecyclerAdapter searchRecyclerAdapter;
    @Inject ProductListViewModel productListViewModel;
    private Tracker tracker;
    @Inject @Named(BASE_TRACK)
    TrackHelper.Dimension BaseTrack;

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
        // tracker 인스턴스 저장
        tracker = ((BaseApplication)getActivity().getApplication()).getTracker();
        // 현재 스크린 경로 수집
        BaseTrack.screen("/MainActivity/" + ProductListFragment.class.getSimpleName()).title("상품목록화면").with(tracker);
    }

    private void initSearchRecyclerView(){
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        dataBinding.productListRecyclerView.setLayoutManager(gridLayoutManager);
        dataBinding.productListRecyclerView.setHasFixedSize(true);
        dataBinding.productListRecyclerView.setAdapter(searchRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
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
        // 클릭 이벤트 수집
        BaseTrack.event("PRODUCT","Product Detail View").name(product.getProductName()).with(tracker);
        NavHostFragment.findNavController(this).navigate(ProductListFragmentDirections.actionProductListFragmentToProductInfoFragment(product.getProductID()));
    }

    private void observeProductList(){
        productListViewModel.getProductList().observe(getViewLifecycleOwner(),products -> searchRecyclerAdapter.submitList(products));
    }

}
