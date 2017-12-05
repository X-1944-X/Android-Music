package com.example.android.lympaljj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lympaljj.Util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPTestActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httptest);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(HTTPTestActivity.this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request){
            //sendRequestWithHttpURLConnection();
            Toast.makeText(this,"sendRequestWithOkHttp",Toast.LENGTH_SHORT).show();
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp(){
        String address = "http://106.14.133.240:3000/music/url?id=479565736";
        HttpUtil.sendOkHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            //.url("https://www.baidu.com")
//                            //.url("http://106.14.133.240:3000/music/url?id=479565736")
//                            .url("http://45.77.121.237/jsontest/test01.json")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    if (responseData == null){
//                        Toast.makeText(HTTPTestActivity.this,"responseData is null",Toast.LENGTH_SHORT).show();
//                    }
//                    parseJSONWithGSON(responseData);
//                    //parseJSONWithJSONObject(responseData);
//                    //showResponse(responseData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<AndroidApi> apiList = gson.fromJson(jsonData,
                new TypeToken<List<AndroidApi>>(){}.getType());
        for (AndroidApi androidApi:apiList){
            Log.d("HTTPTestActivity","id is "+androidApi.getId());
            Log.d("HTTPTestActivity","name is "+androidApi.getName());
            Log.d("HTTPTestActivity","version is "+androidApi.getVersion());
        }
    }

    private void parseJSONWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("HTTPTestActivity","id is "+id);
                Log.d("HTTPTestActivity","name is "+name);
                Log.d("HTTPTestActivity","version is "+version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line ;
                    while ((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}
