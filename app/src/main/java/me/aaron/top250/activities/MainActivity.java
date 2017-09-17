package me.aaron.top250.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.aaron.top250.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private ActionBar actionBarMain;
    private RecyclerView recyclerMain;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        toolbarMain = (Toolbar) findViewById(R.id.tb_main_activity);
        setSupportActionBar(toolbarMain);
        if (actionBarMain == null){
            actionBarMain = getSupportActionBar();
            actionBarMain.setTitle("豆瓣电影Top250");
        }
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorGreen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMain = (RecyclerView) findViewById(R.id.recycler_main);
        recyclerMain.setLayoutManager(linearLayoutManager);
    }
}
