package com.example.otpscreen.Adapters;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.activities.outgoingcall;

import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class callAdapter extends RecyclerView.Adapter<callAdapter.MyViewHolder> {


    Context context;
    ArrayList<signupdetailsmodelsastrologers>list;



    public callAdapter(Context context, ArrayList<signupdetailsmodelsastrologers> list) {
        this.context = context;
        this.list = list;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.calllayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        signupdetailsmodelsastrologers Signupdetailsmodelsastrologers = list.get(position);
        int pos=position;

        holder.name.setText(Signupdetailsmodelsastrologers.getUsername());
        holder.rate.setText(Signupdetailsmodelsastrologers.getRate());
        holder.profession.setText(Signupdetailsmodelsastrologers.getProfession());
        holder.lang.setText(Signupdetailsmodelsastrologers.getLanguages());
        holder.exp.setText(Signupdetailsmodelsastrologers.getExperience());

        Glide.with(context).load(Signupdetailsmodelsastrologers.proImg).into(holder.imagev);
        holder.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference;
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference= firebaseDatabase.getReference("token");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists() ) {


                            if (snapshot.hasChild(Signupdetailsmodelsastrologers.getAuthid()))
                            {
                                snapshot.child(Signupdetailsmodelsastrologers.getAuthid()).getValue();
                                Toast.makeText(view.getContext(), "user online Connecting...",Toast.LENGTH_LONG).show();
                                Log.e("y", "yes");
                                String userid=Signupdetailsmodelsastrologers.getAuthid();
                                String token= snapshot.child(Signupdetailsmodelsastrologers.getAuthid()).getValue().toString();
                                Toast.makeText(view.getContext(),      snapshot.child(Signupdetailsmodelsastrologers.getAuthid()).getValue().toString(),Toast.LENGTH_LONG).show();
                                setintetnonclickitem("uploadAdapter", pos,token ,userid);

                            }
                            else
                            {
                                Toast.makeText(view.getContext(), "user offline",Toast.LENGTH_LONG).show();
                            }











                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
    private void setintetnonclickitem(String ref, int pos,String id,String userid) {

        Intent intent=new Intent(context, outgoingcall.class);

        intent.putExtra("index",pos);
        intent.putExtra("class",ref);
        intent.putExtra("ID",id);
        intent.putExtra("USERID",userid);
        ContextCompat.startActivity(context,intent,null);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rate,profession,lang,exp,callbtn;
        CardView card;
        ImageView imagev;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.astroprousernamecall);
            imagev=itemView.findViewById(R.id.astroproimgcall);
            rate=itemView.findViewById(R.id.ratecall);
            profession=itemView.findViewById(R.id.astroproprofescall);
            lang=itemView.findViewById(R.id.astroprolangcall);
            exp=itemView.findViewById(R.id.astroproexperiencecall);
            callbtn=itemView.findViewById(R.id.callidbtn);




        }
    }
}

