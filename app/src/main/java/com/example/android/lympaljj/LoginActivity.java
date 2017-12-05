package com.example.android.lympaljj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.lympaljj.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.string.ok;

public class LoginActivity extends AppCompatActivity {
    private String susername;
    private String suserpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText tusername = (EditText) findViewById(R.id.Tusername);
        final EditText tuserpassword = (EditText) findViewById(R.id.Tpassword);
        Button loginbutton = (Button) findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                susername = tusername.getText().toString();
                suserpassword = tuserpassword.getText().toString();
                Log.d("LoginActivity","name: "+susername);
                Log.d("LoginActivity","pass: "+suserpassword);
                String myurl = "http://106.14.133.240:3000/login/cellphone?phone="
                        +susername+"&password="+suserpassword;
                Log.d("LoginActivity","URL: "+myurl);
                Login(myurl);
//                Intent intent = new Intent();
//                intent.putExtra("uid","108259219");
//                intent.putExtra("userName","1_18805278238");
//                setResult(RESULT_OK,intent);
//                finish();
            }
        });
    }

    private void Login(String url){
        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Intent intent = new Intent();
                intent.putExtra("uid","failure");
                intent.putExtra("userName","failure");
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d("LoginActivity","responseData "+responseData);
                Intent intent = new Intent();
                intent.putExtra("uid","108259219");
                intent.putExtra("userName","1_18805278238");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
