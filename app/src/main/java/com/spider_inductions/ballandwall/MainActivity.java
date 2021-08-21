package com.spider_inductions.ballandwall;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity implements GameView.datatransfer {
    private static final String TAG = "MainActivity";
private TextView time;
private TextView Taps;
private long startTime = System.currentTimeMillis();
private long currentTime;
private GameView game;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = findViewById(R.id.gameview);
        game.intializeinterface(this);
        time = findViewById(R.id.gametime);
        Taps = findViewById(R.id.gametap);
    }

    @Override
    public void update(boolean stop, int taps) {

        currentTime = System.currentTimeMillis();
        time.setText("time ="+(currentTime-startTime)/1000);
        Taps.setText("taps ="+taps);
        if(stop)
        {
            int score = (int) ((currentTime-startTime)/1000 - taps);
            Log.d(TAG, "update: "+score);
            Intent intent = new Intent(this,PostGameActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }
    }
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder Builder = new AlertDialog.Builder(this);
//        Builder.setTitle("Exit");
//        Builder.setMessage("Are you sure u want to exit?");
//        Builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finishAffinity();
//            }
//        });
//        Builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        Builder.create().show();
//    }
}