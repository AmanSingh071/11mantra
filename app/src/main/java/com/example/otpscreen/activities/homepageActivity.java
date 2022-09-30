package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otpscreen.Adapters.clientReviewAdapter;
import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivityHomepageBinding;
import com.example.otpscreen.fragments.ReportFragment;
import com.example.otpscreen.fragments.WalletFragment;
import com.example.otpscreen.fragments.callFragment;
import com.example.otpscreen.fragments.chatFragment;
import com.example.otpscreen.fragments.chatHistoryFragment;
import com.example.otpscreen.fragments.historyFragment;
import com.example.otpscreen.fragments.liveFragment;
import com.example.otpscreen.fragments.mainhomescreenfrag;

import com.example.otpscreen.models.reviewmodel;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class homepageActivity extends AppCompatActivity  {
   Fragment fragment=new mainhomescreenfrag();
   ActionBarDrawerToggle toogle;
    signupdetailsmodelsastrologers Signupdetailsmodelsastrologers;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static homepageActivity homepageActivity;
    FirebaseAuth fauth;
    com.example.otpscreen.models.horoscopemainmodels horoscopemainmodels;
    ActivityHomepageBinding binding;
    public static ArrayList<signupdetailsmodelsastrologers> listastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fauth=FirebaseAuth.getInstance();


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("user");
        senddcmtoken();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists() ) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        signupdetailsmodelsastrologers user= userSnapshot.getValue(signupdetailsmodelsastrologers.class);

                        if ( user.getAuthid()==fauth.getCurrentUser().getUid()) {


                        }

                    }


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag,new mainhomescreenfrag());
        fragmentTransaction.commit();
    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }*/

        ActionBar var8 = this.getSupportActionBar();
        if (var8 != null) {
            var8.setDisplayHomeAsUpEnabled(true);
        }
        toogle= new ActionBarDrawerToggle(this,binding.getRoot(),R.string.open,R.string.close);
        binding.getRoot().addDrawerListener(toogle);
        toogle.syncState();
        firebaseDatabase = FirebaseDatabase.getInstance();



        if (savedInstanceState==null){
           binding.navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   switch (item.getItemId()){
                       case R.id.home:
                           replacefrag(new mainhomescreenfrag());
                           break;

                       case R.id.chat:
                           replacefrag(new chatFragment());
                           break;
                       case R.id.live:
                           replacefrag(new liveFragment());
                          break;
                       case R.id.call:
                           replacefrag(new callFragment());
                           break;
                       case R.id.history:
                           replacefrag(new historyFragment());
                           break;


                   }
                   return false;
               }
           }  );

            }

/*
        app:headerLayout="@layout/activity_nav_header"
*/

        binding .navbar21.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home3:
                    replacefrag(new mainhomescreenfrag());
                    break;
                case R.id.signupastro:
                    Intent intent = new Intent(homepageActivity.this,astroNumberSignUp.class);
                    startActivity(intent);
                    break;
                case R.id.sendblog:
                    Intent intent2 = new Intent(homepageActivity.this,sendblogTestActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.sendreview:
                    Intent intent3 = new Intent(homepageActivity.this,sendReviewTestActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.signout:
                    signout(FirebaseAuth.getInstance().getCurrentUser().getUid());
                case R.id.walletTrans:
                    Fragment fragment = new WalletFragment();
                    FragmentManager fragmentManager=((FragmentActivity) this).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frag,fragment).commit();
                    break;
                case R.id.orderHis:
                    Fragment orderfragment = new chatHistoryFragment();
                    FragmentManager fragmentManagerorder=((FragmentActivity) this).getSupportFragmentManager();
                    fragmentManagerorder.beginTransaction().replace(R.id.frag,orderfragment).commit();
                    break;
                case R.id.astromall:
                    Intent intentastro = new Intent(homepageActivity.this,astromallActivity.class);
                    startActivity(intentastro);
                    finish();
                    break;
                case R.id.getreport:
                    Fragment fragmentreport = new ReportFragment();
                    FragmentManager fragmentManagerReport=((FragmentActivity) this).getSupportFragmentManager();
                    fragmentManagerReport.beginTransaction().replace(R.id.frag,fragmentreport).commit();
                    break;
                case R.id.chatAstro:
                    Fragment fragmentchat = new chatFragment();
                    FragmentManager fragmentManagerchat=((FragmentActivity) this).getSupportFragmentManager();
                    fragmentManagerchat.beginTransaction().replace(R.id.frag,fragmentchat).commit();
                    break;
                case R.id.chatCouns:
                    Fragment fragmentcouns = new chatFragment();
                    FragmentManager fragmentManagercouns=((FragmentActivity) this).getSupportFragmentManager();
                    fragmentManagercouns.beginTransaction().replace(R.id.frag,fragmentcouns).commit();
                    break;
                case R.id.myfollow:
                    Intent intentfollow = new Intent(homepageActivity.this,followingActivity.class);
                    startActivity(intentfollow);
                    break;
                case R.id.settings:
                    Intent intentsetting = new Intent(homepageActivity.this,settingActivity.class);
                    startActivity(intentsetting);
                    break;





            }
            return false;
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.navbar21);
        View headerview = navigationView.getHeaderView(0);

        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsetting = new Intent(homepageActivity.this,ProfileActivity.class);
                startActivity(intentsetting);
            }
        });

    }

    private void signout(String userid) {
        FirebaseAuth.getInstance().signOut();
        Intent intent3 = new Intent(homepageActivity.this,MainActivity2_number.class);
        finish();
      FirebaseDatabase.getInstance().getReference("token").child(userid).removeValue();
    }
    public void replacefrag(Fragment fragment) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();

        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void senddcmtoken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()){
                    String token=task.getResult();
                    String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
/*
                    Signupdetailsmodelsastrologers=new signupdetailsmodelsastrologers();
                    Signupdetailsmodelsastrologers.setFcm(token);*/
                    FirebaseDatabase.getInstance().getReference("token").child(userid).setValue(token);
                }
            }
        });


    }






}
