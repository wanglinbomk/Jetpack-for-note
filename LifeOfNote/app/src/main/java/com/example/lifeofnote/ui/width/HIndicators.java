package com.example.lifeofnote.ui.width;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofnote.R;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class HIndicators extends View {

    private Paint mBgPaint = new Paint(1);;
    private RectF mBgRect=new RectF();;
    private float mRadius;
    private Paint mPaint= new Paint(1);;
    private RectF mRect=new RectF();;
    private int viewWidth;
    private int mBgColor;
    private int mIndicatorColor;
    private float ratio;
    private float progress;


    public HIndicators(Context context) {
        super(context);
    }

    public HIndicators(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HIndicators(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBgColor = Color.parseColor("#e5e5e5");
        this.mIndicatorColor = Color.parseColor("#ff4646");
        this.ratio = 0.5F;
        TypedArray var10000 = context.obtainStyledAttributes(attrs, R.styleable.HIndicators);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "context.obtainStyledAttr…, R.styleable.HIndicator)");
        TypedArray typedArray = var10000;
        this.mBgColor = typedArray.getColor(R.styleable.HIndicators_hi_bgColor, this.mBgColor);
        this.mIndicatorColor = typedArray.getColor(R.styleable.HIndicators_hi_indicatorColor, this.mIndicatorColor);
        typedArray.recycle();
        this.mBgPaint.setColor(this.mBgColor);
        this.mBgPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mIndicatorColor);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    public final float getRatio() {
        return this.ratio;
    }

    public final void setRatio(float value) {
        this.ratio = value;
        this.invalidate();
    }

    public final float getProgress() {
        return this.progress;
    }

    public final void setProgress(float value) {
        this.progress = value;
        this.invalidate();
    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewWidth = w;
        this.mBgRect.set(0.0F, 0.0F, (float) w * 1.0F, (float) h * 1.0F);
        this.mRadius = (float) h / 2.0F;
    }

    public final void setBgColor(@ColorInt int color) {
        this.mBgPaint.setColor(color);
        this.invalidate();
    }

    public final void setIndicatorColor(@ColorInt int color) {
        this.mPaint.setColor(color);
        this.invalidate();
    }

    public final void bindRecyclerView(@NotNull final RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        recyclerView.addOnScrollListener((RecyclerView.OnScrollListener) (new RecyclerView.OnScrollListener() {
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                int offsetX = recyclerView.computeHorizontalScrollOffset();
                int range = recyclerView.computeHorizontalScrollRange();
                int extend = recyclerView.computeHorizontalScrollExtent();
                float progress = (float) offsetX * 1.0F / (float) (range - extend);
                setProgress(progress);
            }
        }));
        recyclerView.addOnLayoutChangeListener((OnLayoutChangeListener) (new OnLayoutChangeListener() {
            public final void onLayoutChange(View $noName_0, int $noName_1, int $noName_2, int $noName_3, int $noName_4, int $noName_5, int $noName_6, int $noName_7, int $noName_8) {
                int range = recyclerView.computeHorizontalScrollRange();
                int extend = recyclerView.computeHorizontalScrollExtent();
                float ratio = (float) extend * 1.0F / (float) range;
                setRatio(ratio);
            }
        }));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.drawRoundRect(this.mBgRect, this.mRadius, this.mRadius, this.mBgPaint);
        }

        float leftOffset = (float) this.viewWidth * (1.0F - this.ratio) * this.progress;
        float left = this.mBgRect.left + leftOffset;
        float right = left + (float) this.viewWidth * this.ratio;
        this.mRect.set(left, this.mBgRect.top, right, this.mBgRect.bottom);
        if (canvas != null) {
            canvas.drawRoundRect(this.mRect, this.mRadius, this.mRadius, this.mPaint);
        }
    }

}
