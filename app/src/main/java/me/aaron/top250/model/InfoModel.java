package me.aaron.top250.model;

import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import me.aaron.top250.MyApplication;
import me.aaron.top250.contract.InfoContarct;
import me.aaron.top250.model.bean.InfoBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        //new requestSubject().execute(id);
        getInfoData(id);

    }

    @Override
    public InfoBean setSubject() {
        return infoBean;
    }

    /*class requestSubject extends AsyncTask<String , Void ,String>{

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
                iInfoPresenter.callShow();
            }else {
                new requestSubject().execute(id);
            }
        }
    }*/

    private void getInfoData(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(subjectUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InfoDataService infoDataService = retrofit.create(InfoDataService.class);
        Call<InfoBean> response = infoDataService.getInfoData(id);
        response.enqueue(new Callback<InfoBean>() {
            @Override
            public void onResponse(Call<InfoBean> call, retrofit2.Response<InfoBean> response) {
                infoBean = response.body();
                iInfoPresenter.callShow();
            }
            @Override
            public void onFailure(Call<InfoBean> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"加载异常",Toast.LENGTH_SHORT).show();
            }
        });
    }

}



