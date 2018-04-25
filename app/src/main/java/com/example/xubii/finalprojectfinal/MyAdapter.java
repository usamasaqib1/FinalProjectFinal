package com.example.xubii.finalprojectfinal;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    private ArrayList<ground> items;
    private int itemLayout;
    private Context context;

    public MyAdapter(ArrayList<ground> items, int itemLayout, Context context)
    {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

        if(items!=null && holder!=null)
        {

            holder.name.setText(items.get(position).getGroundName());
            holder.votes.setText(items.get(position).getVotes().toString());
            holder.location.setText(items.get(position).getLocation());
            holder.rb.setMax(5);
            holder.rb.setNumStars(5);
            holder.rb.setRating(items.get(position).getRatting());
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Images/"+items.get(position).getGroundName()+" "+items.get(position).getLocation()+".jpg");
         Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(mStorage)
                    .into(holder.imgView);
        }
    }

    @Override
    public int getItemCount()
    {
        if(items !=null)
            return items.size();
        else
            return 0;
    }
}
