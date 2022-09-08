package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.otpscreen.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   Button button;
   FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button=findViewById(R.id.button);
         auth=FirebaseAuth.getInstance();

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(auth.getCurrentUser()!=null)
                 {
                     Intent intent =new Intent(MainActivity.this,MainActivity_backup.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(intent);
                 }
                 else{
                 Intent intent =new Intent(MainActivity.this,MainActivity2_number.class);
                 startActivity(intent);
             }}
         });
    }
}