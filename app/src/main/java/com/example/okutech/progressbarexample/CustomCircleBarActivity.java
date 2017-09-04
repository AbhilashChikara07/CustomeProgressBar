package com.example.okutech.progressbarexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 8/24/17
 */

public class CustomCircleBarActivity extends AppCompatActivity {

    @BindView(R.id.changeColor)
    Button changeColor;
    @BindView(R.id.circleProgressBar)
    CustomCircleBarView circleProgressBar;
    private Unbinder unbinder;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custome_circle_progress_bar);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.changeColor)
    public void changeColor(){
        circleProgressBar.startProgress(100);
    }
}
