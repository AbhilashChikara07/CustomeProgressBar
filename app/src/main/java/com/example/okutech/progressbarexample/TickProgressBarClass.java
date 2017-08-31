package com.example.okutech.progressbarexample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 8/16/17
 */

public class TickProgressBarClass extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.image1)
    ImageView imageView1;
    @BindView(R.id.image2)
    ImageView imageView2;
    @BindView(R.id.image3)
    ImageView imageView3;
    @BindView(R.id.image4)
    ImageView imageView4;
    @BindView(R.id.image5)
    ImageView imageView5;
    @BindView(R.id.image6)
    ImageView imageView6;
    @BindView(R.id.image7)
    ImageView imageView7;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tick_progress_bar_lay);
        unbinder = ButterKnife.bind(this);

        countDownTimer = new CountDownTimer(7000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished / 1000;
                Log.e("progress",""+progress);
                switch (progress) {
                    case 0:
                        imageView7.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 1:
                        imageView6.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 2:
                        imageView5.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 3:
                        imageView4.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 4:
                        imageView3.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 5:
                        imageView2.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                    case 6:
                        imageView1.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorPrimary));
                }
            }

            @Override
            public void onFinish() {
                setImageAtInitialLabel();
                countDownTimer.start();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setImageAtInitialLabel() {
        imageView1.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView2.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView3.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView4.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView5.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView6.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
        imageView7.setBackgroundColor(ContextCompat.getColor(TickProgressBarClass.this, R.color.colorAccent));
    }
}
