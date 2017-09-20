package me.aaron.top250.model;

import android.graphics.Paint;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import me.aaron.top250.contract.InfoContarct;
import me.aaron.top250.model.bean.InfoBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aaron on 17-9-19.
 */

public class InfoModel implements InfoContarct.IInfoModel {

    private final String subjectUrl = "http://api.douban.com/v2/movie/subject/";
    private String id;
    private InfoBean infoBean;

    private InfoContarct.IInfoPresenter iInfoPresenter;

    public InfoModel(InfoContarct.IInfoPresenter infoPresenter){
        this.iInfoPresenter = checkNotNull(infoPresenter);
    }



    @Override
    public void getSubject(String id) {
        this.id = id;
        new requestSubject().execute(id);

    }

    @Override
    public void setSubject() {
        iInfoPresenter.returnSubject(infoBean);
    }

    class requestSubject extends AsyncTask<String , Void ,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                Request request = new Request.Builder().url(subjectUrl + strings[0]).build();
                OkHttpClient client = new OkHttpClient.Builder().build();
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
                infoBean = new Gson().fromJson(s,new TypeToken<InfoBean>(){}.getType());
                setSubject();
            }else {
                new requestSubject().execute(id);
            }
        }
    }



}
