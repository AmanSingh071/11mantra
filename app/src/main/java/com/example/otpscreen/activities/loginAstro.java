package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.otpscreen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginAstro extends AppCompatActivity {
    EditText email,pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_astro);
       findViewById(R.id.btnsignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signinuser();
            }
        });
    }

    private void signinuser() {
        email=findViewById(R.id.emailsignin);
        pass=findViewById(R.id.passwordsignin);




        String email1 = email.getText().toString();
        String pass1= pass.getText().toString();

        auth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(loginAstro.this,"success",Toast.LENGTH_LONG).show();




                }
                else
                {
                    Toast.makeText(loginAstro.this,"failed",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}