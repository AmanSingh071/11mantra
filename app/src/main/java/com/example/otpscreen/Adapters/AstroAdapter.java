package com.example.otpscreen.Adapters;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.otpscreen.activities.AstroProfileActivity;
import com.example.otpscreen.R;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;

import java.util.ArrayList;

public class AstroAdapter extends RecyclerView.Adapter<AstroAdapter.MyViewHolder> {


    Context context;
    ArrayList<signupdetailsmodelsastrologers>list;


    public AstroAdapter(Context context, ArrayList<signupdetailsmodelsastrologers> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.homeastrolayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        signupdetailsmodelsastrologers Signupdetailsmodelsastrologers = list.get(position);
        int pos=position;

        holder.name.setText(Signupdetailsmodelsastrologers.getUsername());
        holder.rate.setText(Signupdetailsmodelsastrologers.getRate());
        Glide.with(context).load(Signupdetailsmodelsastrologers.proImg).into(holder.imagev);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setintetnonclickitem("uploadAdapter",  pos);
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
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rate;
        CardView card;
        ImageView imagev;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameame);
            imagev=itemView.findViewById(R.id.Imagehoro);
            rate=itemView.findViewById(R.id.astrorate);
            card=itemView.findViewById(R.id.cardv);
        }
    }
}

