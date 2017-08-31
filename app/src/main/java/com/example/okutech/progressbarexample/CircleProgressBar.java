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
 * @since 8/22/17
 */

public class CircleProgressBar extends AppCompatActivity {

    private Unbinder unbinder;
    private int progressStatus;
    @BindView(R.id.inDeterminateProgressBar)
    ProgressBar inDeterminateProgressBar;
    @BindView(R.id.determinateProgressBar)
    ProgressBar determinateProgressBar;
    private Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progress_bar_lay);
        unbinder = ButterKnife.bind(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                progressStatus = 0;
                while (progressStatus < 60) {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    progressStatus = progressStatus + 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            determinateProgressBar.setProgress(progressStatus);
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
