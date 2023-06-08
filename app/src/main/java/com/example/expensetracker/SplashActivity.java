package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.progressindicator.CircularProgressIndicator;


public class SplashActivity extends AppCompatActivity {

    int cnt;
    CircularProgressIndicator progressIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cnt = 0;
        progressIndicator = findViewById(R.id.progressBar);

        Intent i = new Intent(this, MainActivity.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(cnt <= 100)
                {
                    cnt++;
                           progressIndicator.setProgress(cnt, true);

                    try{
                        Thread.sleep(45);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }

            }
        }).start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },5000);
    }
}