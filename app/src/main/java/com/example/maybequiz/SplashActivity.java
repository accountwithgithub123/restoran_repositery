package com.example.maybequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView img;
    TextView tv;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img = findViewById(R.id.img);
        tv = findViewById(R.id.tv1);
        tv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.txt_anim));
        img.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_animations));
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.putExtra("name","Hassan Ali");
                intent.putExtra("age",27);
                startActivity(intent);
                finish();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable,3000);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}