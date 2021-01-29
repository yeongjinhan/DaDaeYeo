package com.hanyj96.dadaeyeo.presentation.main.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.Item_type1_1DataBinding;


public class HomeHorizontalAdapter extends FirestoreRecyclerAdapter<Product, HomeHorizontalAdapter.HomeHorizontalViewHolder> {
    public HomeHorizontalAdapter(FirestoreRecyclerOptions<Product> options){
        super(options);
    }
    @NonNull
    @Override
    public HomeHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Item_type1_1DataBinding item_type1_1DataBinding = Item_type1_1DataBinding.inflate(layoutInflater);
        return new HomeHorizontalViewHolder(item_type1_1DataBinding);
    }

    @Override
    protected void onBindViewHolder(@NonNull HomeHorizontalViewHolder holder, int position, @NonNull Product product) {
        holder.bindData(product);
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
