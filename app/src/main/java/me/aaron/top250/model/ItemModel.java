package me.aaron.top250.model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.aaron.top250.contract.MainContract;
import me.aaron.top250.model.bean.ItemBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aaron on 17-9-18.
 */

public class ItemModel implements MainContract.IMainModel {

    private List<ItemBean> itemBeanList = new ArrayList<ItemBean>();
    private final String requestUrlPre = "http://api.douban.com/v2/movie/top250?start=";
    private final String requestUtlSuf = "&count=25";
    private MainContract.IMainPresenter imainPresenter;
    private int start;

    public ItemModel(MainContract.IMainPresenter iMainPresenter){
        this.imainPresenter = iMainPresenter;
    }


    @Override
    public void startRefresh() {
        itemBeanList.clear();
        getItems(0);
    }

    @Override
    public void getItems(int startNum) {
        new requestItems().execute(startNum);
        this.start = startNum;
    }

    @Override
    public void setItems() {
        imainPresenter.returnItems(itemBeanList);
    }


    class requestItems extends AsyncTask<Integer,Void,String>{

        @Override
        protected String doInBackground(Integer... startNum) {
            try{
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url(requestUrlPre + startNum + requestUtlSuf).build();
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
                try {
                    JSONObject jsonobject = new JSONObject(s);
                    JSONArray jsonArray = jsonobject.getJSONArray("subjects");
                    for (int i = 0 ; i < jsonArray.length() ; i++){
                        String content = jsonArray.getJSONObject(i).toString();
                        ItemBean itembean = new Gson().fromJson(content,ItemBean.class);
                        itemBeanList.add(itembean);
                        Log.d("9999999999", "onPostExecute: " + itembean.getTitle());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setItems();
            }else {
                new requestItems().execute(start);
            }

        }
    }

}
