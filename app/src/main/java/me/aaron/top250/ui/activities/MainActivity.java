package me.aaron.top250.ui.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static com.google.common.base.Preconditions.checkNotNull;

import me.aaron.top250.R;
import me.aaron.top250.adapters.MainRecyAdapters;
import me.aaron.top250.contract.MainContract;
import me.aaron.top250.model.bean.ItemsBean;
import me.aaron.top250.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.IMainView{

    private Toolbar toolbarMain;
    private ActionBar actionBarMain;
    private RecyclerView recyclerMain;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton buttonToTop;

    private MainPresenter mainPresenter;
    private MainRecyAdapters mainAdapter;

    public static boolean isStart() {
        return isStart;
    }

    //在model层那边调用  主要是为了分离第一次和加载更多
    public static void setIsStart(boolean isStart) {
        MainActivity.isStart = isStart;
    }

    private static boolean isStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        isStart = true;
        mainPresenter = new MainPresenter(this);
        mainPresenter.start();
    }

    //大体上所有的相关控件的一些初始化工作都在这个方法里面完成
    private void initView(){
        toolbarMain = (Toolbar) findViewById(R.id.tb_main_activity);
        setSupportActionBar(toolbarMain);
        if (actionBarMain == null){
            actionBarMain = getSupportActionBar();
            actionBarMain.setTitle("豆瓣电影Top250");
        }
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorGreen);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isStart = true;
                mainPresenter.startRefresh();
            }
        });
        buttonToTop = (FloatingActionButton) findViewById(R.id.btn_back_to_top);
        buttonToTop.setOnClickListener(clickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMain = (RecyclerView) findViewById(R.id.recycler_main);
        recyclerMain.setLayoutManager(linearLayoutManager);
        recyclerMain.addOnScrollListener(scrollListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_back_to_top:
                    //自动的滑动回滚到顶部
                    recyclerMain.smoothScrollToPosition(0);
            }
        }
    };

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            //这个方法监听recyclerview的滑动，通过滑动监听来判断是否到达底部来处理加载更多的逻辑
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //当前已经在Adapter中有的item总数，第一次因该是25+1
            int endPosition = recyclerView.getAdapter().getItemCount();
            if (endPosition == linearLayoutManager.findLastCompletelyVisibleItemPosition()+1){
                //if 里面的判断是判断Adapter中的item总数是否对应当前已经在屏幕中的最后一个显示出来的item的position
                //如果是的话就说明已经到达底部了，应该处理加载更多的逻辑
                if (endPosition <=250){
                   //没有加载完成所有的item就调用presenter层的加载更多方法
                    mainPresenter.askMoreItems(recyclerView.getAdapter().getItemCount()-1);
                }else {
                    //这一层的判断是为了在加载完所有的250条item之后，应该永久的移除掉footview并且
                    //不再进行相应的加载更多的逻辑处理
                    mainAdapter.notifyItemRemoved(recyclerMain.getAdapter().getItemCount());
                    Toast.makeText(MainActivity.this,"已经到底啦",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };



    @Override
    public void showItems() {
        //最开始的那些25个item就是通过这个方法展示出来的，刷新数据调用的也是这个方法
        stopRefresh();
        mainAdapter = new MainRecyAdapters(this,mainPresenter.returnStartItems());
        recyclerMain.setAdapter(mainAdapter);
    }

    @Override
    public void stopRefresh() {
        //刷新更多的停止方法，由数据更新是在model层，而处理相关的逻辑是在presenter层，在那边需要通过接口来停止刷新
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMore() {
        stopRefresh();
        //将底部自定义的加载更多的footview移除
        mainAdapter.notifyItemRemoved(recyclerMain.getAdapter().getItemCount()-1);
        //将加载更多得到的item给传递给Adapter的初始化数据的方法，在那个方法里面完成recyclerview的加载更多
        mainAdapter.initData(mainPresenter.returnMoreItems());
        //在Adapter那边加载更多数据完成之后，需要在这边调用方法来提示数据更新
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(MainContract.IMainPresenter presenter) {
        mainPresenter = (MainPresenter) checkNotNull(presenter);
    }
}
