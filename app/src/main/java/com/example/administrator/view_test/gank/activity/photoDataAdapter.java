package com.example.administrator.view_test.gank.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.gank.bean.PhotoGirl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class photoDataAdapter extends RecyclerView.Adapter<photoDataAdapter.MyHolder> {

    private Context mContext;
    private List<PhotoGirl> mList = new ArrayList<PhotoGirl>();
    private LayoutInflater mLayoutInflater;

    public photoDataAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public photoDataAdapter(Context context, List<PhotoGirl> list) {
        mContext = context;
        mList = list;
    }

    public List<PhotoGirl> getDatas(){
        return mList;
    }

    public void setDatas(List<PhotoGirl> data){
        mList.clear();
        addData(data);
    }

    public void addData(List<PhotoGirl> data){
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
        Glide.with(mContext)
                .load(mList.get(position).getUrl())
                .placeholder(R.mipmap.imgemsg)
                .centerCrop()
                .error(R.mipmap.circle_image_fail_round)
                .into(holder.photoImg);


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
