package com.cjh15hub.mybirds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cjh15 on 2/28/2017.
 */

public class BirdListAdapter extends RecyclerView.Adapter<BirdListAdapter.BViewHolder> {

    private List<Bird> birdList;

    public BirdListAdapter(List<Bird> birdList){
        this.birdList = birdList;
    }

    @Override
    public BViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bird_list_row, parent, false);

        return new BViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BViewHolder holder, int position) {
        Bird bird = birdList.get(position);
        holder.thumb.setImageResource(R.drawable.placeholder);
        //holder.thumb.setImageResource(bird.);
        holder.name.setText(bird.getName());
        holder.desc.setText(bird.getDescription());
    }

    @Override
    public int getItemCount() {
        return birdList.size();
    }


    public class BViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView thumb;
        public TextView desc;

        public BViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name_li);
            thumb = (ImageView) view.findViewById(R.id.thumb_li);
            desc = (TextView) view.findViewById(R.id.desc_li);
        }


    }

}
