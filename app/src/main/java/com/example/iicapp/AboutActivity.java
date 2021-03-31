package com.example.iicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iicapp.Adapters.GalImageAdapter;
import com.example.iicapp.Adapters.PostAdapter;
import com.example.iicapp.Models.GalleryModel;
import com.example.iicapp.Models.PostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AboutActivity extends AppCompatActivity {

    ImageView people;
    TextView description;
    ImageView logo;

    RecyclerView recviewGal;
    GalImageAdapter galImageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        people = findViewById(R.id.people);
        description = findViewById(R.id.text);
        logo = findViewById(R.id.logo);
        recviewGal = findViewById(R.id.recviewGallery);

        LinearLayoutManager homeLayoutManager = new LinearLayoutManager(this);
        homeLayoutManager.setReverseLayout(true);
        homeLayoutManager.setStackFromEnd(true);
        recviewGal.setLayoutManager(homeLayoutManager);



        FirebaseRecyclerOptions<GalleryModel> options =
                new FirebaseRecyclerOptions.Builder<GalleryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Gallery"), GalleryModel.class)
                        .build();



        galImageAdapter = new GalImageAdapter(options);
        recviewGal.setAdapter(galImageAdapter);


        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this,PeopleActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        galImageAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        galImageAdapter.stopListening();
    }
}