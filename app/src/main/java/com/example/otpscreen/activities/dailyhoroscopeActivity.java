package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.otpscreen.Adapters.rashiTodayTomYesAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.fragments.RashiHoroscopeAdapter;
import com.example.otpscreen.fragments.todayRashiFragment;
import com.example.otpscreen.fragments.tommorowRashiFragment;
import com.example.otpscreen.fragments.yesterdayRashiFragment;
import com.example.otpscreen.models.rashiModel;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dailyhoroscopeActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<rashiModel> list;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyhoroscope);

        list =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("rashi");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        rashiModel user= userSnapshot.getValue(rashiModel.class);
                        list.add(user);
                    }
                    RecyclerView recyview4=(RecyclerView) findViewById(R.id.recyrashi);
                    RashiHoroscopeAdapter rashiHoroscopeAdapter=new RashiHoroscopeAdapter(dailyhoroscopeActivity.this,list);
                    recyview4.setLayoutManager(new LinearLayoutManager(dailyhoroscopeActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    recyview4.setAdapter(rashiHoroscopeAdapter);
                    rashiHoroscopeAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        tabLayout=findViewById(R.id.tablayoutrashi);
        viewPager=findViewById(R.id.viewpagerrashi);
        tabLayout.setupWithViewPager(viewPager);
        rashiTodayTomYesAdapter RashiTodayTomYesAdapter=new rashiTodayTomYesAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        RashiTodayTomYesAdapter.addFragment(new yesterdayRashiFragment(),"yesterday");
        RashiTodayTomYesAdapter.addFragment(new todayRashiFragment(),"today");
        RashiTodayTomYesAdapter.addFragment(new tommorowRashiFragment(),"tommorow");
        viewPager.setAdapter(RashiTodayTomYesAdapter);




    }
}