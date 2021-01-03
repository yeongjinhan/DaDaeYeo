package com.hanyj96.dadaeyeo.presentation.main.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.data.Product;
import com.hanyj96.dadaeyeo.databinding.Item1DataBinding;

public class HomeItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemType1ClickListener onItemType1ClickListener;

    HomeItemAdapter(OnItemType1ClickListener onItemType1ClickListener){
        super();
        this.onItemType1ClickListener = onItemType1ClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Item1DataBinding item1DataBinding = Item1DataBinding.inflate(layoutInflater);
        return new Item1ViewHolder(item1DataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;

    }
    class Item1ViewHolder extends RecyclerView.ViewHolder{
        private Item1DataBinding item1DataBinding;

        Item1ViewHolder(Item1DataBinding item1DataBinding){
            super(item1DataBinding.getRoot());
            this.item1DataBinding = item1DataBinding;
        }
        public Item1ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        void bindItem(Product product){
            item1DataBinding.setProduct(product);
            item1DataBinding.setOnItem1ClickListener(onItemType1ClickListener);
        }
    }
    public interface OnItemType1ClickListener {
        void onItem1Click(Product clickProduct);
    }
}
