package com.example.android.lympaljj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by LuYu on 2017/11/29.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private Context mContext;
    private List<Music> mMusicList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView musicImage;
        TextView musicname;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            musicImage = (ImageView) itemView.findViewById(R.id.music_image);
            musicname = (TextView) itemView.findViewById(R.id.music_name);
        }
    }

    public MusicAdapter(List<Music> musicList){
        mMusicList = musicList;
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Music music = mMusicList.get(position);
                Intent intent = new Intent(mContext,MusicActivity.class);
                intent.putExtra(MusicActivity.MUSIC_NAME,music.getName());
                intent.putExtra(MusicActivity.MUSIC_IMAGE_URL,music.getImageUrl());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
        Music music = mMusicList.get(position);
        holder.musicname.setText(music.getName());
        Glide.with(mContext).load(music.getImageUrl()).into(holder.musicImage);
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
}
