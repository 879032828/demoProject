package com.example.administrator.view_test.gank.contract;

import com.example.administrator.view_test.Base.BaseModel;
import com.example.administrator.view_test.Base.BasePresenter;
import com.example.administrator.view_test.Base.BaseView;
import com.example.administrator.view_test.gank.bean.PhotoGirl;

import java.util.List;

/**
 * 协议类
 * <p>
 * 将P层、V层和M层的接口都写在这个类中，再按需实现，这样就不会有多个接口类
 * <p>
 * <p>
 * Created by Administrator on 2017/4/10 0010.
 */
public interface WelfareContract {

    /**
     * Model层
     */
    interface Model extends BaseModel {

    }

    /**
     * View层
     */
    interface View extends BaseView {
        void showPhoto(List<PhotoGirl> list);

        void refreshPhoto(List<PhotoGirl> list);

        void loadingMorePhoto(List<PhotoGirl> list);
    }

    /**
     * Presenter层
     */
    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void requestPhotoData(int size, int page);

        public abstract void refreshPhotoData(int size, int page);

        public abstract void loadingMorePhotoData(int size, int page);
    }
}
