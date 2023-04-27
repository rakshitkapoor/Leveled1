package com.example.leveled1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expense_manager.activity_expense;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Dashboard extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);

        user=findViewById(R.id.user);

        Intent intent = getIntent();
        int variableValue = intent.getIntExtra("count",0);

        Intent intent2 = getIntent();
        String variableValue2 =intent2.getStringExtra("email");

        if (variableValue==1){
            if(acc!=null){
                String personName=acc.getDisplayName();
                user.setText(personName);

            }
        }

    }







    public void todo(View view){
        Intent intent=new Intent(Dashboard.this, todo_main.class);
        startActivity(intent);
    }

    public void profile(View view){
        Intent intent=new Intent(Dashboard.this, profile.class);
        intent.putExtra("count",1);
        intent.putExtra("email","");
        startActivity(intent);
    }

    public void expense(View view){
        Intent intent=new Intent(Dashboard.this, activity_expense.class);
        startActivity(intent);
    }




}