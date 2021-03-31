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
import com.example.iicapp.Models.NotificationModel;
import com.example.iicapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends FirebaseRecyclerAdapter<NotificationModel,NotificationAdapter.myViewHolder> {

    private List<NotificationModel> mNotifs;


    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<NotificationModel> options, List<NotificationModel> mNotifs) {
        super(options);
        this.mNotifs = mNotifs;
    }

    @NonNull
    @Override
    public NotificationAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_cardview,parent,false);
        return new NotificationAdapter.myViewHolder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull NotificationAdapter.myViewHolder holder, final int position, @NonNull NotificationModel model) {
        final int posRevers = mNotifs.size() - (position + 1);
        holder.title.setText(mNotifs.get(position).getTitle());
        if(mNotifs.get(position).getNotifImageUrl() != null){
            Glide.with(holder.post).load(mNotifs.get(position).getNotifImageUrl()).into(holder.post);
        }else{
            holder.post.setImageResource(R.drawable.logo);
        }
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DescriptionActivity.class);
                intent.putExtra("postKey", getRef(posRevers).getKey());
                v.getContext().startActivity(intent);
            }
        });
    }

    private final int limit = 1;

    @Override
    public int getItemCount() {

        if(mNotifs.size() > limit){
            return limit;
        }
        else
        {
            return mNotifs.size();
        }

    }






    class myViewHolder extends RecyclerView.ViewHolder{

        /*CircleImageView img;*/
        TextView title;
        CircleImageView post;
        View v;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            /*img=(CircleImageView)itemView.findViewById(R.id.img1);*/
            title=(TextView)itemView.findViewById(R.id.notif_title);
            post = (CircleImageView) itemView.findViewById(R.id.notif_img);
            v = itemView;
        }
    }
}
