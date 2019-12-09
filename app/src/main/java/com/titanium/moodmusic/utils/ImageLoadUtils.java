package com.titanium.moodmusic.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class ImageLoadUtils {

    public static void loadImage(Context context, String imgUrl, int placeHolderResourseId, ImageView imageView){
        WeakReference<Context> weakReferenceContext = new WeakReference<>(context);
        Glide.with(weakReferenceContext.get())
                .asBitmap()
                .load(imgUrl)
                .placeholder(placeHolderResourseId)
                .into(imageView);
    }
}
