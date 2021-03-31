package com.example.iicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iicapp.Adapters.PeopleAdapter;
import com.example.iicapp.Adapters.PostAdapter;
import com.example.iicapp.Models.PeopleModel;
import com.example.iicapp.Models.PostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Stack;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recviewPosts;
    PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recviewPosts = findViewById(R.id.recviewPost);
        LinearLayoutManager homeLayoutManager = new LinearLayoutManager(this);
        homeLayoutManager.setReverseLayout(true);
        homeLayoutManager.setStackFromEnd(true);
        recviewPosts.setLayoutManager(homeLayoutManager);

        FirebaseRecyclerOptions<PostModel> options =
                new FirebaseRecyclerOptions.Builder<PostModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), PostModel.class)
                        .build();



        postAdapter = new PostAdapter(options);
        recviewPosts.setAdapter(postAdapter);
    }

        @Override
        protected void onStart() {
            super.onStart();
            postAdapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            postAdapter.stopListening();
        }

}