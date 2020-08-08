package com.example.lifeofnote.utils;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Dimension;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * author : Boggle
 * e-mail : 1105059963@qq.com
 * date   : 2019-11-2611:46
 * desc   :
 * version: 1.0
 */
public class Screenutils {

    public static ConstraintLayout.LayoutParams getBannerParams(View view, Context context) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view
                .getLayoutParams();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        //屏幕宽度
        int screenWidth = dm.widthPixels;
        params.height = (int) (screenWidth * 0.4);
        params.leftMargin = Screenutils.dip2px(context, 15);
        params.rightMargin = Screenutils.dip2px(context, 15);
        return params;
    }

    //首页middle广告的高度
    public static ConstraintLayout.LayoutParams getMIddleParams(View view, Activity activity) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width2 = outMetrics.widthPixels-dip2px(activity,24);
        params.width=width2;
        params.height = (int) (width2 * 0.47);
        return params;
    }

    public static ConstraintLayout.LayoutParams getMyBannerParams(View view, Context context) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view
                .getLayoutParams();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        //屏幕宽度
        int screenWidth = dm.widthPixels;
        params.height = (int) (screenWidth * 0.22);
        return params;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width2 = outMetrics.widthPixels;
        return width2;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidths(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width2 = outMetrics.widthPixels-dip2px(activity,24);
        return width2;
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * 设置EditText的hint字体大小
     *
     * @param editText EditText控件
     * @param hintText hint内容
     * @param size     hint字体大小，单位为sp
     */
    public static void setEditTextHintWithSize(EditText editText, String hintText, @Dimension int size) {
        if (!TextUtils.isEmpty(hintText)) {
            SpannableString ss = new SpannableString(hintText);
            //设置字体大小 true表示单位是sp
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setHint(new SpannedString(ss));
        }
    }


}
