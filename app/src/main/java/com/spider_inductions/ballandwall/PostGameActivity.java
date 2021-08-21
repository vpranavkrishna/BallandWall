package com.spider_inductions.ballandwall;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostGameActivity extends AppCompatActivity {
    private static final String TAG = "PostGameActivity";
private int score;
private TextView Score;
private TextView highscore;
private Button restart;
private int newhighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);
        Score = findViewById(R.id.score);
        highscore = findViewById(R.id.highscore);
        restart = findViewById(R.id.restart);
        newhighscore = getIntent().getIntExtra("score",0);
        Log.d(TAG, "onCreate: ");
         Score.setText("score ="+newhighscore);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("ballandwall", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        score = prefs.getInt("highscore", score);
        if (score <= newhighscore) {
            editor.putInt("highscore", newhighscore);
            editor.apply();
            highscore.setText("HIGHSCORE =" + newhighscore);

        } else {
            highscore.setText("HIGHSCORE =" + score);
        }
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostGameActivity.this,MainActivity.class));
                finish();
            }
        });
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