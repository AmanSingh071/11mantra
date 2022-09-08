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

import com.example.otpscreen.databinding.ActivitySendblogTestBinding;
import com.example.otpscreen.models.blogmodel;
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

public class sendblogTestActivity extends AppCompatActivity {
    EditText blogauthor,blogtitle,blogtxt,blogdate;
    blogmodel Blogmodel;
    ImageView blogimg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    Uri pickedImageUri;
    ActivityResultLauncher<String> launcher;
    ActivitySendblogTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendblogTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        blogimg=binding.Blogimg;
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                binding.Blogimg.setImageURI(uri);
                final String rand= UUID.randomUUID().toString();
                final StorageReference reference= storage.getReference().child("blogimage/"+rand);
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(sendblogTestActivity.this,"success",Toast.LENGTH_LONG).show();
                                pickedImageUri=uri;

                            }
                        });

                    }
                });

            }
        });
        blogimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launcher.launch("image/*");
            }
        });
        binding.btnblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendblogdata();
            }
        });
    }

    private void sendblogdata() {
        blogdate=binding.blogdate;
        blogauthor=binding.blogauthor;
        blogtitle=binding.blogtitle;
        blogtxt=binding.blogtxt;

        String blogdate1 = blogdate.getText().toString();
        String blogauthor1 = blogauthor.getText().toString();
        String blogtitle1 = blogtitle.getText().toString();
        String blogtxt1 = blogtxt.getText().toString();

        storeuserdata(blogdate1,blogauthor1, blogtitle1,blogtxt1);

    }

    private void storeuserdata(String blogdate1, String blogauthor1, String blogtitle1, String blogtxt1) {
        firebaseDatabase= FirebaseDatabase.getInstance();
        final String rand=UUID.randomUUID().toString();
        databaseReference= firebaseDatabase.getReference("blog").child(rand);;

        Blogmodel=new blogmodel();
        Blogmodel.setBlogAuthor(blogauthor1);
        Blogmodel.setBlogDate(blogdate1);
        Blogmodel.setBlogTitle(blogtitle1);
        Blogmodel.setBlogTxt(blogtxt1);
        Blogmodel.setBlogimg(pickedImageUri.toString());





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(Blogmodel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}