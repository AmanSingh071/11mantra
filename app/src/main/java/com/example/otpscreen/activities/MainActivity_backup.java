package com.example.otpscreen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otpscreen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity_backup extends AppCompatActivity implements PaymentResultListener {
    TextView textView,textView1;
    String name;
    int coins=100;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser currentuser;
    LinearLayout layout;
    Button button,button2;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_backup);
       button=findViewById(R.id.button6);
        button2=findViewById(R.id.button8);
        textView1=findViewById(R.id.textView7);
        textView=findViewById(R.id.textView18);
        phone=getIntent().getStringExtra("phonenumber");
/*       currentuser=auth.getCurrentUser();*/
        layout=findViewById(R.id.userCoinsLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(MainActivity_backup.this,MainActivity7.class);
               // startActivity(intent);
                PaymentNow("10000000");
            }
        });
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

         /* database.getReference().child("profile").child(currentuser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        users user=snapshot.getValue(users.class);
                          currentuser=auth.getCurrentUser();
                       // coins= user.getCoins();
                        name=user.getName();
                        textView1.setText("Hi,Welcome"+name);
                       // textView.setText("You have:"+coins);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(coins>25)
            {
                Toast.makeText(MainActivity_backup.this, "connecting......", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity_backup.this,MainActivity5_videocall.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity_backup.this, "Insufficient Coins", Toast.LENGTH_SHORT).show();
            }
            }
        });


    }
    private void PaymentNow(String amount)
    {
        Checkout checkout = new Checkout();



        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("description", "Payment for Anything");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", false);

            //You can omit the image option to fetch the image from dashboard
            options.put("currency", "INR");
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "");
            preFill.put("contact", phone);

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();

    }
}

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success" +s, Toast.LENGTH_SHORT).show();
        textView.setText("You have:100");
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Error"+s, Toast.LENGTH_SHORT).show();
    }
}