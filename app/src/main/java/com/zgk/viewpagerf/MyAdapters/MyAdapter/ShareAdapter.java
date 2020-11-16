package com.zgk.viewpagerf.MyAdapters.MyAdapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgk.viewpagerf.R;

import java.util.List;

public class ShareAdapter extends BaseQuickAdapter<NewsModel, BaseViewHolder> {

        public ShareAdapter(@LayoutRes int layoutResId, @Nullable List<NewsModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsModel item) {
            //可链式调用赋值
            helper.setText(R.id.share_title, item.getTitle())
                    .setText(R.id.share_content, item.getContent());

            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }
