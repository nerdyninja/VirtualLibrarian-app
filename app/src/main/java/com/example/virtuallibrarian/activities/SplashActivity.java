package com.example.virtuallibrarian;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //final Intent i = new Intent(SplashActivity.this, DetailActivity.class);
                //startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
