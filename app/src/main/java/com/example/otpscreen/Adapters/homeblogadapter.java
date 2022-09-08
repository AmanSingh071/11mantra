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
import com.example.otpscreen.R;
import com.example.otpscreen.activities.homeblogActivity;
import com.example.otpscreen.models.blogmodel;

import java.util.ArrayList;

public class homeblogadapter extends RecyclerView.Adapter<homeblogadapter.MyViewHolder> {


    Context context;
    ArrayList<blogmodel>list;

    public homeblogadapter(Context context, ArrayList<blogmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bloglayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        blogmodel Blogmodel = list.get(position);
        int pos=position;
        holder.blogtitle.setText(Blogmodel.getBlogTitle());
        holder.blogauthor.setText(Blogmodel.getBlogAuthor());

        holder.blogdate.setText(Blogmodel.getBlogDate());
        Glide.with(context).load(Blogmodel.blogimg).into(holder.blogimg);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setintetnonclickitem("uploadAdapter",  pos);
            }
        });


    }

    private void setintetnonclickitem(String ref, int pos) {

        Intent intent=new Intent(context, homeblogActivity.class);

        intent.putExtra("index",pos);
        intent.putExtra("class",ref);
        ContextCompat.startActivity(context,intent,null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView blogtitle,blogauthor,blogtxt,blogdate;
        CardView card;
        ImageView blogimg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            blogtitle=itemView.findViewById(R.id.blogtitle);
            blogauthor=itemView.findViewById(R.id.blogauthor);
            blogtxt=itemView.findViewById(R.id.blogtxt);
            blogdate=itemView.findViewById(R.id.blogdate);
            blogimg=itemView.findViewById(R.id.blogimg);
            card=itemView.findViewById(R.id.cardblog);
        }
    }
}
