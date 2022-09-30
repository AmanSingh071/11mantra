package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.Adapters.RashiHoroscopeAdapter;
import com.example.otpscreen.databinding.ActivityDailyhoroscopeBinding;
import com.example.otpscreen.fragments.dailyhoroscopefragement;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.rashiModel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dailyhoroscopeActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    int position=0;
    ActivityDailyhoroscopeBinding binding;
    DatabaseReference databaseReference;
    public static ArrayList<rashiModel> list;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDailyhoroscopeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag2,new dailyhoroscopefragement());
        fragmentTransaction.commit();


        setplayerlayout();




    }




    private void setplayerlayout() {

        list =new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("rashi");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        rashiModel user= userSnapshot.getValue(rashiModel.class);
                        list.add(user);
                    }
                    binding.carrertxt.setText(list.get(0).getCareertxt());
                    binding.lovetxt.setText(list.get(0).getLovetxt());
                    binding.moneytxt.setText(list.get(0).getMoneytxt());
                    binding.healthtxt.setText(list.get(0).getHealthtxt());
                    binding.traveltxt.setText(list.get(0).getTraveltxt());
                    binding.lucktime.setText(list.get(0).getLuckytime());
                    binding.luckynum.setText(list.get(0).getLuckyno().toString());
                    binding.lovepercent.setText(list.get(0).getLoveper());
                    binding.carrerpercent.setText(list.get(0).getCareerper());
                    binding.healthpercent.setText(list.get(0).getHealthper());
                    binding.moneypercent.setText(list.get(0).getMoneyper());
                    binding.travelpercent.setText(list.get(0).getTravelper());
                    Glide.with(dailyhoroscopeActivity.this).load(list.get(0).logo).into(binding.logoimg);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}