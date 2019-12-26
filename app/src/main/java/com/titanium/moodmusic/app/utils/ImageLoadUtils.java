package com.titanium.moodmusic.app.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class ImageLoadUtils {

    public static void loadImage(Context context, String imgUrl, int placeHolderResourсeId, ImageView imageView) {
        WeakReference<Context> weakReferenceContext = new WeakReference<>(context);
        Glide.with(weakReferenceContext.get())
                .asBitmap()
                .load(imgUrl)
                .placeholder(placeHolderResourсeId)
                .into(imageView);
    }
}