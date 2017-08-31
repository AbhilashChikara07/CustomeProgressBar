package com.example.okutech.progressbarexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 8/24/17
 */

public class CustomCircleBarView extends View {

    /*--CIRCLE STATES--*/
    private final int INITIAL_CIRCLE_STATE = 0;
    private final int COLOR_STOCK_CIRCLE_STATE = 1;
    private final int FULL_CIRCLE_STATE = 2;

    private RectF outerRectF;
    private RectF innerRectF;
    private Paint stockCirclePaint;
    private Paint fullCirclePaint;

    private int circleColor;
    private int stockColor;
    private float stockWidth;
    private float circleThickness;
    private int circleState;
    private int startAngel = 270;
    private int finishAngel = 360;
    private Context context;

    public CustomCircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        outerRectF = new RectF();
        innerRectF = new RectF();
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.WizardProgressBar,
                    0, 0);
            circleColor = typedArray.getColor(R.styleable.WizardProgressBar_circleColor,
                    circleColor);
            stockColor = typedArray.getColor(R.styleable.WizardProgressBar_stockColor,
                    stockColor);
            stockWidth = typedArray.getDimension(R.styleable.WizardProgressBar_stockWidth,
                    stockWidth);
            circleThickness = typedArray.getDimension(R.styleable.WizardProgressBar_circleThickness,
                    circleThickness);
            circleState = typedArray.getInteger(R.styleable.WizardProgressBar_circleState, circleState);
        } finally {
            typedArray.recycle();
        }

        stockCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stockCirclePaint.setStyle(Paint.Style.STROKE);
        stockCirclePaint.setColor(stockColor);
        stockCirclePaint.setStrokeWidth(stockWidth);

        fullCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fullCirclePaint.setStyle(Paint.Style.FILL);
        fullCirclePaint.setColor(ContextCompat.getColor(context, R.color.white_color));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (circleState) {
            case INITIAL_CIRCLE_STATE: {
                makeInitialStateCircle(canvas);
                break;
            }
            case COLOR_STOCK_CIRCLE_STATE: {
                makeStockStateCircle(canvas);
                break;
            }
            case FULL_CIRCLE_STATE: {
                makeFullStateCircle(canvas);
                break;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        final int outerMin = Math.min(width, height);
        setMeasuredDimension(outerMin, outerMin);
        outerRectF.set(circleThickness / 2 + getPaddingLeft(), circleThickness / 2 + getPaddingTop()
                , outerMin - circleThickness / 2 - getPaddingRight(),
                outerMin - circleThickness / 2 - getPaddingBottom());

        /*-- 80 is representing that we are making inner circle by covering 80 percent of space --*/
        int innerMin = outerMin * 80 / 100;
        int halfWidth = width / 2;
        int radius = halfWidth / 2;
        int leftCoordinate = (halfWidth - radius);

        innerRectF.set(leftCoordinate, leftCoordinate, innerMin - circleThickness / 2,
                innerMin - circleThickness / 2);
    }

    public void makeInitialStateCircle(Canvas canvas) {
        canvas.drawOval(outerRectF, stockCirclePaint);
        canvas.drawArc(outerRectF, startAngel, finishAngel, true, stockCirclePaint);
    }

    public void makeStockStateCircle(Canvas canvas) {
        stockCirclePaint.setColor(ContextCompat.getColor(context, R.color.white_color));
        canvas.drawOval(outerRectF, stockCirclePaint);
        canvas.drawArc(outerRectF, startAngel, 90, true, stockCirclePaint);

        canvas.drawOval(innerRectF, fullCirclePaint);
        canvas.drawArc(innerRectF, startAngel, 90, true, fullCirclePaint);
    }

    public void makeFullStateCircle(Canvas canvas) {
        canvas.drawOval(outerRectF, fullCirclePaint);
        canvas.drawArc(outerRectF, startAngel, finishAngel, true, fullCirclePaint);
    }

    public void setOuterCircleStockColor(int stockColor) {
        stockCirclePaint.setColor(stockColor);
        invalidate();
    }

//    public void setProgressWithAnimation(float progress) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
//        objectAnimator.setDuration(1500);
//        objectAnimator.setInterpolator(new DecelerateInterpolator());
//        objectAnimator.start();
//        invalidate();
//    }

}
