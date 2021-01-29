package com.hanyj96.dadaeyeo.presentation.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.hanyj96.dadaeyeo.data.model.contents.HomeItem;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.Recycler_View_Type1_DataBinding;

public class HomeVerticalAdapter extends PagedListAdapter<HomeItem, HomeVerticalAdapter.VerticalItemType1ViewHolder> {
    private Context context;

    public HomeVerticalAdapter(Context context){
        super(diffCallback);
        this.context = context;
    }

    public void updateItems(){
        notifyDataSetChanged();
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
        HomeItem homeItem = getItem(position);
        if(homeItem != null){
            holder.bindData(homeItem);
        }
    }

    private static DiffUtil.ItemCallback<HomeItem> diffCallback = new DiffUtil.ItemCallback<HomeItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
            return oldItem.getItemID().equals(newItem.getItemID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
            return oldItem.equals(newItem);
        }
    };

    class VerticalItemType1ViewHolder extends RecyclerView.ViewHolder{
        private Recycler_View_Type1_DataBinding recycler_view_type1_dataBinding;
        public VerticalItemType1ViewHolder(Recycler_View_Type1_DataBinding recycler_view_type1_dataBinding) {
            super(recycler_view_type1_dataBinding.getRoot());
            this.recycler_view_type1_dataBinding = recycler_view_type1_dataBinding;
        }
        void bindData(HomeItem homeItem){
            recycler_view_type1_dataBinding.setData(homeItem);

            Query query = FirebaseFirestore.getInstance()
                    .collection("Products")
                    .whereIn("productID",homeItem.getProductIDs())
                    .limit(10);
            FirestoreRecyclerOptions<Product> options = new FirestoreRecyclerOptions.Builder<Product>()
                    .setQuery(query, Product.class)
                    .build();

            HomeHorizontalAdapter homeHorizontalAdapter = new HomeHorizontalAdapter(options);
            recycler_view_type1_dataBinding.itemType1Recyclerview.setAdapter(homeHorizontalAdapter);
            recycler_view_type1_dataBinding.itemType1Recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recycler_view_type1_dataBinding.itemType1Recyclerview.setHasFixedSize(true);
            homeHorizontalAdapter.startListening();
        }
    }
}
