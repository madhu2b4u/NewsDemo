package com.demo.newyorktimes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.R;
import com.demo.newyorktimes.datamodels.Json;
import com.demo.newyorktimes.datamodels.Result;
import com.demo.newyorktimes.ui.DetailsActivity;
import com.demo.utils.AppConstants;
import com.demo.utils.NetworkDetection;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Result> newsList;
    Activity activity;
    public void updateList(ArrayList<Result> list){
        newsList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle,textViewDate,textViewAuthor,textViewReadMore;
        ImageView articleImage;
        CardView cardLayout;

        public MyViewHolder(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewAuthor = view.findViewById(R.id.textViewAuthor);
            articleImage = view.findViewById(R.id.articleImage);
            textViewReadMore = view.findViewById(R.id.textViewReadMore);
            cardLayout = view.findViewById(R.id.cardLayout);
        }
    }


    public NewsAdapter(Activity context, ArrayList<Result> newsList) {
        this.activity = context;
        this.newsList = newsList;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_news_details, parent, false);

        return new NewsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {
        final Result item = newsList.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDate.setText(AppConstants.formatdate(item.getPublishedDate()));
        try {
            Glide.with(activity).load(item.getMedia().get(0).getMediaMetadata().get(1).getUrl()).into(holder.articleImage);
        } catch (Exception e) {
            Log.e(NewsAdapter.class.getName(), e.getMessage());
        }

        //redirect to the news details page
        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra("data", Json.gson().toJson(item));
                activity.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }




}

