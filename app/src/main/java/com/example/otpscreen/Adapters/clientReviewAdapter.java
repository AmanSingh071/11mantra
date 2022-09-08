package com.example.otpscreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import androidx.recyclerview.widget.RecyclerView;

import com.example.otpscreen.R;
import com.example.otpscreen.models.reviewmodel;

public class clientReviewAdapter extends RecyclerView.Adapter<clientReviewAdapter.MyViewHolder> {


    Context context;
    ArrayList<reviewmodel>list;

    public clientReviewAdapter(Context context, ArrayList<reviewmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.clietnreviewlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        reviewmodel Reviewmodel = list.get(position);

        holder.name.setText(Reviewmodel.getName());
        holder.country.setText(Reviewmodel.getAdd());
        holder.reviewetxt.setText(Reviewmodel.getRevtxt());
        Glide.with(context).load(Reviewmodel.perimg).into(holder.imagev);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,country,reviewetxt;
        ImageView imagev;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.revperName);
            country=itemView.findViewById(R.id.revperAdd);
            reviewetxt=itemView.findViewById(R.id.reviewTxt);
            imagev=itemView.findViewById(R.id.revperImg);
        }
    }
}

/*
public class clientReviewAdapter extends PagerAdapter{
Context context;
    ArrayList<reviewmodel> list;

    public clientReviewAdapter(Context context, ArrayList<reviewmodel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.clietnreviewlayout,null);

        TextView name,country,reviewetxt;
        ImageView imagev;
        name=view.findViewById(R.id.revperName);
        country=view.findViewById(R.id.revperAdd);
        reviewetxt=view.findViewById(R.id.reviewTxt);
        imagev=view.findViewById(R.id.revperImg);

        reviewmodel Reviewmodel = list.get(position);

       name.setText(Reviewmodel.getName());
       country.setText(Reviewmodel.getAdd());
        reviewetxt.setText(Reviewmodel.getRevtxt());
        Glide.with(context).load(Reviewmodel.perimg).into(imagev);

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

}
*/
