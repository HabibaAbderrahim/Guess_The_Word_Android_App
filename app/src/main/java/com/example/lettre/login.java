package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private Button button;
    private Button button1;
    EditText FullName ,Email,Password;
    Button submit;
    Button LoginBtn;
    FirebaseAuth fAuth;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button =(Button) findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createAccount();

            }
        });
        FullName= findViewById(R.id.nameBox);
        Email=findViewById(R.id.emailBox);
        Password=findViewById(R.id.passwordBox);
        submit=findViewById(R.id.submitBtn);
        LoginBtn=findViewById(R.id.loginBtn);
        fAuth= FirebaseAuth.getInstance();
        button1 =(Button) findViewById(R.id.submitBtn);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToGame();

            }
        });


        submit.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email =Email.getText().toString().trim();
                String pass=Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                else if (TextUtils.isEmpty(pass)){
                    Password.setError("Password is required");
                    return;
                }
                else if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(login.this,"fields is empty ",Toast.LENGTH_LONG).show();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //succes or no
                        if(task.isSuccessful()){
                            Toast.makeText(login.this,"loged in succefuly ",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),compte.class));

                        }
                        else {
                            Toast.makeText(login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }



        });

    }
    public void  createAccount(){
        Intent intent = new Intent(this,account.class);
        startActivity(intent);

    }
    public void goToGame() {
        Intent intent = new Intent (this,compte.class);
        startActivity(intent);

    }


}