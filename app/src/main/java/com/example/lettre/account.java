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

public class account extends AppCompatActivity {
    EditText FullName ,Email,Password;
    Button submit;
    Button LoginBtn;
    FirebaseAuth fAuth;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        button =(Button) findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                haveAccount();
            }

        });


        FullName= findViewById(R.id.nameBox);
        Email=findViewById(R.id.emailBox);
        Password=findViewById(R.id.passwordBox);
        submit=findViewById(R.id.submitBtn);
        LoginBtn=findViewById(R.id.loginBtn);
        fAuth= FirebaseAuth.getInstance();
       /* if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        submit.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email =Email.getText().toString().trim();
                String pass=Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Password.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    Password.setError("password must be >= 6 caractéres");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //succes or no
                        if(task.isSuccessful()){
                            Toast.makeText(account.this,"User Created , Please choose game Mode",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),compte.class));

                        }
                        else {
                            Toast.makeText(account.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }



        });


    }
    public void haveAccount(){
        Intent intent = new Intent (this,login.class);
        startActivity(intent);}
}