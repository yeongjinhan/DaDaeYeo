package com.hanyj96.dadaeyeo.presentation.main.product;

import com.hanyj96.dadaeyeo.data.model.products.ProductInfo;

public class ProductInfoEventListener {

    public interface OnBuyButtonClickListener {
        void onBuyClick(ProductInfo productInfo);
    }

    public interface OnAddCartButtonClickListener {
        void onAddCartClick(ProductInfo productInfo);
    }
}
