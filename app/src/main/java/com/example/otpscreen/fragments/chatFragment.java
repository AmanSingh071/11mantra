package com.example.otpscreen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otpscreen.Adapters.chatAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatFragment extends Fragment {

    public chatFragment() {
        // Required empty public constructor
    }
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static ArrayList<signupdetailsmodelsastrologers> list2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_chat, container, false);

        list2 =new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        signupdetailsmodelsastrologers user= userSnapshot.getValue(signupdetailsmodelsastrologers.class);
                        list2.add(user);
                    }       RecyclerView recyview2=(RecyclerView) v.findViewById(R.id.chatrecy);
                    chatAdapter ChatAdapter=new chatAdapter(getContext(),list2);
                    recyview2.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyview2.setAdapter(ChatAdapter);
                    ChatAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}