package com.techjini.nanodegree.spotify.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.techjini.nanodegree.spotify.constants.Constants;

/**
 * Created by Sanjeev on 3/27/16.
 */
public class ViewUtils {
    @BindingAdapter({"app:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (!url.startsWith("http")) {
            url = Constants.IMAGE_BASE_PATH + url;
        }
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
