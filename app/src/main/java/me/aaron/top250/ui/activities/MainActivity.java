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

    public static boolean isRefresh() {
        return isRefresh;
    }

    public static void setIsRefresh(boolean isRefresh) {
        MainActivity.isRefresh = isRefresh;
    }

    private static boolean isRefresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        isRefresh = true;
        mainPresenter = new MainPresenter(this);
        mainPresenter.start();
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
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
                    recyclerMain.smoothScrollToPosition(0);
            }
        }
    };

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int endPosition = recyclerView.getAdapter().getItemCount();
            if (endPosition == linearLayoutManager.findLastCompletelyVisibleItemPosition()+1){
                if (endPosition <=250){
                    mainPresenter.askMoreItems(recyclerView.getAdapter().getItemCount()-1);
                }else {
                    mainAdapter.notifyItemRemoved(recyclerMain.getAdapter().getItemCount());
                    Toast.makeText(MainActivity.this,"已经到底啦",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };



    @Override
    public void showItems(ItemsBean itemsBean) {
        mainAdapter = new MainRecyAdapters(this,itemsBean);
        recyclerMain.setAdapter(mainAdapter);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMore(ItemsBean items) {
        mainAdapter.notifyItemRemoved(recyclerMain.getAdapter().getItemCount()-1);
        mainAdapter.initData(items);
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(MainContract.IMainPresenter presenter) {
        mainPresenter = (MainPresenter) checkNotNull(presenter);
    }
}
