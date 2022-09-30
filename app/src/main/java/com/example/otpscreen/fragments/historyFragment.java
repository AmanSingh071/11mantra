package com.example.otpscreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otpscreen.Adapters.HistoryAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.models.horoscopemainmodels;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class historyFragment extends Fragment {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
com.example.otpscreen.models.horoscopemainmodels horoscopemainmodels;
TabLayout tabLayout;
ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_history, container, false);
        tabLayout=v.findViewById(R.id.tablayouthis);
        viewPager=v.findViewById(R.id.viewpagerhis);
        tabLayout.setupWithViewPager(viewPager);
        HistoryAdapter historyAdapter=new HistoryAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        historyAdapter.addFragment(new WalletFragment(),"Wallet");
        historyAdapter.addFragment(new callHistoryFragment(),"Call");
        historyAdapter.addFragment(new chatHistoryFragment(),"Chat");
        historyAdapter.addFragment(new AstromailFragment(),"Astromail");
        historyAdapter.addFragment(new ReportFragment(),"Report");

        viewPager.setAdapter(historyAdapter);

        return v;
    }
}