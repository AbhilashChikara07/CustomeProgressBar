package com.example.okutech.progressbarexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
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
    private final int INITIAL_STATE = 0;
    private final int MID_STATE = 1;
    private final int FINAL_STATE = 2;

    private RectF outerUnSelectRectF;
    private RectF outerSelectRectF;

    private Paint unSelectStockPaint;
    private Paint selectStockPaint;

    private RectF circleUnSelectedRectF;
    private RectF circleSelectedRectF;

    private Paint circleUnSelectedPaint;
    private Paint circleSelectedPaint;

    private int circleColor;
    private int stockColor;
    private float stockWidth;
    private int circleState;
    private int startAngel = 270;
    private int finishAngel = 360;
    private Context context;
    private int width;
    private int height;
    private int progressValue;

    private Handler progressHandler;
    private Runnable progressRunnable;
    private int max = 100;
    private final int PROGRESS_STEP = 2;
    private final int PROGRESS_DELAY = 8;


    public CustomCircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        outerUnSelectRectF = new RectF();
        outerSelectRectF = new RectF();
        circleUnSelectedRectF = new RectF();
        circleSelectedRectF = new RectF();

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
            circleState = typedArray.getInteger(R.styleable.WizardProgressBar_circleState,
                    circleState);
            progressValue = typedArray.getInteger(R.styleable.WizardProgressBar_progressValue, progressValue);

        } finally {
            typedArray.recycle();
        }

        unSelectStockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        unSelectStockPaint.setStyle(Paint.Style.STROKE);
        unSelectStockPaint.setColor(stockColor);
        unSelectStockPaint.setStrokeWidth(stockWidth);

        selectStockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectStockPaint.setStyle(Paint.Style.STROKE);
        selectStockPaint.setColor(ContextCompat.getColor(context, R.color.white_color));
        selectStockPaint.setStrokeWidth(stockWidth);

        circleUnSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleUnSelectedPaint.setStyle(Paint.Style.FILL);
        circleUnSelectedPaint.setColor(stockColor);

        circleSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleSelectedPaint.setStyle(Paint.Style.FILL);
        circleSelectedPaint.setColor(ContextCompat.getColor(context, R.color.white_color));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (circleState) {
            case INITIAL_STATE: {
                initialStateCircle(canvas);
                break;
            }
            case MID_STATE: {
                midStateCircle(canvas);
                break;
            }
            case FINAL_STATE: {
                finalStateCircle(canvas);
                break;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void initialStateCircle(Canvas canvas) {
        int initialCo = getInitialCoordinate(0.03);
        outerUnSelectRectF.set(initialCo + getPaddingLeft(), initialCo + getPaddingTop()
                , width - initialCo - getPaddingRight(),
                width - initialCo - getPaddingBottom());

        canvas.drawOval(outerUnSelectRectF, unSelectStockPaint);
        canvas.drawArc(outerUnSelectRectF, startAngel, finishAngel, false, unSelectStockPaint);
    }

//    private RectF circleUnSelectedRectF;
//    private RectF circleSelectedRectF;
//
//    private Paint circleUnSelectedPaint;
//    private Paint circleSelectedPaint;

    public void midStateCircle(Canvas canvas) {
        int arcCoordinate = getInitialCoordinate(0.03);

        outerUnSelectRectF.set((arcCoordinate + getPaddingLeft()), (arcCoordinate + getPaddingTop())
                , width - (arcCoordinate + getPaddingRight()),
                width - (arcCoordinate + getPaddingBottom()));

        outerSelectRectF.set(arcCoordinate + getPaddingLeft(), arcCoordinate + getPaddingTop()
                , width - (arcCoordinate + getPaddingRight()),
                width - (arcCoordinate + getPaddingBottom()));

        canvas.drawOval(outerUnSelectRectF, unSelectStockPaint);
        canvas.drawArc(outerUnSelectRectF, startAngel, finishAngel, false, unSelectStockPaint);
        canvas.drawArc(outerSelectRectF, startAngel, getProgressAngle(), false, selectStockPaint);

        int circleCoordinate = getInitialCoordinate(0.12);
        circleUnSelectedRectF.set((circleCoordinate + getPaddingLeft()), (circleCoordinate + getPaddingTop())
                , width - (circleCoordinate + getPaddingRight()),
                width - (circleCoordinate + getPaddingBottom()));

        canvas.drawOval(circleUnSelectedRectF, unSelectStockPaint);
        canvas.drawArc(circleUnSelectedRectF, startAngel, finishAngel, false, circleUnSelectedPaint);
        canvas.drawArc(circleUnSelectedRectF, startAngel, getProgressAngle(), false, circleSelectedPaint);
    }

    public void finalStateCircle(Canvas canvas) {
        int initialCo = getInitialCoordinate(0.03);
        circleUnSelectedRectF.set(initialCo + getPaddingLeft(), initialCo + getPaddingTop()
                , width - (initialCo + getPaddingRight()),
                width - (initialCo + getPaddingBottom()));

        circleSelectedRectF.set(initialCo + getPaddingLeft(), initialCo + getPaddingTop()
                , width - (initialCo + getPaddingRight()),
                width - (initialCo + getPaddingBottom()));

        canvas.drawOval(circleUnSelectedRectF, circleUnSelectedPaint);
        canvas.drawArc(circleUnSelectedRectF, startAngel, finishAngel, false, circleUnSelectedPaint);
        canvas.drawArc(circleSelectedRectF, startAngel, getProgressAngle(), false, circleSelectedPaint);
    }

    private int getInitialCoordinate(double value) {
        Double tempValue = width * value;
        int xORy = tempValue.intValue();
        return xORy;
    }

    private float getProgressAngle() {
        return progressValue * 360f / (float) max;
    }

    public void startProgress(final int targetedProgressValue) {
        progressHandler = new Handler();
        progressRunnable = new Runnable() {
            @Override
            public void run() {
                if (progressValue >= targetedProgressValue) {
                    progressHandler.removeCallbacks(progressRunnable);
                } else {
                    progressValue = progressValue + PROGRESS_STEP;
                    progressHandler.postDelayed(this, PROGRESS_DELAY);
                    invalidate();
                }
            }
        };
        progressHandler.post(progressRunnable);
    }
}
