package com.example.otpscreen.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.otpscreen.databinding.ActivitySendReviewTestBinding;
import com.example.otpscreen.models.reviewmodel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class sendReviewTestActivity extends AppCompatActivity {
    EditText reviewpername,reviewperAdd,reviewtxt;
   reviewmodel Reviewmodel;
    ImageView revperimg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    Uri pickedImageUri;
    ActivityResultLauncher<String> launcher;
    ActivitySendReviewTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendReviewTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                binding.reviewperimg.setImageURI(uri);
                final String rand= UUID.randomUUID().toString();
                final StorageReference reference= storage.getReference().child("revperimage/"+rand);
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(sendReviewTestActivity.this,"success",Toast.LENGTH_LONG).show();
                                pickedImageUri=uri;

                            }
                        });

                    }
                });

            }
        });
        binding.reviewperimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launcher.launch("image/*");
            }
        });
        binding.btnblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendreviewdata();
            }
        });
    }

    private void sendreviewdata() {
        reviewpername=binding.reviewpersonname;
        reviewperAdd=binding.reviewpersonadd;
        reviewtxt=binding.reviewtxt;


        String reviewpername1 = reviewpername.getText().toString();
        String reviewperAdd1 = reviewperAdd.getText().toString();
        String reviewtxt1 = reviewtxt.getText().toString();


        storeuserdata(reviewpername1,reviewperAdd1, reviewtxt1);
    }

    private void storeuserdata(String reviewpername1, String reviewperAdd1, String reviewtxt1) {
        firebaseDatabase= FirebaseDatabase.getInstance();
        final String rand=UUID.randomUUID().toString();
        databaseReference= firebaseDatabase.getReference("review").child(rand);;
        Reviewmodel=new reviewmodel();

        Reviewmodel.setName(reviewpername1);
        Reviewmodel.setAdd(reviewperAdd1);
        Reviewmodel.setRevtxt(reviewtxt1);
        Reviewmodel.setPerimg(pickedImageUri.toString());





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(Reviewmodel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}