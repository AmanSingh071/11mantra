package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.otpscreen.R;

import sdk.chat.app.firebase.ChatSDKFirebase;
import sdk.chat.core.session.ChatSDK;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            ChatSDKFirebase.quickStart(this,"pre_1","abaaba",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ChatSDK.ui().startSplashScreenActivity(this);
    }
}