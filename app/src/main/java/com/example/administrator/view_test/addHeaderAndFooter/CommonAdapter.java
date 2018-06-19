package com.example.administrator.view_test.addHeaderAndFooter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public abstract  class CommonAdapter<T> extends RecyclerView.Adapter<HongYangViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public HongYangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HongYangViewHolder viewHolder = HongYangViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HongYangViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(HongYangViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return 0;
    }
}
