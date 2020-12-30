package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class dictionnaireTest extends AppCompatActivity {
    Button btn ;
    TextView txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionnaire_test);
        //For Test
        btn = findViewById(R.id.button);
        txt= (TextView)findViewById(R.id.editText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> l = readDict();


                txt.setText(l.get(5));




            }
        });
    }

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