package com.example.okutech.progressbarexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.tickProgressBar)
    Button tickProgressBar;
    @BindView(R.id.horizontalProgressBar)
    Button horizontalProgressBar;
    @BindView(R.id.circleProgressBar)
    Button circleProgressBar;
    @BindView(R.id.customProgressBar)
    Button customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.tickProgressBar)
    public void openTickActivity() {
        Intent intent = new Intent(this, TickProgressBarClass.class);
        startActivity(intent);
    }

    @OnClick(R.id.horizontalProgressBar)
    public void openHorizantalProgressActivity() {
        Intent intent = new Intent(MainActivity.this, HorizontalProgressBar.class);
        startActivity(intent);
    }

    @OnClick(R.id.circleProgressBar)
    public void openCircleProgressBar(){
        Intent intent = new Intent(MainActivity.this, CircleProgressBar.class);
        startActivity(intent);
    }

    @OnClick(R.id.customProgressBar)
    public void openCustomeProgressClass(){
        Intent intent  = new Intent(MainActivity.this, CustomCircleBarActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
