package com.example.android.lympaljj;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        WebView mywebview = (WebView) findViewById(R.id.web_view);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.loadUrl("lyembark.xyz");
        FloatingActionButton fabwebview = (FloatingActionButton) findViewById(R.id.fabwv);
        fabwebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("uid","108259219");
                intent.putExtra("userName","1_18805278238");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
