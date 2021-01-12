package com.hanyj96.dadaeyeo.presentation.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.databinding.Recycler_View_Type1_DataBinding;
import java.util.ArrayList;

public class HomeVerticalAdapter extends RecyclerView.Adapter<HomeVerticalAdapter.VerticalItemType1ViewHolder> {
    private ArrayList<HomeItem> homeItems;
    private Context context;

    public HomeVerticalAdapter(Context context, ArrayList<HomeItem> homeItems){
        this.context = context;
        this.homeItems = homeItems;
    }


    @NonNull
    @Override
    public VerticalItemType1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Recycler_View_Type1_DataBinding recycler_view_type1_dataBinding = Recycler_View_Type1_DataBinding.inflate(layoutInflater);
        return new VerticalItemType1ViewHolder(recycler_view_type1_dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalItemType1ViewHolder holder, int position) {
        HomeItem homeItem = homeItems.get(position);
        if(homeItem != null){
            holder.bindData(homeItem);
        }
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    class VerticalItemType1ViewHolder extends RecyclerView.ViewHolder{
        private Recycler_View_Type1_DataBinding recycler_view_type1_dataBinding;
        public VerticalItemType1ViewHolder(Recycler_View_Type1_DataBinding recycler_view_type1_dataBinding) {
            super(recycler_view_type1_dataBinding.getRoot());
            this.recycler_view_type1_dataBinding = recycler_view_type1_dataBinding;
        }
        void bindData(HomeItem homeItem){
            recycler_view_type1_dataBinding.setData(homeItem);
            HomeHorizontalAdapter homeHorizontalAdapter = new HomeHorizontalAdapter(homeItem.getProducts());
            recycler_view_type1_dataBinding.itemType1Recyclerview.setAdapter(homeHorizontalAdapter);
            recycler_view_type1_dataBinding.itemType1Recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recycler_view_type1_dataBinding.itemType1Recyclerview.setHasFixedSize(true);
        }
    }
}
