package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Dictionnaire extends AppCompatActivity {
    List<String> dict = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionnaire);


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