package com.hanyj96.dadaeyeo.presentation.main.product;

import static com.hanyj96.dadaeyeo.utils.Constants.BASE_TRACK;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.hanyj96.dadaeyeo.BaseApplication;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.products.ProductInfo;
import com.hanyj96.dadaeyeo.databinding.FragmentProductinfoBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;

import org.matomo.sdk.Tracker;
import org.matomo.sdk.extra.TrackHelper;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.disposables.Disposable;

public class ProductInfoFragment extends BaseFragment<FragmentProductinfoBinding>
        implements
        ProductInfoEventListener.OnBuyButtonClickListener,
        ProductInfoEventListener.OnAddCartButtonClickListener {
    @Inject ProductInfoViewModel productInfoViewModel;
    private ProductInfoViewPagerAdapter productInfoViewPagerAdapter;
    private Tracker tracker;
    @Inject @Named(BASE_TRACK)
    TrackHelper.Dimension BaseTrack;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_productinfo;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBackButton();
        initViewPagerAdapter();
        initViewPagerIndicator();
        loadProductInfoByID(ProductInfoFragmentArgs.fromBundle(getArguments()).getProductID());
        // tracker 인스턴스 저장
        tracker = ((BaseApplication)getActivity().getApplication()).getTracker();
        // 현재 스크린 경로 수집
        BaseTrack.screen("/MainActivity/" + ProductInfoFragment.class.getSimpleName()).title("상품상세정보화면").with(tracker);
        dataBinding.setOnBuyButtonClickListener(this);
        dataBinding.setOnAddCartButtonClickListener(this);
    }

    /*******************************************
     *  initViews
     *******************************************/

    private void initViewPagerAdapter(){
        productInfoViewPagerAdapter = new ProductInfoViewPagerAdapter();
        dataBinding.productInfoProductImg.setAdapter(productInfoViewPagerAdapter);
    }

    private void initViewPagerIndicator(){
        dataBinding.productInfoIndicator.setWithViewPager2(dataBinding.productInfoProductImg,true);
    }

    private void initBackButton(){
        dataBinding.productInfoBtnBack.setOnClickListener(v ->
                NavHostFragment.findNavController(this).popBackStack()
        );
    }

    /*******************************************
     *  observeDataList
     *******************************************/

    private void loadProductInfoByID(String productID){
        productInfoViewModel.loadProductInfoByID(productID);
        observeProductInfo();
    }

    private void observeProductInfo(){
        productInfoViewModel.getProductInfo().observe(getViewLifecycleOwner(),productInfo -> {
            Log.d("productInfoFragment", "productInfo => " + productInfo.getProductName());
            dataBinding.setData(productInfo);
            productInfoViewPagerAdapter.updateItems(productInfo.getProductImgIDs());
        });
    }

    /*******************************************
     *  Event Listener
     *******************************************/

    @Override
    public void onBuyClick(ProductInfo productInfo) {
        // 클릭 이벤트 수집
        BaseTrack.event("PRDOCUT","상품 구매").name(productInfo.getProductName()).with(tracker);
    }

    @Override
    public void onAddCartClick(ProductInfo productInfo) {
        // 클릭 이벤트 수집
        BaseTrack
                // 상품 식별자 수집
                .dimension(115,productInfo.getProductID())
                .event("PRDOCUT","장바구니 추가")
                .name(productInfo.getProductName())
                .with(tracker);
    }
}
