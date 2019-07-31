package com.example.administrator.view_test.gank.Interface;

import com.example.administrator.view_test.gank.bean.GirlData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public interface requestService {
    //http://gank.io/api/
    @GET("data/福利/{size}/{page}")
    Observable<GirlData> listPhotoGirl(
            @Path("size") int size,
            @Path("page") int page);

}
