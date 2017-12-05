package com.example.android.lympaljj;

/**
 * Created by LuYu on 2017/12/3.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
