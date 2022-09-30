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
import com.example.otpscreen.models.chatModel;
import com.example.otpscreen.models.rashiModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class messageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<chatModel>list;

    public messageAdapter(Context context, ArrayList<chatModel> list) {
        this.context = context;
        this.list = list;
    }
    Integer ITEM_SENT=1;
    Integer ITEM_RECEIVE=2;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            View v= LayoutInflater.from(context).inflate(R.layout.sentmessagelayout,parent,false);
            return new sentviewHolder(v);
        }
        else {

        }
        View v= LayoutInflater.from(context).inflate(R.layout.receivemessagelayout,parent,false);
        return new receiveviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chatModel ChatModel = list.get(position);
        int pos=position;
     if (holder.getClass()==sentviewHolder.class) {

         ((sentviewHolder) holder).sentMessage.setText(ChatModel.getMessage());

     }
     else {
         ((receiveviewHolder) holder).receiveMessage.setText(ChatModel.getMessage());
     }








    }

    @Override
    public int getItemViewType(int position) {
        chatModel ChatModel = list.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(ChatModel.getSenderId())){
            return ITEM_SENT;
        }
        else {
            return ITEM_RECEIVE;
        }


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


    public static  class sentviewHolder extends RecyclerView.ViewHolder{
        TextView sentMessage,receiveMessage;

        public sentviewHolder(@NonNull View itemView) {
            super(itemView);
            sentMessage=itemView.findViewById(R.id.sentmess);

        }


    }
    public static  class receiveviewHolder extends RecyclerView.ViewHolder{
        TextView sentMessage,receiveMessage;

        public receiveviewHolder(@NonNull View itemView) {
            super(itemView);
            receiveMessage=itemView.findViewById(R.id.recMess);

        }


    }

}
