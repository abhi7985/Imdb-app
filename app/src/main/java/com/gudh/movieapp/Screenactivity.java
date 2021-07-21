package com.gudh.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Screenactivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenactivity);
        img = (ImageView) findViewById(R.id.icon_screen);
        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(img);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Screenactivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}