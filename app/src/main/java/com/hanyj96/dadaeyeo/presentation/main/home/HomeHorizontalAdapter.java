package com.hanyj96.dadaeyeo.presentation.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.data.Product;
import com.hanyj96.dadaeyeo.databinding.Item_type1_1DataBinding;

import java.util.ArrayList;

public class HomeHorizontalAdapter extends RecyclerView.Adapter<HomeHorizontalAdapter.HomeHorizontalViewHolder> {
    private ArrayList<Product> products;

    public HomeHorizontalAdapter(ArrayList<Product> products){
        this.products = products;
    }
    @NonNull
    @Override
    public HomeHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Item_type1_1DataBinding item_type1_1DataBinding = Item_type1_1DataBinding.inflate(layoutInflater);
        return new HomeHorizontalViewHolder(item_type1_1DataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorizontalViewHolder holder, int position) {
        Product product = products.get(position);
        if(product != null){
            holder.bindData(product);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class HomeHorizontalViewHolder extends RecyclerView.ViewHolder{
        private Item_type1_1DataBinding item_type1_1DataBinding;

        public HomeHorizontalViewHolder(Item_type1_1DataBinding item_type1_1DataBinding) {
            super(item_type1_1DataBinding.getRoot());
            this.item_type1_1DataBinding = item_type1_1DataBinding;
        }
        void bindData(Product product){
            item_type1_1DataBinding.setData(product);
        }
    }
}
