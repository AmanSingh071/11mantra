package com.example.otpscreen.activities;

import static com.example.otpscreen.fragments.mainhomescreenfrag.list2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.otpscreen.Adapters.AstroAdapter;
import com.example.otpscreen.Adapters.FollowAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.example.otpscreen.models.blogmodel;
import com.example.otpscreen.models.followModel;
import com.example.otpscreen.models.horoscopemainmodels;
import com.example.otpscreen.models.rashiModel;
import com.example.otpscreen.models.responsemodel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class followingActivity extends AppCompatActivity {
    public   ArrayList<followModel> list2;
    public   ArrayList<signupdetailsmodelsastrologers> list;

    FirebaseDatabase firebaseDatabase;

    FirebaseDatabase firebaseDatabase2;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        list2 =new ArrayList<>();
        list =new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("follow");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                       for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                           followModel user= userSnapshot.getValue(followModel.class);
                           list2.add(user);



                               list = new ArrayList<signupdetailsmodelsastrologers>();
                               list =new ArrayList<>();
                               firebaseDatabase2=FirebaseDatabase.getInstance();
                               databaseReference2= firebaseDatabase2.getReference("user").child(user.getAstrouserid());
                               databaseReference2.addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot2) {

                                       if (snapshot2.exists()) {

                                                   signupdetailsmodelsastrologers user= snapshot2.getValue(signupdetailsmodelsastrologers.class);
                                                   list.add(user);

                                               RecyclerView recyview2 = (RecyclerView) findViewById(R.id.followrecy);
                                               FollowAdapter followAdapter = new FollowAdapter(followingActivity.this, list);
                                               recyview2.setLayoutManager(new LinearLayoutManager(followingActivity.this));
                                               recyview2.setAdapter(followAdapter);
                                               followAdapter.notifyDataSetChanged();

                                       }
                                   }
                                   @Override
                                   public void onCancelled(@NonNull DatabaseError error) {

                                   }
                               });




                       }



                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}