package com.example.leveled1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LoginSignupActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_signup1);
    }

    public void nextL(View view){
        Intent intent=new Intent(LoginSignupActivity1.this, LoginActivity1.class);
        startActivity(intent);
    }
    public void nextS(View view){
        Intent intent=new Intent(LoginSignupActivity1.this, signUpActivity.class);
        startActivity(intent);
    }
}