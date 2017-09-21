package me.aaron.top250.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.aaron.top250.R;
import me.aaron.top250.contract.ActorContract;

public class ActorActivity extends AppCompatActivity implements ActorContract.IActorView{

    /**
     * 这一层只有一个Activity的view层，界面展示使用webview加载url实现
     * 不再写presenter和model层，也不再从后台获取json数据及其解析，项目demo深度到此为止
     */


    private WebView webView;
    private Toolbar toolbar;
    private ActionBar actionBar;

    private final String urlPre =  "https://movie.douban.com/celebrity/";
    private final String urlSuf = "/mobile";


    private String name;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        initView();
        setTbTilte();
        showWebView();
    }

    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.tb_actor);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showWebView() {
        Log.d("hjhhhhhhhh", "showWebView: " + id);
        webView.loadUrl(urlPre+id+urlSuf);
    }

    @Override
    public void setTbTilte() {
        actionBar.setTitle(name);
    }
}
