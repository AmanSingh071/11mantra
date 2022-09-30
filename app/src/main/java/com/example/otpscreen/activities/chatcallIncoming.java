package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.example.otpscreen.databinding.ActivityChatcallIncomingBinding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.otpscreen.R;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.example.otpscreen.network.ApiService;
import com.example.otpscreen.network.Apiclient;
import com.example.otpscreen.utilities.Constants2;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatcallIncoming extends AppCompatActivity {
    ActivityChatcallIncomingBinding binding;
    ArrayList<signupdetailsmodelsastrologers> list;
public static String name;
    public static String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatcallIncomingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name= getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_NAME);
        binding.callername.setText(name);
          image= getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_IMAGE);
        Glide.with(this).load(image).into(binding.incomimgchat);

        binding.pickupcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInvitationResponse(
                        Constants2.REMOTE_MSG_INVITATION_ACCEPTED, getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITER_TOKEN)
                );
            }
        });
        binding.discall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInvitationResponse(Constants2.REMOTE_MSG_INVITATION_REJECTED, getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITER_TOKEN));
               /* Intent intent = new Intent(incomingcall.this,homepageActivity.class);
                startActivity(intent);
                finish();*/
            }
        });

    }
    private void sendInvitationResponse(String type,String receiverToken){
        try {
            JSONArray tokens=new JSONArray();
            tokens.put(receiverToken);

            JSONObject body=new JSONObject();
            JSONObject data =new JSONObject();
            data.put(Constants2.REMOTE_MSG_TYPE,Constants2.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants2.REMOTE_MSG_INVITATION_RESPONSE,type);
            body.put(Constants2.REMOTE_MSG_DATA,data);
            body.put(Constants2.REMOTE_MSG_REGISTRATION_IDS,tokens);

            sendRemoteMessage(body.toString(),type);
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
                    if (type.equals(Constants2.REMOTE_MSG_INVITATION_ACCEPTED)){
                        try { String ref="incomingchat";
                            Intent intent2=new Intent(chatcallIncoming.this, chatActivity.class);
                            String name= getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_NAME);

                            String image= getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_IMAGE);
                            intent2.putExtra("USERID2",getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM));
                            intent2.putExtra("usermy",getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM2));

                            intent2.putExtra("class",ref);
                            Toast.makeText(chatcallIncoming.this,getIntent().getStringExtra(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM).toString(),Toast.LENGTH_LONG).show();
                            ContextCompat.startActivity(chatcallIncoming.this,intent2,null);
                        }
                        catch (Exception exception) {
                            Toast.makeText(chatcallIncoming.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(chatcallIncoming.this,"Invitation Rejected",Toast.LENGTH_LONG).show();
                        finish();
                    }


                }
                else {
                    Toast.makeText(chatcallIncoming.this,response.message(),Toast.LENGTH_LONG).show();
                    finish();
                }


            }

            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Toast.makeText(chatcallIncoming.this,t.getMessage(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    private BroadcastReceiver invitatiobResponseReceiever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type=intent.getStringExtra(Constants2.REMOTE_MSG_INVITATION_RESPONSE);
            if (type!=null){
                if (type.equals(Constants2.REMOTE_MSG_INVITATION_CANCELLED)){
                    Toast.makeText(context,"Invitaion Cancelled",Toast.LENGTH_LONG).show();
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