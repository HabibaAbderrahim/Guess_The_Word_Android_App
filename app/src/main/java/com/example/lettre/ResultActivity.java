package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lettre.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int currentScore = getIntent().getIntExtra("correct" , 0);
        //int TotalScore = getIntent().getIntExtra("correct" , 1000);


        binding.textView13.setText(String.format("%d/23067" , currentScore));

    }

    public void replay(View view) {

        Intent intent = new Intent(this,compte.class);
        startActivity(intent);

    }
}