package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.otpscreen.Adapters.horomainhomeadapter;
import com.example.otpscreen.R;
import com.example.otpscreen.models.horoscopemainmodels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mainhomescreen2 extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyview;
    ArrayList<horoscopemainmodels> list;
    horomainhomeadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhomescreen2);
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




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyview=findViewById(R.id.recyhoro);
        recyview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myadapter=new horomainhomeadapter(this,list);
        recyview.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }
}