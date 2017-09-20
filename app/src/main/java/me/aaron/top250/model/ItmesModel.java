package me.aaron.top250.model;

import android.os.AsyncTask;

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

    private final String requestUrlPre = "http://api.douban.com/v2/movie/top250?start=";
    private final String requestUtlSuf = "&count=25";
    private MainContract.IMainPresenter imainPresenter;
    private static int positionNum = 0;

    private ItemsBean items;

    public ItmesModel(MainContract.IMainPresenter iMainPresenter){
        this.imainPresenter = iMainPresenter;
    }


    @Override
    public void startRefresh() {
        getItems(0);
    }

    @Override
    public void getItems(int startNumber) {
        new requestItems().execute(startNumber);
    }


    @Override
    public void setStartItems() {
        imainPresenter.returnStartItems(items);
    }

    @Override
    public void setMoreItems(){
        imainPresenter.returnMoreItems(items);
    }


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
                if (MainActivity.isRefresh()){
                    setStartItems();
                    MainActivity.setIsRefresh(false);
                }else {
                    setMoreItems();
                }
            }else {
                new requestItems().execute(positionNum);
            }

        }
    }

}
