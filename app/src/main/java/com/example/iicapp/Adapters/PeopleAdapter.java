package com.example.iicapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iicapp.Models.PeopleModel;
import com.example.iicapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;


public class PeopleAdapter extends FirebaseRecyclerAdapter<PeopleModel,PeopleAdapter.myViewHolder>{

    public PeopleAdapter(@NonNull FirebaseRecyclerOptions<PeopleModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PeopleModel model) {
        holder.name.setText(model.getName());
        holder.designation.setText(model.getDesignation());
        holder.email.setText(model.getEmail());
        Glide.with(holder.img).load(model.getImageurl()).into(holder.img);
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.people_cardview,parent,false);
        return new myViewHolder(view);
    }


    static class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,designation,email;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            designation =(TextView)itemView.findViewById(R.id.designationtext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
        }
    }

}
