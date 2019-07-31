package com.example.administrator.view_test.gank.presenter;

import android.app.ProgressDialog;

import com.example.administrator.view_test.gank.Interface.requestService;
import com.example.administrator.view_test.gank.activity.WelfareActivity;
import com.example.administrator.view_test.gank.bean.GirlData;
import com.example.administrator.view_test.gank.contract.WelfareContract;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class PhotoDataPresenter extends WelfareContract.Presenter {

    private Retrofit retrofit;
    private requestService service;

    //读超时时间， 毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接超时时间， 毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    public PhotoDataPresenter() {
        init();
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) //使用工厂模式创建Gason的解析器
                .build();
        service = retrofit.create(requestService.class);
    }

    @Override
    public void requestPhotoData(int size, int page) {
        final ProgressDialog pd = new ProgressDialog((WelfareActivity) mView);
        pd.setMessage("正在获取数据...");

        //通过retrofit使用okhttp请求到数据后，返回被观察者observable
        //使用RxJava对返回的数据进行处理
        service.listPhotoGirl(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GirlData girlData) {
                        mView.showPhoto(girlData.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    @Override
    public void refreshPhotoData(int size, int page) {
        service.listPhotoGirl(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GirlData girlData) {
                        mView.refreshPhoto(girlData.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    @Override
    public void loadingMorePhotoData(int size, int page) {
        service.listPhotoGirl(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GirlData girlData) {
                        mView.loadingMorePhoto(girlData.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
