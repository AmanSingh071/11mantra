package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otpscreen.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity2_number extends AppCompatActivity {
Button button;
EditText editText1;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_number);
        editText1=findViewById(R.id.editTextPhone);
       // editText2=findViewById(R.id.editText);
        button=findViewById(R.id.button6);
        progressDialog=new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(MainActivity2_number.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
              // else if(editText2.getText().toString().trim().isEmpty())
                //{
                  //  Toast.makeText(MainActivity2_number.this, "Enter Name", Toast.LENGTH_SHORT).show();
                //}
                else {
                progressDialog.setMessage("Sending OTP");
                progressDialog.setCancelable(false);
                progressDialog.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + editText1.getText().toString(), 60, TimeUnit.DAYS.SECONDS,
                        MainActivity2_number.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(MainActivity2_number.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                               // progressDialog.dismiss();
                            }

                            @Override
                            public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                Intent intent=new Intent(MainActivity2_number.this,MainActivity3_otp.class);
                                intent.putExtra("phonenumber",editText1.getText().toString());
                                intent.putExtra("verificationid",VerificationId);

                                progressDialog.dismiss();
                                startActivity(intent);
                            }
                        }
                );
            }}
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}