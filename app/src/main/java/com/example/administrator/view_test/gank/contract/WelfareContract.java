package com.example.administrator.view_test.gank.contract;

import com.example.administrator.view_test.Base.BaseModel;
import com.example.administrator.view_test.Base.BasePresenter;
import com.example.administrator.view_test.Base.BaseView;
import com.example.administrator.view_test.gank.bean.PhotoGirl;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public interface WelfareContract {
    interface Model extends BaseModel{

    }

    interface View extends BaseView{
        void showPhoto(List<PhotoGirl> list);
        void refreshPhoto(List<PhotoGirl> list);
        void loadingMorePhoto(List<PhotoGirl> list);
    }

    abstract static class Presenter extends BasePresenter<View, Model>{
        public abstract void requestPhotoData(int size, int page);
        public abstract void refreshPhotoData(int size, int page);
        public abstract void loadingMorePhotoData(int size, int page);
    }
}
