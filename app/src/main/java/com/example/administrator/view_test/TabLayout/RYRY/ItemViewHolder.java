package com.example.administrator.view_test.TabLayout.RYRY;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.view_test.R;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class ItemViewHolder extends BaseHolder<Integer> {

    private Context mContext;

    private ImageView imageview_item;

    private int screenWidth;

    public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
        super(viewId, parent, viewType);
        imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
        ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
        layoutParams.width = layoutParams.height = screenWidth / 3;
        imageview_item.setLayoutParams(layoutParams);

        mContext = parent.getContext();
    }

    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);

        screenWidth = metric.widthPixels;

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
