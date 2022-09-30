package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.otpscreen.R;
import com.example.otpscreen.models.users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity6_Profile extends AppCompatActivity {
ImageView imageView;
EditText editText;
Button button;
String phone;
FirebaseAuth auth;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6_profile);
        imageView=findViewById(R.id.profile_image);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button10);
        phone=getIntent().getStringExtra("phonenumber");
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(editText.getText().toString().trim().isEmpty())
               {
                   Toast.makeText(MainActivity6_Profile.this, "Enter Name", Toast.LENGTH_SHORT).show();
               }
               else{
               String name=editText.getText().toString();
               String number=phone;
               String uid= auth.getUid();
               users user=new users(uid,name,number,100);
               database.getReference()
                       .child("user")
                       .child(auth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(MainActivity6_Profile.this, "Registered successful ", Toast.LENGTH_SHORT).show();
                       Intent intent= new Intent(MainActivity6_Profile.this,homepageActivity.class);
                       intent.putExtra("phone",phone);

                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   }
               });
           }}
       });


    }
}