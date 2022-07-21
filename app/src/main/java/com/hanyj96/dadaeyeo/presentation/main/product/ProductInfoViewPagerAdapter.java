package com.hanyj96.dadaeyeo.presentation.main.product;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.databinding.ProductImg_Item;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoViewPagerAdapter extends RecyclerView.Adapter<ProductInfoViewPagerAdapter.ProductInfoViewPagerViewHolder>{
    List<String> productImgIDs;

    ProductInfoViewPagerAdapter(){
        this.productImgIDs = new ArrayList<>();
    }

    public void updateItems(List<String> productImgIDs){
        Log.d("ProductImgs", "productImgIDs -> " +productImgIDs.toString() );
        this.productImgIDs.clear();
        this.productImgIDs = productImgIDs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductInfoViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductImg_Item product_img_item = ProductImg_Item.inflate(layoutInflater, parent, false);
        return new ProductInfoViewPagerViewHolder(product_img_item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductInfoViewPagerViewHolder holder, int position) {
        String imgID = productImgIDs.get(position);
        if(imgID != null){
            holder.bindData(imgID);
        }
    }

    @Override
    public int getItemCount() {
        return productImgIDs.size();
    }

    class ProductInfoViewPagerViewHolder extends RecyclerView.ViewHolder {
        private ProductImg_Item product_img_item;
        public ProductInfoViewPagerViewHolder(ProductImg_Item product_img_item) {
            super(product_img_item.getRoot());
            this.product_img_item = product_img_item;
        }

        public void bindData(String imgID){
            product_img_item.setImgID(imgID);
        }
    }
}
