package com.example.administrator.view_test.listview.pullToRefreshListViewGai.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.view_test.R;
import com.example.administrator.view_test.gank.bean.PhotoGirl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.MyHolder> {

    private Context mContext;
    private List<String> mList = new ArrayList<String>();
    private LayoutInflater mLayoutInflater;

    public RecycAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RecycAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public List<String> getDatas(){
        return mList;
    }

    public void setDatas(List<String> data){
        mList.clear();
        addData(data);
    }

    public void addData(List<String> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.photoitem, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        if (mOnItemClickListener != null){
            holder.photoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView photoImg;

        public MyHolder(View itemView) {
            super(itemView);
            photoImg = (ImageView) itemView.findViewById(R.id.photo_item);
        }
    }

    public OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    };

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
