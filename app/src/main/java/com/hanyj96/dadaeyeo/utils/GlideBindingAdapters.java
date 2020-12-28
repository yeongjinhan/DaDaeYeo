package com.hanyj96.dadaeyeo.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideBindingAdapters {

    @BindingAdapter("ProductImageView")
    public static void setProductImageView(ImageView view, String url){
        Context context = view.getContext();
        Glide.with(context)
                .load(url)
                //.apply(new RequestOptions().override())
                .into(view);
    }
}
