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
import com.example.otpscreen.activities.AstroProfileActivity;
import com.example.otpscreen.R;
import com.example.otpscreen.activities.chatActivity;
import com.example.otpscreen.activities.chatcallOutGoing;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.MyViewHolder> {


    Context context;
    ArrayList<signupdetailsmodelsastrologers>list;


    public chatAdapter(Context context, ArrayList<signupdetailsmodelsastrologers> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.chatlayout,parent,false);
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
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setintetnonclickitem("uploadAdapter",  pos);
            }
        });


        holder.chatbtn.setOnClickListener(new View.OnClickListener() {
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

                                String userid=Signupdetailsmodelsastrologers.getAuthid();
                                String token= snapshot.child(Signupdetailsmodelsastrologers.getAuthid()).getValue().toString();
                                 setintetnonclickitem2("chat", pos,token ,userid);

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
    private void setintetnonclickitem(String ref, int pos) {

        Intent intent=new Intent(context, AstroProfileActivity.class);

        intent.putExtra("index",pos);
        intent.putExtra("class",ref);
        ContextCompat.startActivity(context,intent,null);
    }
    private void setintetnonclickitem2(String ref, int pos,String token ,String userid) {

        Intent intent=new Intent(context, chatcallOutGoing.class);

        intent.putExtra("index",pos);
        intent.putExtra("class",ref);
        intent.putExtra("ID",token);
        intent.putExtra("USERID",userid);
        ContextCompat.startActivity(context,intent,null);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rate,profession,lang,exp,chatbtn;
        CardView card;
        ImageView imagev;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.astroprousernamechat);
            imagev=itemView.findViewById(R.id.astroproimgchat);
            rate=itemView.findViewById(R.id.ratechat);
            profession=itemView.findViewById(R.id.astroproprofeschat);
            lang=itemView.findViewById(R.id.astroprolangchat);
            exp=itemView.findViewById(R.id.astroproexperiencechat);
            card=itemView.findViewById(R.id.cardchat);
            chatbtn=itemView.findViewById(R.id.chatbtn);




        }
    }
}

