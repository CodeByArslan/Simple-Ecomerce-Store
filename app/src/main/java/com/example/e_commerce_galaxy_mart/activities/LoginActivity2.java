package com.example.e_commerce_galaxy_mart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce_galaxy_mart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity2 extends AppCompatActivity {

    EditText email,password;
    Button signin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        auth= FirebaseAuth.getInstance();

        email= findViewById(R.id.email);
        password= findViewById(R.id.password);

        signin= findViewById(R.id.button5);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  email12=email.getText().toString();
                String passs1= password.getText().toString();

                if (email12.isEmpty()){
                    Toast.makeText(LoginActivity2.this, "email is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passs1.isEmpty()){
                    Toast.makeText(LoginActivity2.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passs1.length()<6){
                    Toast.makeText(LoginActivity2.this, "Password is too short should be >6 ", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email12,passs1)
                        .addOnCompleteListener(LoginActivity2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity2.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity2.this,MainActivity.class));

                                }
                                else {
                                    Toast.makeText(LoginActivity2.this, "Seems like Error"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });



    }

    public void signin() {

      String  email1=email.getText().toString();
      String passs1= password.getText().toString();

//      if (email1.isEmpty()){
//          Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
//          return;
//      }
//
//        if (passs1.isEmpty()){
//            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (passs1.length()<6){
//            Toast.makeText(this, "Password is too short should be >6 ", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        auth.signInWithEmailAndPassword(email1,passs1)
//                        .addOnCompleteListener(LoginActivity2.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()){
//                                    Toast.makeText(LoginActivity2.this, "Welcome", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(LoginActivity2.this,MainActivity.class));
//
//                                }
//                                else {
//                                    Toast.makeText(LoginActivity2.this, "Seems like Error"+task.getException(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });



    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity2.this,RegistrationActivity2.class));

    }
}