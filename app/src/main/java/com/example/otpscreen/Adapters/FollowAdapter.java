package com.example.otpscreen.Adapters;



import android.content.Context;
import android.content.Intent;
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
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.example.otpscreen.models.followModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.MyViewHolder> {


    Context context;
    ArrayList<signupdetailsmodelsastrologers>list;
    public  static Integer no;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public FollowAdapter(Context context, ArrayList<signupdetailsmodelsastrologers> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.followlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        signupdetailsmodelsastrologers Signupdetailsmodelsastrologers = list.get(position);

        int pos=position;
        firebaseDatabase=FirebaseDatabase.getInstance();


        holder.name.setText(Signupdetailsmodelsastrologers.getUsername());
        holder.rate.setText(Signupdetailsmodelsastrologers.getRate());
        holder.profession.setText(Signupdetailsmodelsastrologers.getProfession());
        holder.lang.setText(Signupdetailsmodelsastrologers.getLanguages());
        holder.exp.setText(Signupdetailsmodelsastrologers.getExperience());
        Glide.with(context).load(Signupdetailsmodelsastrologers.proImg).into(holder.imagev);
        holder.unfollowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         FirebaseDatabase.getInstance().getReference().child("follow").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       Toast.makeText(context,snapshot.toString(),Toast.LENGTH_LONG).show();

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

    @Override
    public int getItemCount() {
        no=list.size();
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rate,profession,lang,exp,unfollowbtn;

        ImageView imagev;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.astroprousernamefollow);
            imagev=itemView.findViewById(R.id.astroproimgfollow);
            rate=itemView.findViewById(R.id.ratefollow);
            profession=itemView.findViewById(R.id.astroproprofesfollow);
            lang=itemView.findViewById(R.id.astroprolangfollow);
            exp=itemView.findViewById(R.id.astroproexperiencefollowchat);
            card=itemView.findViewById(R.id.cardfollow);

            unfollowbtn=itemView.findViewById(R.id.unfollowbtn);
        }
    }
}

