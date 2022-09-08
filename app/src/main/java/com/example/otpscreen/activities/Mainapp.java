package com.example.otpscreen.activities;
import android.app.Application;

import sdk.chat.app.firebase.ChatSDKFirebase;
import sdk.chat.core.session.ChatSDK;

public class Mainapp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            ChatSDKFirebase.quickStart(this,"pre_1","abaaba",true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
