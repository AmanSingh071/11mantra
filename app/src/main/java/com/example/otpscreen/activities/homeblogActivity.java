package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.otpscreen.databinding.ActivityHomeblogBinding;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.blogmodel;

import java.util.ArrayList;

public class homeblogActivity extends AppCompatActivity {
    int position=0;
    ArrayList<blogmodel> list;
    ActivityHomeblogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeblogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializelayout();
    }

    private void initializelayout() {
        Bundle extras=getIntent().getExtras();

        position = extras.getInt("index",0);
        switch (extras.getString("class"))
        {
            case  "uploadAdapter":
                list=new ArrayList<blogmodel>();
                list.addAll(mainhomescreenfrag.list3);

                setplayerlayout();
                break;

        }
    }

    private void setplayerlayout() {
        Glide.with(this).load(list.get(position).blogimg).into(binding.blogImg);
        binding.blogTitle.setText(list.get(position).getBlogTitle());
        binding.blogTxt.setText(list.get(position).getBlogTxt());
        binding.blogDate.setText("Posted On-"+list.get(position).getBlogDate());
       binding.blogAuthor2.setText("Posted By-"+list.get(position).getBlogAuthor());
    }
}