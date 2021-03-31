package com.example.iicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DescriptionActivity extends AppCompatActivity {

    TextView title,description;
    ImageView image;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        title = findViewById(R.id.descTitle);
        description = findViewById(R.id.descDescription);
        image = findViewById(R.id.descPost);
        reference = FirebaseDatabase.getInstance().getReference().child("Posts");

        String postKey = getIntent().getStringExtra("postKey");

        reference.child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String reftitle = snapshot.child("title").getValue().toString();
                    String refimageurl = snapshot.child("imageurl").getValue().toString();
                    String refdescription = snapshot.child("description").getValue().toString();

                    Glide.with(DescriptionActivity.this).load(refimageurl).into(image);

                    title.setText(reftitle);
                    description.setText(refdescription);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}