package com.example.e_commerce_galaxy_mart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistrationActivity2 extends AppCompatActivity {

    EditText name,email,password;
    private FirebaseAuth auth;
    Button signup;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
//        getSupportActionBar().hide();
        auth= FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!= null){
            startActivity(new Intent(RegistrationActivity2.this,MainActivity.class));
            finish();
        }

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.button4);

        sharedPreferences = getSharedPreferences("onBoardScreen",MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

        if (isFirstTime){
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent intent= new Intent(RegistrationActivity2.this,OnBoardingActivity.class);
            startActivity(intent);
            finish();


        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                signup();

                String name1 =name.getText().toString();
                String email1 =email.getText().toString();
                String pass1 =password.getText().toString();


//                if (name1.isEmpty()|| email1.isEmpty()||pass1.isEmpty()){
//                    Toast.makeText(RegistrationActivity2.this, "all fields are required", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                if (TextUtils.isEmpty(name1)){
//                    Toast.makeText(RegistrationActivity2.this, "Name is r", Toast.LENGTH_SHORT).show();
//
//                }



                if (name1.isEmpty()){
                    Toast.makeText(RegistrationActivity2.this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
                }


                if (email1.isEmpty()){
                    Toast.makeText(RegistrationActivity2.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass1.isEmpty()){
                    Toast.makeText(RegistrationActivity2.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass1.length()<6){
                    Toast.makeText(RegistrationActivity2.this, "Password is too short should be >6 ", Toast.LENGTH_SHORT).show();
                    return;
                }

//                startActivity(new Intent(RegistrationActivity2.this,LoginActivity2.class));

                auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(RegistrationActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity2.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity2.this,LoginActivity2.class));
                        }
                        else {
                            Toast.makeText(RegistrationActivity2.this, "Registration Faild"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }

    public void signin(View view) {



        startActivity(new Intent(RegistrationActivity2.this,LoginActivity2.class));

    }

//    public void signup() {
//
//
//
//
//
//        String name1 =name.getText().toString();
//        Toast.makeText(this, "" + name, Toast.LENGTH_SHORT).show();
//        String email1 = email.getText().toString();
//        String password1 = password.getText().toString();
//
//        if (name1.isEmpty()){
//            Toast.makeText(this,"naem is rees",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//
//
//
//
//
//        String userName = name.getText().toString();
//        String userEmail = email.getText().toString();
//        String userPassword = password.getText().toString();
//
//        if (userName==""){
//
//            Toast.makeText(this,"Enter Name!", Toast.LENGTH_SHORT).show();
//        }
//
//        if (userEmail.isEmpty()){
//
//            Toast.makeText(this,"Enter Email!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (userPassword.isEmpty()){
//
//            Toast.makeText(this,"Enter Password!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (userPassword.length()< 6){
//            Toast.makeText(this,"Password Too Short!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//
//        startActivity(new Intent(RegistrationActivity2.this,LoginActivity2.class));
//    }
}