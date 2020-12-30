package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class game extends AppCompatActivity {
    //vars
    TextView correctAnswer , rightAnswer , questContainer ;
    EditText etUserInput ;
    Button btCheck , btShow , btNext ;
    //for timer
    private boolean timeRuning;
    TextView time;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilleseconds=60000; //
    //For score
    TextView scores ;
    //for random
    String mot;
    Random random;

    //Score
    int currentScore=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //time
        time=findViewById(R.id.textView10);
        //Score
        scores = findViewById(R.id.textView13);
        correctAnswer = findViewById(R.id.txtCorrectAnswer);
        rightAnswer = findViewById(R.id.txtRightAnswer);
        questContainer = findViewById(R.id.textQuestionContainer);
        etUserInput = findViewById(R.id.etUserInput);
        btCheck = findViewById(R.id.btCheck);
        btShow = findViewById(R.id.btShow);
        btNext = findViewById(R.id.btnext);
        random = new Random();

        //List of dict remplie gce au method readDict
        ArrayList<String> List1 = new ArrayList<>();
        List1 = readDict();

        //Choix d'un mot random de la liste list1 et l'application de method mixWord pour yshufflitih

        mot = List1.get(random.nextInt(List1.size()));
        questContainer.setText(mixword(mot).toUpperCase());

        //check
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                startTime();
            }
        });

        //Next
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
                startTime();
            }

        });

        //show
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer.setVisibility(View.VISIBLE);
                rightAnswer.setVisibility(View.VISIBLE);
                //mot sans la methode mixWords
                rightAnswer.setText(mot);


            }
        });


    }

     //next
    private void nextQuestion() {

        ///***************************************//
        ArrayList<String> List1 = new ArrayList<>();
        List1 = readDict();
        mot = List1.get(random.nextInt(List1.size()));
        questContainer.setText(mixword(mot).toUpperCase());
        //*******************************************///

        etUserInput.setText("");
        correctAnswer.setVisibility(View.INVISIBLE);
        rightAnswer.setVisibility(View.INVISIBLE);

    }

    //Timing

    private void startTime() {
        countDownTimer = new CountDownTimer(timeLeftInMilleseconds , 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilleseconds = l ;
                updateTimer();

            }

            @Override
            public void onFinish() {

            }
        }.start();
        timeRuning = true ;

    }

    private void updateTimer() {
        int min = (int) (timeLeftInMilleseconds / 60000);
        int sec = (int) (timeLeftInMilleseconds % 60000 / 1000);
        String timeLedtTxt ;
        timeLedtTxt = "" + min;
        timeLedtTxt += ":" ;
        //  09
        if (sec < 10) timeLedtTxt +="0" ;
        timeLedtTxt += sec ;
        time.setText(timeLedtTxt);

        //TIME OUT
        if (sec == 00){
            Toast.makeText(this, "Time out please come again !!!", Toast.LENGTH_SHORT).show();
            //u want be able To continue
//             etUserInput.setFocusable(false);
//             etUserInput.setEnabled(false);
             //Take me to see my results
             Intent intent = new Intent (this,ResultActivity.class);
             //Take with u extra vars + ActivityResultBinding
             intent.putExtra("correct" , currentScore);
             //intent.putExtra("total" , 1000);
             startActivity(intent);
        }

    }

    //Check and add score
    private void checkAnswer() {
        if (etUserInput.getText().toString().equalsIgnoreCase(mot)) {

            currentScore ++ ;


            Toast.makeText(game.this, "Correct Answer", Toast.LENGTH_SHORT).show();


        }
        else {
            Toast.makeText(game.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        }
    }

    //fonction mixword appliqué sur un mot du dictionnaire dans list1 pris en Random a chaque fois

    private String mixword(String word) {
        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed="";
    for (String i:words){
        mixed+=i;
    }
    return mixed;
    }


    //raw  dictionnaire
    private ArrayList readDict() {
        InputStream is = getResources().openRawResource(R.raw.liste_francais);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        ArrayList<String> ListWords = new ArrayList<>();



        String line = "";


        try {
            while ((line = reader.readLine()) != null) {
                ListWords.add(line);
            }

        } catch (IOException e) {
            Log.wtf("MyActivity", "ERROR reading data file on line" + line, e);
            e.printStackTrace();
        }

        return ListWords;

    }

    }


