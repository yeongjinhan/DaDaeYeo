package com.hanyj96.dadaeyeo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hanyj96.dadaeyeo.di.GlideApp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class BindingAdapters {
    // ImageView Glide Binding
    @BindingAdapter("bind_product_img")
    public static void setProductImageView(ImageView view, String pid){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("root/Products/" + pid + ".jpg");
        Context context = view.getContext();
        GlideApp.with(context)
                .load(storageReference)
                .override(200,200)
                .into(view);
    }

    @BindingAdapter("bind_String_price")
    public static void setStringPrice(TextView textView, double price){
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String value = myFormatter.format(price);
        textView.setText(value + "원");
    }

/*    @BindingAdapter("bind_date_format")
    public static void setDateFormat(TextView textView, String date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        textView.setText(simpleDateFormat.parse(date));
    }*/
}
