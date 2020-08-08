package com.example.lifeofnote.mineview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * author : Boggle
 * e-mail : 1105059963@qq.com
 * date   : 2019-11-2915:24
 * desc   :
 * version: 1.0
 */
@SuppressLint("AppCompatCustomView")
public class FontIconView extends TextView {
    public FontIconView(Context context) {
        super(context);
        init(context);
    }

    public FontIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        //设置字体图标
        Typeface font = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
        this.setTypeface(font);
    }
}
