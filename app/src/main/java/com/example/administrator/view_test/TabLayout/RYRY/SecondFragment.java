package com.example.administrator.view_test.TabLayout.RYRY;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.view_test.R;
import com.example.administrator.view_test.TabLayout.LazyLoadBaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends LazyLoadBaseFragment {

    private RecyclerView recylcerview;

    private String type = "";

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void lazyload() {
        //只有当前fragment可见，且当前fragment已初始化时
        //才进行数据的加载
        if (isVisible == true && isPrepared == true) {
            Log.e(TAG, "正在进行数据加载..." + type);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Toast.makeText(getActivity(), "type -- " + type, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        isPrepared = true;
        basicParamInit();
        initData();
        initRecyclerView(view);
        return view;
    }


    private int screenWidth;

    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);

        screenWidth = metric.widthPixels;

    }

    ArrayList<Integer> resourceList = new ArrayList<>();

    private void initData() {

        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);
        resourceList.add(R.mipmap.ic_launcher);

    }

    private void initRecyclerView(View view) {
        recylcerview = (RecyclerView) view.findViewById(R.id.recylcerview);

        recylcerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recylcerview.setAdapter(new RecyclerViewAdapter(getActivity(), resourceList));
    }

    /**
     * GridView形状的RecyclerView
     */
    public class GridViewHolder extends BaseHolder<List<Integer>> {

        private RecyclerView item_recyclerview;

        private final int ONE_LINE_SHOW_NUMBER = 3;

        private List<Integer> data;

        public GridViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            item_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview);
        }

        @Override
        public void refreshData(List<Integer> data, int position) {
            super.refreshData(data, position);
            this.data = data;
            //每行显示3个，水平显示
            item_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));

            ViewGroup.LayoutParams layoutParams = item_recyclerview.getLayoutParams();
            //计算行数
            int lineNumber = data.size() % ONE_LINE_SHOW_NUMBER == 0 ? data.size() / ONE_LINE_SHOW_NUMBER : data.size() / ONE_LINE_SHOW_NUMBER + 1;
            //计算高度=行数＊每行的高度 ＋(行数－1)＊10dp的margin ＋ 10dp（为了居中）
            //因为每行显示3个条目，为了保持正方形，那么高度应该是也是宽度/3
            //高度的计算需要自己好好理解，否则会产生嵌套recyclerView可以滑动的现象
            layoutParams.height = lineNumber * (screenWidth / 3) + (lineNumber - 1) * dip2px(10) + dip2px(10);

            item_recyclerview.setLayoutParams(layoutParams);

            item_recyclerview.setBackgroundResource(R.color.colorPrimary);

            item_recyclerview.setAdapter(new GridAdapter());
        }


        private class GridAdapter extends RecyclerView.Adapter<BaseHolder> {

            @Override
            public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
            }

            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                holder.refreshData(data.get(position), position);
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        }


    }

    /**
     * 将dp转化为px
     */
    private int dip2px(float dip) {
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return (int) (v + 0.5f);
    }

    /**
     * 通用子条目hodler
     */
    private class ItemViewHolder extends BaseHolder<Integer> {

        private ImageView imageview_item;

        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
            ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;
            imageview_item.setLayoutParams(layoutParams);
        }

        @Override
        public void refreshData(Integer data, final int position) {
            imageview_item.setBackgroundResource(data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    public class RecyclerViewAdapter extends RecyclerView.Adapter<BaseHolder> {
//        //条目样式
//        private final int HORIZONTAL_VIEW = 1000;
//        private final int VERTICAL_VIEW = 1001;
//        private final int GRID_VIEW = 1002;
//
//        @Override
//        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            switch (viewType) {
//                case GRID_VIEW:
//                    return new SecondFragment.GridViewHolder(R.layout.item_recyclerview, parent, viewType);
//                case VERTICAL_VIEW:
//                    return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
//            }
//            return null;
//        }
//
//        @Override
//        public void onBindViewHolder(BaseHolder holder, int position) {
//            if (holder instanceof GridViewHolder) {
//                holder.refreshData(resourceList, position);
//            }
//
//        }
//
//        @Override
//        /**
//         * 当Item出现时调用此方法
//         */
//        public void onViewAttachedToWindow(BaseHolder holder) {
//            Log.i("mengyuan", "onViewAttachedToWindow:" + holder.getClass().toString());
//        }
//
//        @Override
//        /**
//         * 当Item被回收时调用此方法
//         */
//        public void onViewDetachedFromWindow(BaseHolder holder) {
//
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return 2 + resourceList.size();
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//
//            return GRID_VIEW;
//        }
//    }
}
