package com.example.okutech.progressbarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 8/31/17
 */

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar_activity);

        SeekBar seekBarProgress;
        seekBarProgress = (SeekBar) findViewById(R.id.seekBar_progress);
        final ProgressBar circleProgressBar = (ProgressBar) findViewById(R.id.custom_progressBar);

        seekBarProgress.setProgress((int) circleProgressBar.getProgress());
        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                    circleProgressBar.setProgressWithAnimation(i);
                else
                    circleProgressBar.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
