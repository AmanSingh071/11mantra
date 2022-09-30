package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivityIncomingcallBinding;
import com.example.otpscreen.network.ApiService;
import com.example.otpscreen.network.Apiclient;
import com.example.otpscreen.utilities.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class incomingcall extends AppCompatActivity {
    ActivityIncomingcallBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomingcallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name= getIntent().getStringExtra(Constants.REMOTE_MSG_INVITATION_MEETING_NAME);
        binding.callername.setText(name);
        String image= getIntent().getStringExtra(Constants.REMOTE_MSG_INVITATION_MEETING_IMAGE);
        Glide.with(this).load(image).into(binding.incomimgcall);
        binding.pickupcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInvitationResponse(
                        Constants.REMOTE_MSG_INVITATION_ACCEPTED, getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN)
                );
            }
        });
        binding.discall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInvitationResponse(Constants.REMOTE_MSG_INVITATION_REJECTED, getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN));
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
            data.put(Constants.REMOTE_MSG_TYPE,Constants.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE,type);
            body.put(Constants.REMOTE_MSG_DATA,data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS,tokens);

            sendRemoteMessage(body.toString(),type);
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
            public void onResponse(Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    if (type.equals(Constants.REMOTE_MSG_INVITATION_ACCEPTED)){
                         try {
                             URL serverURL=new URL("https://meet.jit.si");
                             JitsiMeetConferenceOptions.Builder builder=new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL);
                             builder.setRoom(getIntent().getStringExtra(Constants.REMOTE_MSG_INVITATION_MEETING_ROOM));


                             builder.setAudioOnly(true);

                             JitsiMeetActivity.launch(incomingcall.this,builder.build());
                             finish();
                         }
                         catch (Exception exception) {
                             Toast.makeText(incomingcall.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                             finish();
                         }
                    }
                    else {
                        Toast.makeText(incomingcall.this,"Invitation Rejected",Toast.LENGTH_LONG).show();
                        finish();
                    }


                }
                else {
                    Toast.makeText(incomingcall.this,response.message(),Toast.LENGTH_LONG).show();
                    finish();
                }


            }

            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Toast.makeText(incomingcall.this,t.getMessage(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    private BroadcastReceiver invitatiobResponseReceiever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type=intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE);
            if (type!=null){
                if (type.equals(Constants.REMOTE_MSG_INVITATION_CANCELLED)){
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
                invitatiobResponseReceiever,new IntentFilter(Constants.REMOTE_MSG_INVITATION_RESPONSE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(invitatiobResponseReceiever);
    }
}