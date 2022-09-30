package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivityTodayactivityBinding;
import com.example.otpscreen.fragments.dailyhoroscopefragement;
import com.example.otpscreen.models.rashiModel;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class todayactivity extends AppCompatActivity {
    int position=0;
    ActivityTodayactivityBinding binding;
    public static ArrayList<rashiModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodayactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag3,new dailyhoroscopefragement());
        fragmentTransaction.commit();

        initializelayout();
    }
    private void initializelayout() {

        Bundle extras=getIntent().getExtras();

        position = extras.getInt("index",0);
        switch (extras.getString("class"))
        {
            case  "uploadAdapter":
                list=new ArrayList<rashiModel>();
                list.addAll(dailyhoroscopefragement.list);

                setplayerlayout();
                break;

        }


    }

    private void setplayerlayout() {
        binding.carrertxt.setText(list.get(position).getCareertxt());
        binding.lovetxt.setText(list.get(position).getLovetxt());
        binding.moneytxt.setText(list.get(position).getMoneytxt());
        binding.healthtxt.setText(list.get(position).getHealthtxt());
        binding.traveltxt.setText(list.get(position).getTraveltxt());
        binding.lucktime.setText(list.get(position).getLuckytime());
        binding.luckynum.setText(list.get(position).getLuckyno().toString());
        binding.lovepercent.setText(list.get(position).getLoveper());
        binding.carrerpercent.setText(list.get(position).getCareerper());
        binding.healthpercent.setText(list.get(position).getHealthper());
        binding.moneypercent.setText(list.get(position).getMoneyper());
        binding.travelpercent.setText(list.get(position).getTravelper());
        Glide.with(todayactivity.this).load(list.get(position).logo).into(binding.logoimg);






    }
}