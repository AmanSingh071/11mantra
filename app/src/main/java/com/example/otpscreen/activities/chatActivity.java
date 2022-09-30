package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.otpscreen.Adapters.homeblogadapter;
import com.example.otpscreen.Adapters.messageAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.blogmodel;
import com.example.otpscreen.models.chatModel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.example.otpscreen.network.ApiService;
import com.example.otpscreen.network.Apiclient;
import com.example.otpscreen.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class
chatActivity extends AppCompatActivity {
    public  ArrayList<chatModel> listmes= new ArrayList();;
    ImageView sendbtn;
    EditText messBox;
    String senderRoom=null;
    String receiverRoom=null;
    Integer position;
    String meetingRoom=null;
    public static String name;
    public static String image;
    String inviterToken=null;
    DatabaseReference mDbRef;
    signupdetailsmodelsastrologers Signupdetailsmodelsastrologers;
    chatModel Chatmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        ImageView backbtn;
        ShapeableImageView userimg;
        TextView username;
        backbtn=findViewById(R.id.backbtn);
        userimg=findViewById(R.id.chatuserimg);
        username=findViewById(R.id.chatusername);
        String usernamemy = chatcallOutGoing.name;
        if (usernamemy==null){
            Toast.makeText(this,"yes nullrrrrrr",Toast.LENGTH_LONG).show();

        }
        String userimage = chatcallOutGoing.image;
        username.setText(usernamemy);
        Glide.with(this).load(userimage).into(userimg);
        Bundle extras=getIntent().getExtras();

        switch (extras.getString("class"))
        {
            case  "outgoingchat": {


                String receiveruid = extras.getString("USERID2");
                String senderuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                senderRoom = receiveruid + senderuid;
                receiverRoom = senderuid + receiveruid;
                sendbtn=findViewById(R.id.sendbtn);
                messBox=findViewById(R.id.messbox);
                mDbRef= FirebaseDatabase.getInstance().getReference();

                mDbRef.child("chats").child(senderRoom).child("messages").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listmes.clear();

                        if (snapshot.exists()) {

                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String ma=userSnapshot.child("message").getValue().toString();
                                System.out.println(ma);

                                chatModel user= userSnapshot.getValue(chatModel.class);
                                listmes.add(user);
                            }  RecyclerView recyview3=(RecyclerView) findViewById(R.id.chatrecyv);
                            messageAdapter MessageAdapter=new messageAdapter(chatActivity.this,listmes);
                            recyview3.setLayoutManager(new LinearLayoutManager(chatActivity.this));
                            recyview3.setAdapter(MessageAdapter);
                            MessageAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                sendbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String message1=messBox.getText().toString();
                        Chatmodel=new chatModel();
                        Chatmodel.setMessage(message1);
                        Chatmodel.setSenderId(senderuid);

                        mDbRef.child("chats").child(senderRoom).child("messages").push().setValue(Chatmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                mDbRef.child("chats").child(receiverRoom).child("messages").push().setValue(Chatmodel);

                            }
                        });
                        messBox.setText("");



                    }
                });
                RecyclerView recyview3=(RecyclerView) findViewById(R.id.chatrecyv);
                messageAdapter MessageAdapter=new messageAdapter(chatActivity.this,listmes);
                recyview3.setLayoutManager(new LinearLayoutManager(chatActivity.this));
                recyview3.setAdapter(MessageAdapter);
                MessageAdapter.notifyDataSetChanged();
                break;

            }
            case "incomingchat":{

               String uid=extras.getString("usermy");
                String receiveruid = uid;
                if (uid==null){
                    Toast.makeText(chatActivity.this,"it is null",Toast.LENGTH_LONG).show();
                }

                String senderuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                senderRoom = receiveruid + senderuid;
                receiverRoom = senderuid + receiveruid;
                sendbtn=findViewById(R.id.sendbtn);
                messBox=findViewById(R.id.messbox);
                mDbRef= FirebaseDatabase.getInstance().getReference();

                mDbRef.child("chats").child(senderRoom).child("messages").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listmes.clear();

                        if (snapshot.exists()) {

                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String ma=userSnapshot.child("message").getValue().toString();
                                System.out.println(ma);

                                chatModel user= userSnapshot.getValue(chatModel.class);
                                listmes.add(user);
                            }
                            RecyclerView recyview3=(RecyclerView) findViewById(R.id.chatrecyv);
                            messageAdapter MessageAdapter=new messageAdapter(chatActivity.this,listmes);
                            recyview3.setLayoutManager(new LinearLayoutManager(chatActivity.this));
                            recyview3.setAdapter(MessageAdapter);
                            MessageAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                sendbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String message1=messBox.getText().toString();
                        Chatmodel=new chatModel();
                        Chatmodel.setMessage(message1);
                        Chatmodel.setSenderId(senderuid);

                        mDbRef.child("chats").child(senderRoom).child("messages").push().setValue(Chatmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                mDbRef.child("chats").child(receiverRoom).child("messages").push().setValue(Chatmodel);

                            }
                        });

                        messBox.setText("");
                    }
                });
                RecyclerView recyview3=(RecyclerView) findViewById(R.id.chatrecyv);
                messageAdapter MessageAdapter=new messageAdapter(chatActivity.this,listmes);
                recyview3.setLayoutManager(new LinearLayoutManager(chatActivity.this));
                recyview3.setAdapter(MessageAdapter);
                MessageAdapter.notifyDataSetChanged();

                break;
            }



        }










    }



}