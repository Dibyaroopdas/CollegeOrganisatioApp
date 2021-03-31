package com.example.iicapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iicapp.DescriptionActivity;
import com.example.iicapp.Models.PostModel;
import com.example.iicapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SearchAdapter extends FirebaseRecyclerAdapter<PostModel,SearchAdapter.myViewHolder> {


    public SearchAdapter(@NonNull FirebaseRecyclerOptions<PostModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public SearchAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_cardview,parent,false);
        return new SearchAdapter.myViewHolder(view);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull PostModel model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        Glide.with(holder.post).load(model.getImageurl()).into(holder.post);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DescriptionActivity.class);
                /*intent.putExtra("desc", model.getDescription());*/
                intent.putExtra("postKey", getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });
    }







    class myViewHolder extends RecyclerView.ViewHolder{

        /*CircleImageView img;*/
        TextView title,description;
        ImageView post;
        View v;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            /*img=(CircleImageView)itemView.findViewById(R.id.img1);*/
            title=(TextView)itemView.findViewById(R.id.searchTitle);
            description =(TextView)itemView.findViewById(R.id.searchDesc);
            post = (ImageView)itemView.findViewById(R.id.searchImage);
            v = itemView;
        }
    }
}
