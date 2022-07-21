package com.hanyj96.dadaeyeo.di;

import com.hanyj96.dadaeyeo.presentation.main.category.CategoryFragment;
import com.hanyj96.dadaeyeo.presentation.main.home.HomeFragment;
import com.hanyj96.dadaeyeo.presentation.main.myinfo.MyinfoFragment;
import com.hanyj96.dadaeyeo.presentation.main.myrent.MyrentFragment;
import com.hanyj96.dadaeyeo.presentation.main.productlist.ProductListFragment;
import com.hanyj96.dadaeyeo.presentation.main.search.SearchFragment;
import com.hanyj96.dadaeyeo.presentation.main.product.ProductInfoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
@SuppressWarnings("unused")
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragemnt();

    @ContributesAndroidInjector
    abstract CategoryFragment contributeCataegoryFragment();

    @ContributesAndroidInjector
    abstract MyinfoFragment contributeMyinfoFragment();

    @ContributesAndroidInjector
    abstract MyrentFragment contributeMyrentFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract ProductListFragment contributeProductListFragment();

    @ContributesAndroidInjector
    abstract ProductInfoFragment contributeProductInfoFragment();
}
