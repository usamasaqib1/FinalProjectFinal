package com.example.xubii.finalprojectfinal;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder
{

    public TextView name;
    public ImageView imgView;
    public TextView location;
    public RatingBar rb;
    public TextView votes;
    public MyViewHolder(View v) {
        super(v);
        name = (TextView) v.findViewById(R.id.textView9);
        votes = (TextView) v.findViewById(R.id.textView14);
        location = (TextView) v.findViewById(R.id.textView11);
        imgView = (ImageView) v.findViewById(R.id.imageView2);
        rb = (RatingBar) v.findViewById(R.id.ratingBar);
    }
}
