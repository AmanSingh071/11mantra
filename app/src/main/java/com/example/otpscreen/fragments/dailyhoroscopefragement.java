package com.example.otpscreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otpscreen.Adapters.RashiHoroscopeAdapter;
import com.example.otpscreen.Adapters.horomainhomeadapter;
import com.example.otpscreen.R;
import com.example.otpscreen.models.horoscopemainmodels;
import com.example.otpscreen.models.rashiModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dailyhoroscopefragement extends Fragment {

    public dailyhoroscopefragement() {
        // Required empty public constructor
    }
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static ArrayList<rashiModel> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dailyhoroscopefragement, container, false);
        RecyclerView recyview=(RecyclerView) v.findViewById(R.id.recyrashi2);
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
                    recyview.setHasFixedSize(true);
                    RashiHoroscopeAdapter rashiHoroscopeAdapter=new RashiHoroscopeAdapter(getContext(),list);
                    recyview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    recyview.setAdapter(rashiHoroscopeAdapter);
                    rashiHoroscopeAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}