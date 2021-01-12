package com.hanyj96.dadaeyeo.presentation.main.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.databinding.ProductItem_type1;

import java.util.ArrayList;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchRecyclerViewHolder>  {
    private ArrayList<Product> products;

    public SearchRecyclerAdapter(ArrayList<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductItem_type1 productItem_type1 = ProductItem_type1.inflate(layoutInflater);
        return new SearchRecyclerViewHolder(productItem_type1);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewHolder holder, int position) {
        Product product = products.get(position);
        if(product != null){
            holder.bindData(product);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class SearchRecyclerViewHolder extends RecyclerView.ViewHolder{
        private ProductItem_type1 productItem_type1;

        public SearchRecyclerViewHolder(ProductItem_type1 productItem_type1){
            super(productItem_type1.getRoot());
            this.productItem_type1 = productItem_type1;
        }

        void bindData(Product product){
            productItem_type1.setProduct(product);
        }
    }
}
