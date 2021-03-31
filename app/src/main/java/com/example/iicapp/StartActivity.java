package com.example.iicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.nex3z.notificationbadge.NotificationBadge;
import android.os.Handler;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Objects;


public class StartActivity extends AppCompatActivity {

    CircleMenu circleMenu;
    NotificationBadge notificationBadge;
    ImageView notifImage;
    RelativeLayout cont;
    AnimationDrawable anim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        circleMenu =findViewById(R.id.circle_menu);

        notificationBadge = findViewById(R.id.badge);
        notifImage = findViewById(R.id.notifImage);
        cont = findViewById(R.id.container);

       anim = (AnimationDrawable) cont.getBackground();

        anim.setEnterFadeDuration(1000);


        anim.setExitFadeDuration(1000);



        circleMenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.ic_menu,R.drawable.ic_cancel)
                .addSubMenu(Color.parseColor("#ffffff"), R.drawable.ic_home)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.ic_about)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.ic_search)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {

                        switch (index){
                            case 0:
                                Intent intent = new Intent(StartActivity.this,HomeActivity.class);
                                startActivity(intent);
                                notificationBadge.setNumber(0);
                                break;
                            case 1:
                                intent = new Intent(StartActivity.this,AboutActivity.class);
                                startActivity(intent);
                                break;

                            case 2:
                                intent = new Intent(StartActivity.this,SearchActivity.class);
                                startActivity(intent);
                                break;


                        }

                    }
                });






        final HashMap<String, Object> countmap = new HashMap<>();
        /*int prev=0;*/
        final DatabaseReference mCountRef = FirebaseDatabase.getInstance().getReference().child("Count");
        /*mCountRef.setValue(prev);*/

        FirebaseDatabase.getInstance().getReference().child("Posts").addValueEventListener(new ValueEventListener() {

            int count = 0,prevCount=0;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    count++;
                }

                notificationBadge.setNumber(0);
                FirebaseDatabase.getInstance().getReference().child("Count").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {


                                prevCount = Integer.parseInt(snapshot.getValue().toString());



                            if (count != prevCount) {
                                FirebaseDatabase.getInstance().getReference().child("Count").removeValue();
                                countmap.clear();
                                mCountRef.setValue(count);
                                int notifCount,notifC;
                                if(count%2 == 0){
                                    notifCount = count + 1;
                                    notifC = notifCount%2;
                                }else{
                                    notifCount = count;
                                    notifC = notifCount%2;
                                }
                                notificationBadge.setNumber(notifC);


                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });




        notifImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,HomeActivity.class);
                startActivity(intent);
                notificationBadge.setNumber(0);
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    // Stopping animation:- stop the animation on onPause.
    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }





}