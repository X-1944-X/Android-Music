package com.example.android.lympaljj;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MusicActivity extends AppCompatActivity {

    public static final String MUSIC_NAME = "music_name";
    public static final String MUSIC_IMAGE_URL = "music_image_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MUSIC_NAME);
        String imageUrl = intent.getStringExtra(MUSIC_IMAGE_URL);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView musicImageView = (ImageView) findViewById(R.id.music_image_view);
        TextView musicContentText = (TextView) findViewById(R.id.music_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(name);
        Glide.with(this).load(imageUrl).into(musicImageView);
        String MusicContent = generateMusicContent(name);
        musicContentText.setText(MusicContent);
    }

    private String generateMusicContent(String name) {
        StringBuilder MusicContent = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            MusicContent.append(name);
        }
        return MusicContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
