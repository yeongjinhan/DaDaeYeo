package com.hanyj96.dadaeyeo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;


public class BindingAdapters {
    // ImageView Glide Binding
    @BindingAdapter("bind_product_img")
    public static void setProductImageView(ImageView view, String pid){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("root/Products/" + pid + ".jpg");
        Context context = view.getContext();
        GlideApp.with(context)
                .load(storageReference)
                .into(view);
    }

    @BindingAdapter("bind_String_price")
    public static void setStringPrice(TextView textView, double price){
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String value = myFormatter.format(price);
        textView.setText(value + "원");
    }

}
