package com.example.administrator.view_test.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.view_test.R;

/**
 * Created by keer on 2016/12/7.
 */

public class ShareWhiteDialog extends BaseDialog implements View.OnClickListener {

    private Context context;
    private Builder mBuilder;

    public ShareWhiteDialog(Context context, Builder builder) {
        super(context, R.style.dialog_share);
        this.context = context;
        this.mBuilder = builder;
        View view = LayoutInflater.from(context).inflate(setLayoutResId(), null);
        setContentView(view);
        setParams();
        initView(view);
    }

    protected int setLayoutResId() {
        return R.layout.dialog_share;
    }

    protected void initView(View view) {
        view.findViewById(R.id.share_qq_view).setOnClickListener(this);
        view.findViewById(R.id.share_sina_view).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_view).setOnClickListener(this);
        view.findViewById(R.id.share_qqzone_view).setOnClickListener(this);
        view.findViewById(R.id.share_wechat_moments_view).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        view.findViewById(R.id.iv_long_picture).setOnClickListener(this);

        if (mBuilder.showCopyView) {
            view.findViewById(R.id.share_copy_link_view).setOnClickListener(this);
        } else {
            view.findViewById(R.id.share_copy_link_view).setVisibility(View.GONE);
        }

        if (mBuilder.showCreatePosterView) {
            view.findViewById(R.id.share_create_poster_view).setOnClickListener(this);
            view.findViewById(R.id.share_create_poster_view).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.share_create_poster_view).setVisibility(View.GONE);
        }

        if (mBuilder.showDownloadView) {
            view.findViewById(R.id.share_save_local_view).setOnClickListener(this);
            view.findViewById(R.id.share_save_local_view).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.share_save_local_view).setVisibility(View.GONE);
        }
        if (mBuilder.showReportView) {
            view.findViewById(R.id.share_report_view).setOnClickListener(this);
        } else {
            view.findViewById(R.id.share_report_view).setVisibility(View.GONE);
        }

    }

    protected void setParams() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        ShareType type = null;
        int i = view.getId();
        if (i == R.id.share_qq_view) {
            Log.d(TAG, "qq");
            type = ShareType.TYPE_QQ;

        } else if (i == R.id.share_qqzone_view) {
            Log.d(TAG, "qq空间");
            type = ShareType.TYPE_QQZONG;

        } else if (i == R.id.share_wechat_view) {
            Log.d(TAG, "微信");
            type = ShareType.TYPE_WECHAT;

        } else if (i == R.id.share_wechat_moments_view) {
            Log.d(TAG, "微信朋友圈");
            type = ShareType.TYPE_WECHAT_MOMENT;

        } else if (i == R.id.share_sina_view) {
            Log.d(TAG, "新浪");
            type = ShareType.TYPE_SINA;

        } else if (i == R.id.share_copy_link_view) {
            Log.d(TAG, "复制链接");
            type = ShareType.TYPE_COPY;

        } else if (i == R.id.share_report_view) {
            type = ShareType.TYPE_REPORT;

        } else if (i == R.id.share_save_local_view) {
            type = ShareType.TYPE_DOWNLOAD;

        } else if (i == R.id.share_create_poster_view) {
            type = ShareType.TYPE_CREATEPOSTER;

        } else if (i == R.id.iv_long_picture) {
            type = ShareType.TYPE_LONG_PICTURE;

        } else if (i == R.id.cancel) {
            dismiss();
            return;
        }
        if (mBuilder.shareItemClickListener != null) {
            mBuilder.shareItemClickListener.onShareItemClick(this, type);
        }
    }

    public static class Builder {
        private Activity context;
        private boolean showCopyView = true;
        private boolean showReportView = true;
        private boolean showDownloadView = false;
        private boolean showCreatePosterView = false;
        private OnShareItemClickListener shareItemClickListener;

        /**
         * 默认显示
         *
         * @param show
         * @return
         */
        public Builder showCopyView(boolean show) {
            this.showCopyView = show;
            return this;
        }

        /**
         * 默认显示
         *
         * @param show
         * @return
         */
        public Builder showReportView(boolean show) {
            this.showReportView = show;
            return this;
        }

        /**
         * 默认显示
         *
         * @param show
         * @return
         */
        public Builder showDownloadView(boolean show) {
            this.showDownloadView = show;
            return this;
        }

        /**
         * 默认显示
         *
         * @param show
         * @return
         */
        public Builder showCreatePosterView(boolean show) {
            this.showCreatePosterView = show;
            return this;
        }

        public Builder setShareItemClickListener(OnShareItemClickListener shareItemClickListener) {
            this.shareItemClickListener = shareItemClickListener;
            return this;
        }

        public Builder(Activity context) {
            this.context = context;
        }

        public ShareWhiteDialog create() {
            return new ShareWhiteDialog(context, this);
        }
    }
}
