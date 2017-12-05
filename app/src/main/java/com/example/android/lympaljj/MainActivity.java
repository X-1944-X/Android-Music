package com.example.android.lympaljj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.android.lympaljj.R.mipmap.ic_launcher;

public class MainActivity extends AppCompatActivity {

    private TextView myusername;
    private TextView myuserId;

    private String pubuname;
    private String pubuid;

    private SwipeRefreshLayout swipeRefresh;

    private DrawerLayout mDrawerLayout;

    private Music[] musics = {
            new Music("弱水三千","http://p4.music.126.net/etmTuJkra7wkdw5tjiIQjw==/16611421672873352.jpg"),
            new Music("带你去旅行","http://p4.music.126.net/2FvsVxoz8v5GcDUBeFw5kw==/109951163053302335.jpg"),
            new Music("刚好遇见你","http://p4.music.126.net/4mUKGD6wyIW0XpTWXiFcdQ==/19124905253588326.jpg"),
            new Music("相思赋予谁","http://p1.music.126.net/W37RhrD1dRXD30vnSdyqgw==/16642207998646210.jpg"),
            new Music("逐梦令","http://p4.music.126.net/a2b-wcVrNbB1Ktum8q-uyw==/7870304232586557.jpg"),
            new Music("棠梨煎雪","http://p1.music.126.net/LBnYDAUED2mD1veBvBnC8g==/5859297464524710.jpg"),
            new Music("牵丝戏","http://p1.music.126.net/Nd86SOcyCxU5Qu7jdZn_MQ==/7725168696876736.jpg"),
            new Music("远走高飞","http://p1.music.126.net/elfqBKIdad0KYCCeKQpDSA==/18700493767108166.jpg"),
            new Music("Intro","http://p1.music.126.net/Xik6a3wXoGPOLI1GNXGdFQ==/825733232504415.jpg"),
            new Music("Despacito","http://p1.music.126.net/vA4UF75gS1Qpyx07B0acrw==/18521273371761277.jpg")
    };

    private List<Music> musicList = new ArrayList<>();

    private MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Snackbar-Undo-onClick",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        initMusics();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMusics();
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.login:
                        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                        Intent loginintent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(loginintent,2);
                        break;
                    case R.id.friends:
                        Toast.makeText(MainActivity.this, "Friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        Toast.makeText(MainActivity.this, "Location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.recommend:
                        Toast.makeText(MainActivity.this, "Recommend", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mysonglist:
                        Toast.makeText(MainActivity.this, "mysonglist", Toast.LENGTH_SHORT).show();
                        break;
                    default:mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void initMusics(){
        int count = musics.length;
        musicList.clear();
        for (int i = 0;i<count;i++){
//            Random random = new Random();
//            int index = random.nextInt(musics.length);
            musicList.add(musics[i]);
        }
    }

    private void refreshMusics() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initMusics();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.website:
                Toast.makeText(this, "You clicked Website", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this,MyWebView.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.httptest:
                Toast.makeText(this,"You clicked HTTPTest",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this,HTTPTestActivity.class);
                startActivity(intent2);
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String uid = data.getStringExtra("uid");
                    String username = data.getStringExtra("userName");
                    Log.d("FirstActivity",uid);
                    Log.d("FirstActivity",username);
                    pubuid = uid;
                    pubuname = username;
//                    myusername.setText(username);
//                    myuserId.setText(uid);
//                    myusername = (TextView) findViewById(R.id.username);
//                    myuserId = (TextView) findViewById(R.id.userId);
//                    myusername.setText(pubuname);
//                    myuserId.setText(pubuid);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK){
                    pubuid = data.getStringExtra("uid");
                    pubuname= data.getStringExtra("userName");
                    myusername = (TextView) findViewById(R.id.username);
                    myuserId = (TextView) findViewById(R.id.userId);
                    Log.d("MainActivity","uname"+pubuname);
                    Log.d("MainActivity","uid"+pubuid);
                    myusername.setText(pubuname);
                    myuserId.setText(pubuid);
                }
                break;
            default:
        }
    }
}
