package com.hanyj96.dadaeyeo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
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
                .override(200,200)
                .into(view);
    }

    @BindingAdapter("bind_product_info_img")
    public static void setProductInfoImageView(ImageView view, String pid){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("root/Products/" + pid + ".jpg");
        Context context = view.getContext();
        GlideApp.with(context)
                .load(storageReference)
                .into(view);
    }

    @BindingAdapter("bind_event_img")
    public static void setEventImageView(ImageView view, String eid){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("root/Events/" + eid + ".jpg");
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

    @BindingAdapter("bind_String_PurchaseCount")
    public static void setStringPurchaseCount(TextView textView, double purchaseCount){
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String value = myFormatter.format(purchaseCount);
        textView.setText(value + "명 구매");
    }

    /*
    @BindingAdapter("bind_date_format")
    public static void setDateFormat(TextView textView, String date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        textView.setText(simpleDateFormat.parse(date));
    }
    */
}
