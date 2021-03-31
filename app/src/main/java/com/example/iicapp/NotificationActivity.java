package com.example.iicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.iicapp.Adapters.NotificationAdapter;
import com.example.iicapp.Adapters.PostAdapter;
import com.example.iicapp.Models.NotificationModel;
import com.example.iicapp.Models.PostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recviewNotifs;
    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recviewNotifs = findViewById(R.id.recviewNotif);
        LinearLayoutManager notifLayoutManager = new LinearLayoutManager(this);
        recviewNotifs.setLayoutManager(notifLayoutManager);

        FirebaseRecyclerOptions<NotificationModel> options =
                new FirebaseRecyclerOptions.Builder<NotificationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), NotificationModel.class)
                        .build();


        final List<NotificationModel> mNotifs = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        NotificationModel notif = snapshot.getValue(NotificationModel.class);
                        mNotifs.add(notif);
                    }

                    Collections.reverse(mNotifs);

                    notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        notificationAdapter = new NotificationAdapter(options, mNotifs);
        recviewNotifs.setAdapter(notificationAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        notificationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notificationAdapter.stopListening();
    }
}