package com.example.iicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iicapp.Adapters.PeopleAdapter;
import com.example.iicapp.Models.PeopleModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PeopleActivity extends AppCompatActivity {

    RecyclerView recviewPeople;
    PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        recviewPeople = findViewById(R.id.recview);
        recviewPeople.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PeopleModel> options =
                new FirebaseRecyclerOptions.Builder<PeopleModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), PeopleModel.class)
                        .build();

        peopleAdapter  =new PeopleAdapter(options);
        recviewPeople.setAdapter(peopleAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        peopleAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        peopleAdapter.stopListening();
    }

}