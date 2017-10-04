package me.aaron.top250.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.aaron.top250.R;
import me.aaron.top250.adapters.InfoRecyAdapters;
import me.aaron.top250.contract.InfoContarct;
import me.aaron.top250.model.bean.InfoBean;
import me.aaron.top250.presenter.InfoPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class InfoActivity extends AppCompatActivity implements InfoContarct.IInfoView{


    private RecyclerView recyclerInfo;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView ivMoviePic;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    private String id;
    private InfoPresenter infoPresenter;
    private InfoRecyAdapters infoRecyAdapters;

    private ActionBar actionBar;

    private String shareUrl;
    private String mobileUrl;
    private String tbTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        id = getIntent().getStringExtra("id");
        initView();
        infoPresenter = new InfoPresenter(this);
        infoPresenter.askSubject(id);
    }

    private void initView(){
        recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        toolbar = (Toolbar) findViewById(R.id.tb_info_activity);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivMoviePic = (ImageView) findViewById(R.id.iv_movie_biggest_pic);
        toolbar = (Toolbar) findViewById(R.id.tb_info_activity);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_floating_douban);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobileUrl != null){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mobileUrl));
                    view.getContext().startActivity(intent);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerInfo.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                shareArticle();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu,menu);
        return true;
    }

    public void shareArticle() {
        Intent sharedIntent = new Intent();
        sharedIntent.setAction(Intent.ACTION_SEND);
        sharedIntent.putExtra(Intent.EXTRA_TEXT,shareUrl);
        sharedIntent.setType("text/plain");
        startActivity(Intent.createChooser(sharedIntent, "分享文章到"));
    }


    @Override
    public void setPresenter(InfoContarct.IInfoPresenter presenter) {
        infoPresenter = (InfoPresenter) checkNotNull(presenter);
    }

    @Override
    public void showTopPic(String imageString) {
        recyclerInfo.setFocusable(false);
        Glide.with(this).load(imageString).into(ivMoviePic);
    }

    @Override
    public void showItems() {
        InfoBean infoBean = infoPresenter.returnSubject();
        shareUrl = infoBean.getShare_url();
        mobileUrl = infoBean.getMobile_url();
        tbTitle = infoBean.getTitle();
        showTopPic(infoBean.getImages().getLarge());
        infoRecyAdapters = new InfoRecyAdapters(infoBean,this);
        recyclerInfo.setAdapter(infoRecyAdapters);
    }
}
