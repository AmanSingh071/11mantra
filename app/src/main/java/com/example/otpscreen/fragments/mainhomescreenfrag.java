package com.example.otpscreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.otpscreen.Adapters.AstroAdapter;
import com.example.otpscreen.Adapters.clientReviewAdapter;
import com.example.otpscreen.Adapters.homeblogadapter;
import com.example.otpscreen.Adapters.horomainhomeadapter;
import com.example.otpscreen.R;
import com.example.otpscreen.models.blogmodel;
import com.example.otpscreen.models.horoscopemainmodels;
import com.example.otpscreen.models.reviewmodel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mainhomescreenfrag extends Fragment {

    public  mainhomescreenfrag(){

    }
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<horoscopemainmodels> list;

    public static ArrayList<signupdetailsmodelsastrologers> list2;
    public static ArrayList<blogmodel> list3;
    public static ArrayList<reviewmodel> list4;
    ViewPager viewPager;
    clientReviewAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyview=(RecyclerView) v.findViewById(R.id.recyhoro);
        list =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("test");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        horoscopemainmodels user= userSnapshot.getValue(horoscopemainmodels.class);
                        list.add(user);
                    }
                    recyview.setHasFixedSize(true);
                    horomainhomeadapter horomainhomeadapter=new horomainhomeadapter(getContext(),list);
                    recyview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    recyview.setAdapter(horomainhomeadapter);
                    horomainhomeadapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        list2 =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        signupdetailsmodelsastrologers user= userSnapshot.getValue(signupdetailsmodelsastrologers.class);
                        list2.add(user);
                    }       RecyclerView recyview2=(RecyclerView) v.findViewById(R.id.recyastro);
                    AstroAdapter astroAdapter=new AstroAdapter(getContext(),list2);
                    recyview2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    recyview2.setAdapter(astroAdapter);
                    astroAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        list3 =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("blog");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        blogmodel user= userSnapshot.getValue(blogmodel.class);
                        list3.add(user);
                    }
                    RecyclerView recyview3=(RecyclerView) v.findViewById(R.id.recyblog);
                    homeblogadapter Blogadpter=new homeblogadapter(getContext(),list3);
                    recyview3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    recyview3.setAdapter(Blogadpter);
                    Blogadpter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        list4 =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("review");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        reviewmodel user= userSnapshot.getValue(reviewmodel.class);
                        list4.add(user);
                    }
                    RecyclerView recyview4=(RecyclerView) v.findViewById(R.id.recyreview);
                    clientReviewAdapter Clientreviewadapter=new clientReviewAdapter(getContext(),list4);
                    recyview4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    recyview4.setAdapter(Clientreviewadapter);
                    Clientreviewadapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ImageView chatimg=v.findViewById(R.id.imgchat);
        chatimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new chatFragment();
                FragmentManager fragmentManager=((FragmentActivity) getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frag,fragment).commit();
            }
        });










       /* viewPager=v.findViewById(R.id.recyreview);
        adapter=new clientReviewAdapter(getContext(),list4);
        viewPager.setAdapter(adapter);*/


        return v;
    }





}