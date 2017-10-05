package me.aaron.top250.model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import me.aaron.top250.contract.MainContract;
import me.aaron.top250.model.bean.ItemsBean;
import me.aaron.top250.ui.activities.MainActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aaron on 17-9-18.
 */

public class ItmesModel implements MainContract.IMainModel {

    //请求数据的前缀和后缀
    private final String requestUrlPre = "http://api.douban.com/v2/movie/top250?start=";
    private final String requestUtlSuf = "&count=25";
    //Presenter层的接口引用
    private MainContract.IMainPresenter imainPresenter;
    //这个值用来记录已经加载了多少个item，在加载更多的时候会用到
    private static int positionNum = 0;

    //数据模型
    private ItemsBean items;

    public ItmesModel(MainContract.IMainPresenter iMainPresenter){
        this.imainPresenter = iMainPresenter;
    }

    //刷新数据就直接相当于重新第一次请求数据
    @Override
    public void startRefresh() {

        getItems(0);
    }

    //加载数据在这个方法里面构造异步加载类的对象
    @Override
    public void getItems(int startNumber) {
        new requestItems().execute(startNumber);
    }

    //在Presenter层中会调用该方法，得到请求的最开使得25个item数据
    @Override
    public ItemsBean setStartItems() {
        return items;
    }

    //同上，只是请求的是更多的item数据
    @Override
    public ItemsBean setMoreItems(){
        return items;
    }



    //异步类处理网络请求
    class requestItems extends AsyncTask<Integer,Void,String>{

        @Override
        protected String doInBackground(Integer... startNum) {
            try{
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url(requestUrlPre + startNum[0] + requestUtlSuf).build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            if (s != null){
                items = new Gson().fromJson(s,new TypeToken<ItemsBean>(){}.getType());
                if (MainActivity.isStart()){
                    imainPresenter.callShowStart();
                    MainActivity.setIsStart(false);
                }else {
                    imainPresenter.callShowMore();
                }
            }else {
                new requestItems().execute(positionNum);
            }

        }
    }

}
