package com.example.leveled1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpActivity extends AppCompatActivity {

    TextInputEditText editTextName,editTextEmail;
    EditText editTextPasswords;
    Button buttonReg;
    FirebaseAuth mAuth;
    TextView SigntoLog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        mAuth= FirebaseAuth.getInstance();
        editTextName=findViewById(R.id.firstNameS);
        editTextEmail=findViewById(R.id.EmailS);
        editTextPasswords=findViewById(R.id.PasswordS);
        buttonReg=findViewById(R.id.mainsignup);
        SigntoLog=findViewById(R.id.SignupToLogin);

        SigntoLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity1.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fname,email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPasswords.getText());
                Fname=String.valueOf(editTextName.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(signUpActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(signUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Fname)){
                    Toast.makeText(signUpActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signUpActivity.this, "Account created",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}