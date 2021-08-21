package com.spider_inductions.ballandwall;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreGameActivity extends AppCompatActivity {
private TextView highscore;
private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        btn_start = findViewById(R.id.start);
        highscore = findViewById(R.id.highscore);
        SharedPreferences prefs=getApplicationContext().getSharedPreferences("ballandwall",MODE_PRIVATE);
        highscore.setText("highscore ="+prefs.getInt("highscore", 0));
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreGameActivity.this,MainActivity.class));
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