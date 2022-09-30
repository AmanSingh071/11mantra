package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivityChatcallOutGoingBinding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.example.otpscreen.network.ApiService;
import com.example.otpscreen.network.Apiclient;
import com.example.otpscreen.utilities.Constants;
import com.example.otpscreen.utilities.Constants2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatcallOutGoing extends AppCompatActivity {
    String meetingRoom=null;
    int position=0;
    public static String name;
    public static String image;
    String  userid;
    public static String u;
    ArrayList<signupdetailsmodelsastrologers> list;
    ActivityChatcallOutGoingBinding binding;
    String inviterToken=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatcallOutGoingBinding.inflate(getLayoutInflater());
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
        Toast.makeText(chatcallOutGoing.this,userid.toString(),Toast.LENGTH_LONG).show();
        switch (extras.getString("class"))
        {
            case  "chat":
                list=new ArrayList<signupdetailsmodelsastrologers>();
                list.addAll(mainhomescreenfrag.list2);

                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful() && task.getResult()!=null){
                            inviterToken=task.getResult();
                            initiateMeeting(userid );
                        }
                        else
                        {
                            Toast.makeText(chatcallOutGoing.this,"yep1",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                setplayerlayout();
                break;

        }


    }

    private void setplayerlayout() {

        binding.callername.setText(list.get(position).getUsername());
        Glide.with(this).load(list.get(position).getProImg()).into(binding.outgoingimg);
        name=list.get(position).getUsername();
        image=list.get(position).getProImg();



    }


    private void initiateMeeting(String receiverToken){
        try{
            JSONArray tokens=new JSONArray();
            tokens.put(receiverToken);

            JSONObject body=new JSONObject();
            JSONObject data=new JSONObject();

            data.put(Constants2.REMOTE_MSG_TYPE,Constants2.REMOTE_MSG_INVITATION);
            data.put(Constants2.REMOTE_MSG_INVITER_TOKEN,inviterToken);

            data.put(Constants2.REMOTE_MSG_INVITATION_MEETING_TYPE,"chat");
            Bundle extras=getIntent().getExtras();
            meetingRoom = extras.getString("USERID");

            data.put(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM,meetingRoom);
            data.put(Constants2.REMOTE_MSG_INVITATION_MEETING_NAME,list.get(position).getUsername());
            data.put(Constants2.REMOTE_MSG_INVITATION_MEETING_IMAGE,list.get(position).getProImg());
            data.put(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM2,FirebaseAuth.getInstance().getCurrentUser().getUid());

            body.put(Constants2.REMOTE_MSG_DATA,data);
            body.put(Constants2.REMOTE_MSG_REGISTRATION_IDS,tokens);

            Toast.makeText(this,"yep2",Toast.LENGTH_LONG).show();
            sendRemoteMessage(body.toString(),Constants2.REMOTE_MSG_INVITATION);

        }
        catch (Exception exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void sendRemoteMessage(String remoteMessageBody,String type){

        Apiclient.getClient().create(ApiService.class).sendremotemessage(
                Constants2.getRemoteMessageHeaders(),remoteMessageBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {


                    if (type.equals(Constants2.REMOTE_MSG_INVITATION)) {
                        Toast.makeText(chatcallOutGoing.this, "Invitation sent successfully", Toast.LENGTH_LONG).show();
                    }
                    else if(type.equals(Constants2.REMOTE_MSG_INVITATION_RESPONSE)){
                        Toast.makeText(chatcallOutGoing.this,"Invitation Cancelled",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                else {
                    Toast.makeText(chatcallOutGoing.this,response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Toast.makeText(chatcallOutGoing.this,t.getMessage(),Toast.LENGTH_LONG).show();
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
            data.put(Constants2.REMOTE_MSG_TYPE,Constants2.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants2.REMOTE_MSG_INVITATION_RESPONSE,Constants2.REMOTE_MSG_INVITATION_CANCELLED);
            body.put(Constants2.REMOTE_MSG_DATA,data);
            body.put(Constants2.REMOTE_MSG_REGISTRATION_IDS,tokens);

            sendRemoteMessage(body.toString(),Constants2.REMOTE_MSG_INVITATION_RESPONSE);
        }
        catch (Exception exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }

    }
    private BroadcastReceiver invitatiobResponseReceiever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type=intent.getStringExtra(Constants2.REMOTE_MSG_INVITATION_RESPONSE);
            if (type!=null){
                if (type.equals(Constants2.REMOTE_MSG_INVITATION_ACCEPTED)){
                    try {
                       String ref="outgoingchat";
                        Intent intent2=new Intent(context, chatActivity.class);


                        intent2.putExtra("USERID2",meetingRoom);

                        intent2.putExtra("class",ref);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);


                    }
                    catch (Exception exception){
                        Toast.makeText(context,exception.getMessage(),Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else if (type.equals(Constants2.REMOTE_MSG_INVITATION_REJECTED)){
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
                invitatiobResponseReceiever,new IntentFilter(Constants2.REMOTE_MSG_INVITATION_RESPONSE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(invitatiobResponseReceiever);
    }
}