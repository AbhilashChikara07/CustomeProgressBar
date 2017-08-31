package com.example.okutech.progressbarexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

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

public class HorizontalProgressBar extends AppCompatActivity {

    private Unbinder unbinder;
    @BindView(R.id.inDeterminateProgressBar)
    ProgressBar inDeterminateProgressBar;
    @BindView(R.id.determinateProgressBar)
    ProgressBar determinateProgressBar;

    private int progressBarStatus;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizantal_progress_bar);
        unbinder = ButterKnife.bind(this);
        startDeterminateThread();
    }


    private void startDeterminateThread() {
        progressBarStatus = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus < 60) {
                    progressBarStatus = progressBarStatus + 1;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            determinateProgressBar.setProgress(progressBarStatus);
                        }
                    });
                }
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
