package com.example.lifeofnote.base;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.DecimalFormat;

public class BindingAttr {
    @BindingAdapter({"ImageViewId"})
    public static void loadLocalImage(ImageView imageView, Drawable drawable) {
        imageView.setBackground(drawable);
    }

    @BindingAdapter({"doubleNum"})
    public static void doubleNum(TextView textView, double number) {
        textView.setText(new DecimalFormat("0.00").format(number));
    }
}
