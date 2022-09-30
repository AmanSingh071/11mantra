package com.example.otpscreen.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.otpscreen.R;
import com.example.otpscreen.databinding.ActivitySignupastrologersBinding;
import com.example.otpscreen.models.signupdetailsmodelsastrologers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class signupastrologers extends AppCompatActivity {
    EditText  email,pass,userName;
    EditText lang,prof,exp,rate,chatrate,callrate,videorate,about;
    ImageView astroproimg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    signupdetailsmodelsastrologers Signupdetailsmodelsastrologers;
    FirebaseAuth auth;
    ActivitySignupastrologersBinding binding;
    static int REQUEST_CODE=1;
    Uri pickedImageUri;
    ActivityResultLauncher<String>launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupastrologersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        astroproimg=findViewById(R.id.astroproimg);
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                binding.astroproimg.setImageURI(uri);
                final String rand=UUID.randomUUID().toString();
                final StorageReference reference= storage.getReference().child("image/"+rand);
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(signupastrologers.this,"success",Toast.LENGTH_LONG).show();
                                pickedImageUri=uri;

                            }
                        });

                    }
                });

            }
        });
        astroproimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launcher.launch("image/*");
            }
        });
        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUpuser();
            }
        });

    }


    private void signUpuser() {
        email=findViewById(R.id.emailsignup);
        pass=findViewById(R.id.passwordsignup);
        userName=findViewById(R.id.usernamesignup);
        lang=binding.langsignup;
        prof=binding.professionsignup;
        exp=binding.expsignup;
        rate=binding.ratesignup;
        chatrate=binding.chatepricesignup;
        callrate=binding.callpricesignup;
        videorate=binding.videosignup;
        about=binding.aboutastrosignup;


        String userName1 = userName.getText().toString();
        String email1 = email.getText().toString();
        String pass1= pass.getText().toString();
        String lang1 = lang.getText().toString();
        String prof1 = prof.getText().toString();
        String exp1= exp.getText().toString();
        String rate1= rate.getText().toString();
        String chatrate1= chatrate.getText().toString();
        String callrate1= callrate.getText().toString();
        String videorate1= videorate.getText().toString();
        String about1= about.getText().toString();




                    storeuserdata(email1,pass1, userName1,lang1,prof1, exp1,rate1,chatrate1,callrate1,videorate1,about1);
                    Intent intent=new Intent(signupastrologers.this,homepageActivity.class);
                    startActivity(intent);
                    Toast.makeText(signupastrologers.this,"please log in again",Toast.LENGTH_LONG).show();






    }

    private void storeuserdata(String email1, String pass1, String userName1, String lang1, String prof1, String exp1, String rate1, String chatrate1, String callrate1, String videorate1, String about1) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("user").child(auth.getCurrentUser().getUid());

        Signupdetailsmodelsastrologers=new signupdetailsmodelsastrologers();
        Signupdetailsmodelsastrologers.setEmail(email1);
        Signupdetailsmodelsastrologers.setPassword(pass1);
        Signupdetailsmodelsastrologers.setUsername(userName1);
        Signupdetailsmodelsastrologers.setProImg(pickedImageUri.toString());

        Signupdetailsmodelsastrologers.setLanguages(lang1);
        Signupdetailsmodelsastrologers.setProfession(prof1);
        Signupdetailsmodelsastrologers.setExperience(exp1);
        Signupdetailsmodelsastrologers.setRate(rate1);
        Signupdetailsmodelsastrologers.setChatPrice(chatrate1);
        Signupdetailsmodelsastrologers.setCallPrice(callrate1);
        Signupdetailsmodelsastrologers.setVideoPrice(videorate1);
        Signupdetailsmodelsastrologers.setAbout(about1);
        Signupdetailsmodelsastrologers.setAuthid(FirebaseAuth.getInstance().getCurrentUser().getUid());





                databaseReference.setValue(Signupdetailsmodelsastrologers);


    }


}