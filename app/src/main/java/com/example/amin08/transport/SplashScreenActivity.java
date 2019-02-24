package com.example.amin08.transport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.animation.AnimationUtils;

public class SplashScreenActivity extends AppCompatActivity {

    AppCompatImageView logo;
    AppCompatTextView transport , description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo        = findViewById(R.id.logo);
        transport   = findViewById(R.id.transport);
        description = findViewById(R.id.description);

        logo.startAnimation(AnimationUtils.loadAnimation( this ,R.anim.fade_in));
        transport.startAnimation(AnimationUtils.loadAnimation( this ,R.anim.fade_in));
        description.startAnimation(AnimationUtils.loadAnimation( this ,R.anim.fade_in));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this , EnterActivity.class));
                finish();
            }
        },4000);
    }
}
