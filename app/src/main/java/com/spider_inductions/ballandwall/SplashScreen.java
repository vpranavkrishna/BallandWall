package com.spider_inductions.ballandwall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private boolean onbackpressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splashscreen);
    ImageView img = findViewById(R.id.imageView);
    Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);
        img.startAnimation(slideAnimation);
    Intent intent = new Intent(this,PreGameActivity.class);
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            if(!onbackpressed)
                startActivity(intent);
            finish();
        }
    },3000);
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onbackpressed = true;
    }
}
