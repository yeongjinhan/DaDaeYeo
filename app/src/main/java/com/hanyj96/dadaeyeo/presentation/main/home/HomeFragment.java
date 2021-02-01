package com.hanyj96.dadaeyeo.presentation.main.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.FragmentHomeBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.productlist.ProductListFragmentArgs;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends BaseFragment<FragmentHomeBinding>
        implements
        HomeVerticalAdapter.OnMoreClickListener {
    private static final String TAG = "HomeFragment";
    @Inject HomeViewModel homeViewModel;
    private HomeVerticalAdapter homeVerticalAdapter;
    private HomeViewPagerAdapter homeViewPagerAdapter;
    Disposable disposable;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        disposable.dispose();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored");
    }

    private void saveScrollPosition(){
        dataBinding.mainHomeCenterLayout.scrollTo(0,2373);
        dataBinding.mainHomeCenterLayout.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("스크롤","state : " + scrollX + " / " + scrollY);
            }
        });

    }

    /*******************************************
     *  Lifecycle
     *******************************************/



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
        initRecyclerAdapter();
        initViewPagerAdapter();
        initViewPagerIndicator();
        observeEventIDList();
        observeHomeItemList();
        //observeProductHistoryList();
        homeViewModel.getAllEventIds();
        observeViewPager();
        saveScrollPosition();
    }

    /*******************************************
     *  initViews
     *******************************************/

    private void initRecyclerAdapter(){
        homeVerticalAdapter = new HomeVerticalAdapter(getContext(), this);
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
                    Log.d("홈아이템","홈아이템 업데이트");
                    homeVerticalAdapter.submitList(homeItems);

                });
    }

    private void observeProductHistoryList(){
        homeViewModel.getProductHistoryList().
                observe(getViewLifecycleOwner(), productID -> {
                    if(!productID.isEmpty()){
                        Log.d("유저가 클릭한 제품 리스트", productID.toString());
                        /*ArrayList<HomeItem> homeItems = new ArrayList<>();
                        homeItems.add(new HomeItem(0,"고객님이 보신 제품들", productID));
                        homeViewModel.setHomeItemList(homeItems);*/
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

    /*******************************************
     *  Event Listener
     *******************************************/

    @Override
    public void onMoreClick(String title) {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToProductListFragment(title));
    }
}
