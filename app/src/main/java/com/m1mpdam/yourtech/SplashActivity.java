package com.m1mpdam.yourtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openNextScreen();
                finish();
            }
        }, Constants.SPLASH_DELAY);
    }

    private void openNextScreen() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        boolean is_connected = sharedPref.getBoolean(Constants.PREF_IS_CONNECTED,false);
        if (is_connected){
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
        else{
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        }
    }
}