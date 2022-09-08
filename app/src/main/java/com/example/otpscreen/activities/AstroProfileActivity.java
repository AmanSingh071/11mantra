package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.otpscreen.databinding.ActivityAstroProfileBinding;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;

import java.util.ArrayList;

public class AstroProfileActivity extends AppCompatActivity {
    int position=0;
    ArrayList<signupdetailsmodelsastrologers> list;
    ActivityAstroProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstroProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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