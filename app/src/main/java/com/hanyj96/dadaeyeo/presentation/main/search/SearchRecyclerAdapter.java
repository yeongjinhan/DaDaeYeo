package com.hanyj96.dadaeyeo.presentation.main.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.ProductItem_type1;


public class SearchRecyclerAdapter extends PagedListAdapter<Product, SearchRecyclerAdapter.SearchRecyclerViewHolder> {
    private OnProductClickListener onProductClickListener;

    public SearchRecyclerAdapter(OnProductClickListener onProductClickListener){
        super(diffCallback);
        this.onProductClickListener = onProductClickListener;
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
        Product product = getItem(position);
        if(product != null){
            holder.bindData(product);
        }
    }

    private static DiffUtil.ItemCallback<Product> diffCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(Product oldProduct, Product newProduct) {
            return oldProduct.getProductID().equals(newProduct.getProductID());
        }

        @Override
        public boolean areContentsTheSame(Product oldProduct, @NonNull Product newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    class SearchRecyclerViewHolder extends RecyclerView.ViewHolder{
        private ProductItem_type1 productItem_type1;

        public SearchRecyclerViewHolder(ProductItem_type1 productItem_type1){
            super(productItem_type1.getRoot());
            this.productItem_type1 = productItem_type1;
        }

        void bindData(Product product){
            productItem_type1.setProduct(product);
            productItem_type1.setOnProductClickListener(onProductClickListener);
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }
}
