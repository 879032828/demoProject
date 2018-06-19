package com.example.administrator.view_test.TabLayout.RYRY;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.view_test.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class GridViewHolder extends BaseHolder<List<Integer>> {

    private Context mContext;

    private RecyclerView item_recyclerview;

    private final int ONE_LINE_SHOW_NUMBER = 4;

    private List<Integer> data;

    private int screenWidth;

    public GridViewHolder(int viewId, ViewGroup parent, int viewType) {
        super(viewId, parent, viewType);
        mContext = parent.getContext();
        basicParamInit();
        item_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview);
    }

    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);

        screenWidth = metric.widthPixels;

    }

    @Override
    public void refreshData(List<Integer> data, int position) {
        super.refreshData(data, position);
        this.data = data;
        //每行显示3个，水平显示
        item_recyclerview.setLayoutManager(new GridLayoutManager(mContext, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));

        ViewGroup.LayoutParams layoutParams = item_recyclerview.getLayoutParams();
        //计算行数
        int lineNumber = data.size() % ONE_LINE_SHOW_NUMBER == 0 ? data.size() / ONE_LINE_SHOW_NUMBER : data.size() / ONE_LINE_SHOW_NUMBER + 1;
        //计算高度=行数＊每行的高度 ＋(行数－1)＊10dp的margin ＋ 10dp（为了居中）
        //因为每行显示3个条目，为了保持正方形，那么高度应该是也是宽度/3
        //高度的计算需要自己好好理解，否则会产生嵌套recyclerView可以滑动的现象
        layoutParams.height = lineNumber * (screenWidth / 4) + (lineNumber - 1) * dip2px(10) + dip2px(10);

        item_recyclerview.setLayoutParams(layoutParams);

        item_recyclerview.setBackgroundResource(R.color.colorPrimary);

        item_recyclerview.setAdapter(new GridViewHolder.GridAdapter());
    }


    private class GridAdapter extends RecyclerView.Adapter<BaseHolder> {

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            //该holder是ItemViewHolder
            holder.refreshData(data.get(position), position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    /**
     * 通用子条目hodler
     * 该hodler是GridView的ViewHolder
     */
    private class ItemViewHolder extends BaseHolder<Integer> {

        private ImageView imageview_item;

        private LinearLayout linearLayout;

        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear);

            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 4;
            linearLayout.setLayoutParams(layoutParams);
        }

        @Override
        public void refreshData(Integer data, final int position) {
            imageview_item.setBackgroundResource(data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 将dp转化为px
     */
    private int dip2px(float dip) {
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, mContext.getResources().getDisplayMetrics());
        return (int) (v + 0.5f);
    }

}
