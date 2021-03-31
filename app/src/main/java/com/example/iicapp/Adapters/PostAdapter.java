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

public class PostAdapter extends FirebaseRecyclerAdapter<PostModel,PostAdapter.myViewHolder> {


    public PostAdapter(@NonNull FirebaseRecyclerOptions<PostModel> options) {
        super(options);
    }


    @NonNull
    @Override
    public PostAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cardview,parent,false);
        return new myViewHolder(view);
    }

    @Override
    protected void onBindViewHolder (@NonNull final myViewHolder holder, final int position, @NonNull final PostModel model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        if(model.getImageurl() != null){
            Glide.with(holder.post).load(model.getImageurl()).into(holder.post);
        }else{
            holder.post.setImageResource(R.drawable.logo);
        }
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DescriptionActivity.class);
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
            title=(TextView)itemView.findViewById(R.id.title);
            description =(TextView)itemView.findViewById(R.id.description);
            post = (ImageView)itemView.findViewById(R.id.post);
            v = itemView;
        }
    }
}
