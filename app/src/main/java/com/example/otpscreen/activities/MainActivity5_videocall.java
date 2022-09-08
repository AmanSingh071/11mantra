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

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity5_videocall extends AppCompatActivity {
 EditText editText;
 ImageView imageView;
 Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5_videocall);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button6);
        imageView=findViewById(R.id.message);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5_videocall.this,homepageActivity.class);
                startActivity(intent);
            }
        });
        try{
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))

                    .build();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String text = editText.getText().toString();
               if (text.length() > 0) {
                   // creating a room using  JitsiMeetConferenceOptions class
                   // here .setRoom() method will set the text in room name
                   // here launch method with launch a new room to user where
                   // they can invite others too.
                   JitsiMeetConferenceOptions options
                           = new JitsiMeetConferenceOptions.Builder()
                           .setRoom(text)
                           .build();
                   JitsiMeetActivity.launch(MainActivity5_videocall.this, options);
               }
               else {
                   Toast.makeText(MainActivity5_videocall.this, "Enter The Code", Toast.LENGTH_SHORT).show();
               }
           }

       });
    }
}