package me.aaron.top250.model;

import me.aaron.top250.model.bean.InfoBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aaron on 17-10-6.
 */

public interface InfoDataService {
    @GET("{id}")
    Call<InfoBean> getInfoData(@Path("id")String id);
}
