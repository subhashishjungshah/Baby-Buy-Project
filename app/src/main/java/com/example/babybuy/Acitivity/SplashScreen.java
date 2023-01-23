package com.example.babybuy.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babybuy.R;

public class SplashScreen extends AppCompatActivity {
    ImageView imgview;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //invoking animate function
        animateImage();
        animateText();
        Intent iSplash = new Intent(SplashScreen.this, LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(iSplash);
                finish();
            }
        }, 5000);
    }

    //creating animation for splash screen
    private void animateImage(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fade);
        imgview = findViewById(R.id.splashlogo);
        anim.reset();
        imgview.clearAnimation();
        imgview.setAnimation(anim);
    }
    private void animateText(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fade);
        textView = findViewById(R.id.copyrights);
        anim.reset();
        textView.clearAnimation();
        textView.setAnimation(anim);
    }

}