package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.example.otpscreen.databinding.ActivityOngoingcall2Binding;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.example.otpscreen.network.ApiService;
import com.example.otpscreen.network.Apiclient;
import com.example.otpscreen.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class outgoingcall extends AppCompatActivity {
    int position=0;
    String  userid;
    String meetingRoom=null;
    ArrayList<signupdetailsmodelsastrologers> list;
    ActivityOngoingcall2Binding binding;
  PreferenceManager preferenceManager;
    String inviterToken=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngoingcall2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.disbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras=getIntent().getExtras();
                userid = extras.getString("ID");
              cancelInvitaion(userid);
            }
        });

        initializelayout();

    }
    private void initializelayout() {
        Bundle extras=getIntent().getExtras();

        position = extras.getInt("index",0);
        userid = extras.getString("ID");
        Toast.makeText(outgoingcall.this,userid.toString(),Toast.LENGTH_LONG).show();
        switch (extras.getString("class"))
        {
            case  "uploadAdapter":
                list=new ArrayList<signupdetailsmodelsastrologers>();
                list.addAll(mainhomescreenfrag.list2);

                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful() && task.getResult()!=null){
                            inviterToken=task.getResult();
                            initiateMeeting(userid );
                        }
                    }
                });

                setplayerlayout();
                break;

        }


    }

    private void setplayerlayout() {

       binding.callernameout.setText(list.get(position).getUsername());
        Glide.with(this).load(list.get(position).getProImg()).into(binding.outgoingimgcall);



    }
    private void initiateMeeting(String receiverToken){
        try{
            JSONArray tokens=new JSONArray();
            tokens.put(receiverToken);
            JSONObject body=new JSONObject();
            JSONObject data=new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE,Constants.REMOTE_MSG_INVITATION);
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN,inviterToken);
            data.put(Constants.REMOTE_MSG_INVITATION_MEETING_TYPE,"call");


            Bundle extras=getIntent().getExtras();

            data.put(Constants.REMOTE_MSG_INVITATION_MEETING_NAME,list.get(position).getUsername());
            data.put(Constants.REMOTE_MSG_INVITATION_MEETING_IMAGE,list.get(position).getProImg());
            meetingRoom = extras.getString("USERID");

       data.put(Constants.REMOTE_MSG_INVITATION_MEETING_ROOM,meetingRoom);

            body.put(Constants.REMOTE_MSG_DATA,data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS,tokens);

            sendRemoteMessage(body.toString(),Constants.REMOTE_MSG_INVITATION);
        }
        catch (Exception exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }
    }
    public void sendRemoteMessage(String remoteMessageBody,String type){
        Apiclient.getClient().create(ApiService.class).sendremotemessage(
                Constants.getRemoteMessageHeaders(),remoteMessageBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call,@NonNull Response<String> response) {
                if (response.isSuccessful()) {


                    if (type.equals(Constants.REMOTE_MSG_INVITATION)) {
                        Toast.makeText(outgoingcall.this, "Invitation sent successfully", Toast.LENGTH_LONG).show();
                    }
                    else if(type.equals(Constants.REMOTE_MSG_INVITATION_RESPONSE)){
                        Toast.makeText(outgoingcall.this,"Invitation Cancelled",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                else {
                    Toast.makeText(outgoingcall.this,response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Toast.makeText(outgoingcall.this,t.getMessage(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    private void cancelInvitaion(String receiverToken){
        try {
            JSONArray tokens=new JSONArray();
            tokens.put(receiverToken);

            JSONObject body=new JSONObject();
            JSONObject data =new JSONObject();
            data.put(Constants.REMOTE_MSG_TYPE,Constants.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE,Constants.REMOTE_MSG_INVITATION_CANCELLED);
            body.put(Constants.REMOTE_MSG_DATA,data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS,tokens);

            sendRemoteMessage(body.toString(),Constants.REMOTE_MSG_INVITATION_RESPONSE);
        }
        catch (Exception exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }

    }
    private BroadcastReceiver invitatiobResponseReceiever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type=intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE);
            if (type!=null){
                if (type.equals(Constants.REMOTE_MSG_INVITATION_ACCEPTED)){
                   try {
                       URL serverURL=new URL("https://meet.jit.si");
                       JitsiMeetConferenceOptions.Builder builder=new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL);
                       builder.setRoom(meetingRoom);


                       builder.setAudioMuted(true);
                       builder.setVideoMuted(true);


                       JitsiMeetActivity.launch(outgoingcall.this,builder.build());
                       finish();

                   }
                   catch (Exception exception){
                       Toast.makeText(context,exception.getMessage(),Toast.LENGTH_LONG).show();
                       finish();
                   }
                }
                else if (type.equals(Constants.REMOTE_MSG_INVITATION_REJECTED)){
                    Toast.makeText(context,"Invitaion rejected",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                invitatiobResponseReceiever,new IntentFilter(Constants.REMOTE_MSG_INVITATION_RESPONSE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(invitatiobResponseReceiever);
    }
}