package com.example.otpscreen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivityHomepageBinding;
import com.example.otpscreen.fragments.callFragment;
import com.example.otpscreen.fragments.chatFragment;
import com.example.otpscreen.fragments.historyFragment;
import com.example.otpscreen.fragments.liveFragment;
import com.example.otpscreen.fragments.mainhomescreenfrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homepageActivity extends AppCompatActivity {
   Fragment fragment=new mainhomescreenfrag();
   ActionBarDrawerToggle toogle;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    com.example.otpscreen.models.horoscopemainmodels horoscopemainmodels;
    ActivityHomepageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
           /* binding.navbar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
                @Override
                public void onItemSelected(int i) {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    switch(i){
                        case 0:
                            fragmentTransaction.replace(R.id.frag,new mainhomescreenfrag());
                            Toast.makeText(homepageActivity.this,"home",Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            fragmentTransaction.replace(R.id.frag,new chatFragment());
                            Toast.makeText(homepageActivity.this,"chat",Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            fragmentTransaction.replace(R.id.frag,new liveFragment());
                            Toast.makeText(homepageActivity.this,"live",Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            fragmentTransaction.replace(R.id.frag,new callFragment());
                            Toast.makeText(homepageActivity.this,"call",Toast.LENGTH_LONG).show();
                            break;
                        case 4:
                            fragmentTransaction.replace(R.id.frag,new historyFragment());
                            Toast.makeText(homepageActivity.this,"history",Toast.LENGTH_LONG).show();
                            break;
                    }
                    fragmentTransaction.commit();

                }
            });*/
            }



        binding .navbar21.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home3:
                    replacefrag(new mainhomescreenfrag());
                    break;
                case R.id.signupastro:
                    Intent intent = new Intent(homepageActivity.this,signupastrologers.class);
                    startActivity(intent);
                    break;
                case R.id.sendblog:
                    Intent intent2 = new Intent(homepageActivity.this,sendblogTestActivity.class);
                    startActivity(intent2);

                case R.id.sendreview:
                    Intent intent3 = new Intent(homepageActivity.this,sendReviewTestActivity.class);
                    startActivity(intent3);

            }
            return true;
        });


    }

    public void replacefrag(Fragment fragment) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag,fragment);
                fragmentTransaction.commit();

        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
