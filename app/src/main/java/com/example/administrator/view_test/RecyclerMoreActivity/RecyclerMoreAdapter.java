package com.example.administrator.view_test.RecyclerMoreActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.view_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class RecyclerMoreAdapter extends RecyclerView.Adapter<RecyclerMoreAdapter.BaseHolder> {

    private static final int one = 1;

    private static final int two = 2;

    private static final int three = 3;

    private static final int four = 4;

    private List<RecyclerViewMoreActivity.MyBean> mData = new ArrayList<>();

    private Context mContext;

    private final LayoutInflater mLayoutInflater;

    public RecyclerMoreAdapter(Context context, List<RecyclerViewMoreActivity.MyBean> mData) {
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void addData(List<RecyclerViewMoreActivity.MyBean> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case one:
                return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_recycler_more_1, parent, false));
            case two:
                return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_recycler_more_2, parent, false));
            case three:
                return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_recycler_more_3, parent, false));
            case four:
                return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_recycler_more_4, parent, false));
        }
        return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_recycler_more_1, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("getItemViewType", mData.get(position).getContent());
        Log.e("getItemViewType", mData.get(position).getType() + "");

        return mData.get(position).getType();
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 头部布局1
     */
    class MyViewHolder1 extends BaseHolder {

        private TextView textView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    /**
     * 头部布局2
     */
    class MyViewHolder2 extends BaseHolder {

        private TextView textView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    /**
     * 底部布局3
     */
    class MyViewHolder3 extends BaseHolder {

        private TextView textView;

        public MyViewHolder3(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    /**
     * 中间布局4
     */
    class MyViewHolder4 extends BaseHolder {

        private TextView textView;

        public MyViewHolder4(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
