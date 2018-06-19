package com.example.administrator.view_test.TabLayout.RYRY;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.view_test.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<BaseHolder> {

    private Context mContext;

    //条目样式
    private final int HORIZONTAL_VIEW = 1000;

    private final int VERTICAL_VIEW = 1001;

    private final int GRID_VIEW = 1002;

    private ArrayList<Integer> resourceList = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<Integer> resourceList) {
        this.mContext = context;
        this.resourceList = resourceList;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case GRID_VIEW:
                return new GridViewHolder(R.layout.item_recyclerview, parent, viewType);
            case VERTICAL_VIEW:
                return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (holder instanceof GridViewHolder) {
            holder.refreshData(resourceList, position);
        }

    }

    @Override
    /**
     * 当Item出现时调用此方法
     */
    public void onViewAttachedToWindow(BaseHolder holder) {
        Log.i("mengyuan", "onViewAttachedToWindow:" + holder.getClass().toString());
    }

    @Override
    /**
     * 当Item被回收时调用此方法
     */
    public void onViewDetachedFromWindow(BaseHolder holder) {

    }


    @Override
    public int getItemCount() {
        return resourceList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return GRID_VIEW;
    }
}