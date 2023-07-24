package com.example.leveled1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name;
    TextView mail;
    ImageView picture;

    Button g_out;
    Button m_out;
    String email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(getIntent());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        name=findViewById(R.id.user_name);
        mail=findViewById(R.id.user_mail);
        picture=findViewById(R.id.imageView4);
        g_out=findViewById(R.id.google_out);
        m_out=findViewById(R.id.manual_out);


        Intent intent = getIntent();
        Intent intent2=getIntent();
        int variableValue = intent.getIntExtra("count",0);
        String variableValue2=intent2.getStringExtra("email");

        if (variableValue==1){
            if(acc!=null){
                String personName=acc.getDisplayName();
                String personEmail=acc.getEmail();
                name.setText(personName);
                mail.setText(personEmail);

            }
        }
        else {
            email= variableValue2;
            mail.setText(email);

        }

        if (variableValue == 1) {
            g_out.setVisibility(View.VISIBLE);
            g_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signout_g();
                }
            });
        }
        else {
            m_out.setVisibility(View.VISIBLE);
            m_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signout_m();
                }
            });
        }
    }

    void signout_g(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                finish();
                startActivity(new Intent(profile.this,LoginActivity1.class));
            }
        });
    }

    void signout_m(){

        FirebaseAuth.getInstance().signOut();
    }
}