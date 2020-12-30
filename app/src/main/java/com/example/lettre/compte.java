package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class compte extends AppCompatActivity {
    TextView text;
    View homes ;
    Button rank ;
    Button prof ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        text = (TextView) findViewById(R.id.textView8);
        homes = findViewById(R.id.home);
//        rank = findViewById(R.id.rank);
//        prof = findViewById(R.id.profile);


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                let();

            }
        });




    }
//        homes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takeMeMain();
//            }
//        });
//
//        rank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takeMeRes();
//            }
//        });
//
//        prof.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                 takeMeLog();
//            }
//        });
//
//    }
//
//    private void takeMeLog() {
////        Intent intent = new Intent (this,login.class);
////        startActivity(intent);
//    }
//
//    private void takeMeRes() {
//        Intent intent = new Intent (this,ResultActivity.class);
//        startActivity(intent);
//    }
//
//    private void takeMeMain() {
//        Intent intent = new Intent (this,MainActivity.class);
//        startActivity(intent);
//    }

    public void let() {
        Intent intent = new Intent (this,game.class);
        startActivity(intent);

    }

}