package com.example.otpscreen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.otpscreen.R;
import com.example.otpscreen.activities.dailyhoroscopeActivity;
import com.example.otpscreen.activities.homeblogActivity;
import com.example.otpscreen.activities.todayactivity;
import com.example.otpscreen.models.rashiModel;

import java.util.ArrayList;

public class RashiHoroscopeAdapter extends RecyclerView.Adapter<RashiHoroscopeAdapter.MyViewHolder> {


    Context context;
    ArrayList<rashiModel>list;

    public RashiHoroscopeAdapter(Context context, ArrayList<rashiModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.rashihoroscopelayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        rashiModel RashiModel = list.get(position);
        int pos=position;
        holder.name.setText(RashiModel.getRashiname());
        Glide.with(context).load(RashiModel.rashiimage).into(holder.imagev);
        holder.imagev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setintetnonclickitem("uploadAdapter",  pos);
            }
        });







    }

    private void setintetnonclickitem(String ref, int pos) {

        Intent intent=new Intent(context, todayactivity.class);

        intent.putExtra("index",pos);
        intent.putExtra("class",ref);
        ContextCompat.startActivity(context,intent,null);
        ((Activity)context).finish();
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
            name=itemView.findViewById(R.id.rashiname);
            imagev=itemView.findViewById(R.id.rashiimage);
        }
    }
}
