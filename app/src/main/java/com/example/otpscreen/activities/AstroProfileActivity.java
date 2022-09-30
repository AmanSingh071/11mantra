package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.otpscreen.Adapters.FollowAdapter;
import com.example.otpscreen.databinding.ActivityAstroProfileBinding;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.followModel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AstroProfileActivity extends AppCompatActivity {
    int position=0;
    ArrayList<signupdetailsmodelsastrologers> list;
    ActivityAstroProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    public static long num=0;
    followModel Followmodel;
String astrouserid;
String currentuserid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstroProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        databaseReference= firebaseDatabase.getReference("follow");
        list=new ArrayList<signupdetailsmodelsastrologers>();
        list.addAll(mainhomescreenfrag.list2);

        initializelayout();
    }

    private void initializelayout() {
        Bundle extras=getIntent().getExtras();

        position = extras.getInt("index",0);
        switch (extras.getString("class"))
        {
            case  "uploadAdapter":
                list=new ArrayList<signupdetailsmodelsastrologers>();
                list.addAll(mainhomescreenfrag.list2);

                Followmodel=new followModel();

                astrouserid= list.get(position).getAuthid();
                currentuserid=  auth.getCurrentUser().getUid();
                Followmodel.setAstrouserid(astrouserid);
                Followmodel.setCurrentuserid(currentuserid);



                binding.followastro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        databaseReference.push().setValue(Followmodel);
                        Toast.makeText(AstroProfileActivity.this,"You are following ",Toast.LENGTH_LONG).show();


                    }
                });

                setplayerlayout();
                break;

        }


    }

    private void setplayerlayout() {
        Glide.with(this).load(list.get(position).proImg).into(binding.astroproimg);
        binding.astroprousername.setText(list.get(position).getUsername());
        binding.astroproexperience.setText(list.get(position).getExperience());
        binding.astroprolang.setText(list.get(position).getLanguages());
        binding.astroproprofes.setText(list.get(position).getProfession());
        binding.rate.setText(list.get(position).getRate());
        binding.chatrate.setText(list.get(position).getChatPrice());
        binding.callrate.setText(list.get(position).getCallPrice());
        binding.videorate.setText(list.get(position).getVideoPrice());
        binding.about.setText(list.get(position).getAbout());



    }
}