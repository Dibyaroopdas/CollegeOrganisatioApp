package com.example.iicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.iicapp.Adapters.SearchAdapter;
import com.example.iicapp.Models.PostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity {

    RecyclerView searchRecView;
    SearchAdapter searchAdapter;
    EditText searchText;
    ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchRecView = findViewById(R.id.searchRecView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        searchRecView.setLayoutManager(mLayoutManager);

        FirebaseRecyclerOptions<PostModel> options =
                new FirebaseRecyclerOptions.Builder<PostModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), PostModel.class)
                        .build();

        searchAdapter = new SearchAdapter(options);
        searchRecView.setAdapter(searchAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        searchAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        searchAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.searchMenuBar);

        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase();
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
               processSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s)
    {
        FirebaseRecyclerOptions<PostModel> options =
                new FirebaseRecyclerOptions.Builder<PostModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("title").startAt(s).endAt(s+"\uf8ff"), PostModel.class)
                        .build();

        searchAdapter = new SearchAdapter(options);
        searchAdapter.startListening();
        searchRecView.setAdapter(searchAdapter);
    }
}