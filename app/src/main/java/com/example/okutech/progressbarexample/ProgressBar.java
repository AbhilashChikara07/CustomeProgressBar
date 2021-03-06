/*
 * Copyright (C) 2015 Pedramrn@gmail.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.okutech.progressbarexample;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * A subclass of {@link android.view.View} class for creating a custom circular progressBar
 * <p>
 * Created by Pedram on 2015-01-06.
 */

public class ProgressBar extends View {


    /**
     * ProgressBar's line thickness
     */
    private float progressBarThickness = 4;
    private float progress = 0;
    private int min = 0;
    private int max = 100;
    /**
     * Start the progress at 12 o'clock
     */
    private int startAngle = -90;
    private int color = Color.RED;
    private RectF rectF;
    private Paint defaultStockPaint;
    private Paint stockPaint;

    public float getStrokeWidth() {
        return progressBarThickness;
    }

    public void setStrokeWidth(float progressBarThickness) {
        this.progressBarThickness = progressBarThickness;
        defaultStockPaint.setStrokeWidth(progressBarThickness);
        stockPaint.setStrokeWidth(progressBarThickness);
        invalidate();
        requestLayout();//Because it should recalculate its bounds
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setColor(int color) {
        this.color = color;
        defaultStockPaint.setColor(adjustAlpha(color, 0.3f));
        stockPaint.setColor(color);
        invalidate();
        requestLayout();
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressBar,
                0, 0);
        //Reading values from the XML layout
        try {
            progressBarThickness = typedArray.getDimension(R.styleable.ProgressBar_progressBarThickness, progressBarThickness);
            progress = typedArray.getFloat(R.styleable.ProgressBar_progress, progress);
            color = typedArray.getInt(R.styleable.ProgressBar_progressbarColor, color);
            min = typedArray.getInt(R.styleable.ProgressBar_min, min);
            max = typedArray.getInt(R.styleable.ProgressBar_max, max);
        } finally {
            typedArray.recycle();
        }

        defaultStockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        defaultStockPaint.setColor(adjustAlpha(color, 0.3f));
        defaultStockPaint.setStyle(Paint.Style.STROKE);
        defaultStockPaint.setStrokeWidth(progressBarThickness);

        stockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stockPaint.setColor(color);
        stockPaint.setStyle(Paint.Style.STROKE);
        stockPaint.setStrokeWidth(progressBarThickness);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(rectF, defaultStockPaint);
        float angle = 360 * progress / max;
        canvas.drawArc(rectF, startAngle, angle, false, stockPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        Log.e("height", "" + height);
        Log.e("width", "" + width);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);

        Log.e("progressBarThickness--", "" + progressBarThickness);
        Log.e("progressBarThickness-1", "" + 0 + progressBarThickness / 2);
        Log.e("progressBarThickness-2", "" + 0 + progressBarThickness / 2);
        Log.e("progressBarThickness-3", "" + (min - progressBarThickness / 2));
        Log.e("progressBarThickness-4", "" + (min - progressBarThickness / 2));
        Log.e("min", "" + min);

        rectF.set(0 + progressBarThickness / 2, 0 + progressBarThickness / 2, min - progressBarThickness / 2, min - progressBarThickness / 2);
    }

    /**
     * Lighten the given color by the factor
     *
     * @param color  The color to lighten
     * @param factor 0 to 4
     * @return A brighter color
     */
    public int lightenColor(int color, float factor) {
        float r = Color.red(color) * factor;
        float g = Color.green(color) * factor;
        float b = Color.blue(color) * factor;
        int ir = Math.min(255, (int) r);
        int ig = Math.min(255, (int) g);
        int ib = Math.min(255, (int) b);
        int ia = Color.alpha(color);
        return (Color.argb(ia, ir, ig, ib));
    }

    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color  The color to transparent
     * @param factor 1.0f to 0.0f
     * @return int - A transplanted color
     */
    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Set the progress with an animation.
     * Note that the {@link android.animation.ObjectAnimator} Class automatically set the progress
     * so don't call the {@link ProgressBar#setProgress(float)} directly within this method.
     *
     * @param progress The progress it should animate to it.
     */
    public void setProgressWithAnimation(float progress) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(1500);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}
