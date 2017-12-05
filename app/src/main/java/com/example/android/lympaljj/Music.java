package com.example.android.lympaljj;

/**
 * Created by LuYu on 2017/11/29.
 */

public class Music {
    private String name;
    private String imageUrl;

        public Music(String name,String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName(){
        return name;
    }

    public String getImageUrl(){
        return imageUrl;
    }


}
