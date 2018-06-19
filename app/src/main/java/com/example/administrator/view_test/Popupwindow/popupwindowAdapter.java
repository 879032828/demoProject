package com.example.administrator.view_test.Popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.view_test.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class popupwindowAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater mLayoutInflater;

    public popupwindowAdapter(Context context, List<String> mData) {
        this.mData = mData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.list_item_textview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mData.get(position));

        return convertView;
    }

    public final class ViewHolder{
        public TextView textView;
    }
}
