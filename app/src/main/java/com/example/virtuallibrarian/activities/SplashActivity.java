package com.example.virtuallibrarian.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.virtuallibrarian.R;
import com.example.virtuallibrarian.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {
    public static final int SPLASH_TIME_OUT = 2000;
        SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.v("in","splash");
        session = new SessionManager(SplashActivity.this);
        Log.v("t",(session.getIsLogin()));
        String isLogin = session.getIsLogin();
        if(isLogin.contains("true"))
        {
            Log.v("in","splashFirstIf");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        else
        {
            Log.v("in","splashSecondIf");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }
}
