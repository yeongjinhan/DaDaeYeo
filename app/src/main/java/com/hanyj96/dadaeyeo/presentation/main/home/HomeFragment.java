package com.hanyj96.dadaeyeo.presentation.main.home;

import static com.hanyj96.dadaeyeo.utils.Constants.BASE_TRACK;
import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEMS_PAGED_LIST_CONFIG;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.BaseApplication;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.FragmentHomeBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.productlist.ProductListFragmentArgs;
import com.hanyj96.dadaeyeo.presentation.main.productlist.ProductListFragmentDirections;
import com.hanyj96.dadaeyeo.presentation.main.search.SearchRecyclerAdapter;
import com.orhanobut.logger.Logger;

import org.matomo.sdk.TrackMe;
import org.matomo.sdk.Tracker;
import org.matomo.sdk.extra.TrackHelper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends BaseFragment<FragmentHomeBinding>
        implements
        HomeVerticalAdapter.OnMoreClickListener,
        SearchRecyclerAdapter.OnProductClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @Inject HomeViewModel homeViewModel;
    private HomeVerticalAdapter homeVerticalAdapter;
    private HomeViewPagerAdapter homeViewPagerAdapter;
    private Disposable disposable;
    private Tracker tracker;
    @Inject @Named(BASE_TRACK)TrackHelper.Dimension BaseTrack;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        homeViewModel.setScrollY(dataBinding.mainHomeCenterLayout.getScrollY());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerAdapter();
        initViewPagerAdapter();
        initViewPagerIndicator();
        observeEventIDList();
        observeHomeItemList();
        //observeProductHistoryList();
        homeViewModel.getAllEventIds();
        observeViewPager();
        observeScrollPosition();
        // tracker 인스턴스 저장
        tracker = ((BaseApplication)getActivity().getApplication()).getTracker();
        // 현재 스크린 경로 수집
        BaseTrack.screen("/MainActivity/" + HomeFragment.class.getSimpleName()).title("홈화면").with(tracker);
    }

    /*******************************************
     *  initViews
     *******************************************/

    private void initRecyclerAdapter(){
        homeVerticalAdapter = new HomeVerticalAdapter(getContext(), this, this);
        homeVerticalAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
        dataBinding.mainHomeRecyclerview.setHasFixedSize(true);
        dataBinding.mainHomeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.mainHomeRecyclerview.setAdapter(homeVerticalAdapter);
    }

    private void initViewPagerAdapter(){
        homeViewPagerAdapter = new HomeViewPagerAdapter();
        dataBinding.mainHomeEventPage.setAdapter(homeViewPagerAdapter);
    }

    private void initViewPagerIndicator(){
        dataBinding.mainHomeEventIndicator.setWithViewPager2(dataBinding.mainHomeEventPage,true);
    }

    /*******************************************
     *  observeDataList
     *******************************************/

    private void observeHomeItemList(){
        homeViewModel.getHomeItemList().
                observe(getViewLifecycleOwner(), homeItems -> {
                    homeVerticalAdapter.submitList(homeItems);
                });
    }

    private void observeProductHistoryList(){
        homeViewModel.getProductHistoryList().
                observe(getViewLifecycleOwner(), productID -> {
                    if(!productID.isEmpty()){

                    }
                });
    }

    private void observeEventIDList(){
        homeViewModel.getEventIDList().observe(getViewLifecycleOwner(), ids ->{
            homeViewPagerAdapter.updateItems(ids);
        });
    }

    private void observeViewPager(){
        if(disposable == null){
            disposable = Observable.interval(5,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(it->{
                        if(homeViewPagerAdapter.getItemCount() != 0){
                            if(dataBinding.mainHomeEventPage.getCurrentItem() < homeViewPagerAdapter.getItemCount() -1){
                                dataBinding.mainHomeEventPage.setCurrentItem(dataBinding.mainHomeEventPage.getCurrentItem() + 1);
                            }else{
                                dataBinding.mainHomeEventPage.setCurrentItem(0);
                            }
                        }
                    });
        }
    }

    private void observeScrollPosition(){
        homeViewModel.getScrollY().observe(getViewLifecycleOwner(), y -> {
        });
    }

    /*******************************************
     *  Event Listener
     *******************************************/

    @Override
    public void onMoreClick(String title, String productCategory, String productSubCategory) {
        // 클릭 이벤트 수집
        BaseTrack.event("CLICK","Menu Click").name("더보기").with(tracker);
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToProductListFragment(title, productCategory, productSubCategory));
    }

    @Override
    public void onProductClick(Product product) {
        // 클릭 이벤트 수집
        BaseTrack.event("PRODUCT","Product Detail View").name(product.getProductName()).with(tracker);
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToProductInfoFragment(product.getProductID()));
    }
}
