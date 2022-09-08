package com.example.otpscreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.activities.dailyhoroscopeActivity;
import com.example.otpscreen.fragments.chatFragment;
import com.example.otpscreen.models.horoscopemainmodels;

import java.util.ArrayList;

public class horomainhomeadapter extends RecyclerView.Adapter<horomainhomeadapter.MyViewHolder> {


    Context context;
    ArrayList<horoscopemainmodels>list;

    public horomainhomeadapter(Context context, ArrayList<horoscopemainmodels> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.homehorolayout,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        horoscopemainmodels horoscopemainmodels = list.get(position);

        holder.name.setText(horoscopemainmodels.getNametype());
        String pos=String.valueOf(position);
        int po=position;
        Glide.with(context).load(horoscopemainmodels.image).into(holder.imagev);


            holder.imagev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (po==0){
                        Intent intent = new Intent(context, dailyhoroscopeActivity.class);
                        context.startActivity(intent);
                    }
                    else if (po==1){
                        Fragment fragment = new chatFragment();
                        FragmentManager fragmentManager=((FragmentActivity) context).getSupportFragmentManager();
                       fragmentManager.beginTransaction().replace(R.id.frag,fragment).commit();


                    }
                }
            });





    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
  TextView name;
  ImageView imagev;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameame);
            imagev=itemView.findViewById(R.id.Imagehoro);
        }
    }
}
