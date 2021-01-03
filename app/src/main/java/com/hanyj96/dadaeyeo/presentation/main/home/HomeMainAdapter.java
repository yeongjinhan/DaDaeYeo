package com.hanyj96.dadaeyeo.presentation.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.databinding.ItemType1Binding;

import static com.hanyj96.dadaeyeo.utils.Constants.HOME_ITEM_TYPE1;

public class HomeMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case HOME_ITEM_TYPE1:
                ItemType1Binding itemType1Binding = ItemType1Binding.inflate(layoutInflater);
                return new ViewHolderItemType1(itemType1Binding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
class ViewHolderItemType1 extends RecyclerView.ViewHolder{
    private ItemType1Binding itemType1Binding;

    public ViewHolderItemType1(ItemType1Binding itemType1Binding) {
        super(itemType1Binding.getRoot());
        this.itemType1Binding = itemType1Binding;
    }

    void bindItemType1(){

    }
}

