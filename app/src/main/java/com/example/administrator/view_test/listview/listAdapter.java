package com.example.administrator.view_test.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.view_test.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class listAdapter extends BaseAdapter {

    private Context mContext;

    private List<String> mData;

    private LayoutInflater mInflater;

    private ViewHolder viewHolder;

    public listAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = null;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_left, null);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(mData.get(position));

        return convertView;
    }

    private class ViewHolder{
        public TextView text;
    }
}
