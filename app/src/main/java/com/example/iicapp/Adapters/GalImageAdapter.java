package com.example.iicapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iicapp.Models.GalleryModel;

import com.example.iicapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class GalImageAdapter extends FirebaseRecyclerAdapter<GalleryModel,GalImageAdapter.myViewHolder> {



    public GalImageAdapter(@NonNull FirebaseRecyclerOptions<GalleryModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public GalImageAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gal_img_cardview,parent,false);
        return new myViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull GalleryModel model) {

        Glide.with(holder.galImg).load(model.getGalImguRL()).into(holder.galImg);
    }



    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView galImg;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            galImg=(ImageView)itemView.findViewById(R.id.galImage);

        }
    }
}
