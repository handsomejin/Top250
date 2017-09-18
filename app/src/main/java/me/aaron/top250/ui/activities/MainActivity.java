package me.aaron.top250.ui.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import me.aaron.top250.R;
import me.aaron.top250.adapters.MainRecyAdapters;
import me.aaron.top250.contract.MainContract;
import me.aaron.top250.model.bean.ItemBean;
import me.aaron.top250.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.IMainView{

    private Toolbar toolbarMain;
    private ActionBar actionBarMain;
    private RecyclerView recyclerMain;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton buttonToTop;

    private MainPresenter mainPresenter = new MainPresenter(this);
    private MainRecyAdapters mainAdapter;
    private boolean isLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            Log.d("66666666666", "onScrolled: ");
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int endPosition = recyclerView.getAdapter().getItemCount();
            Log.d("77777777777", "onScrolled: "+endPosition+"-----"+ linearLayoutManager.findLastCompletelyVisibleItemPosition());
            if (endPosition == linearLayoutManager.findLastCompletelyVisibleItemPosition()+1){
                mainPresenter.askMoreItems(recyclerView.getAdapter().getItemCount()-1);
            }
        }
    };



    @Override
    public void showItems(List<ItemBean> items) {
        mainAdapter = new MainRecyAdapters(this,items);
        recyclerMain.setAdapter(mainAdapter);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMore() {
        mainAdapter.loadMore();

    }


    @Override
    public void setPresenter(MainContract.IMainPresenter presenter) {
        mainPresenter = (MainPresenter) checkNotNull(presenter);
    }
}
