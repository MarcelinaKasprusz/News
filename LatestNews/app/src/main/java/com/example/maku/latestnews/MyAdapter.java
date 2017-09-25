package com.example.maku.latestnews;


import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Maku on 2017-09-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<FeedItem> feedItems = new ArrayList<FeedItem>();
    Context context;
    FeedItem current;

    public MyAdapter( Context context, ArrayList<FeedItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        current = feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailURL()).into(holder.thumbnailURL);
        holder.cardView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, Details.class);
               intent.putExtra("link", current.getLink());
                context.startActivity(intent);

           }
        });

    }


    @Override
    public int getItemCount() {

        return (feedItems.size() > 0) ? feedItems.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date;
        ImageView thumbnailURL;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleText);
            description = (TextView) itemView.findViewById(R.id.descriptionText);
            date = (TextView) itemView.findViewById(R.id.dateText);
            thumbnailURL = (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }

    }
}
