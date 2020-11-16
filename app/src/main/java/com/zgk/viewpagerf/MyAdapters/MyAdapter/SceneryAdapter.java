package com.zgk.viewpagerf.MyAdapters.MyAdapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgk.viewpagerf.R;

import java.util.List;

public class SceneryAdapter extends BaseQuickAdapter<NewsModel, BaseViewHolder> {

        public SceneryAdapter(@LayoutRes int layoutResId, @Nullable List<NewsModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsModel item) {
            //可链式调用赋值
            helper.setText(R.id.scenery_title, item.getTitle());
            Glide.with(mContext).load(item.getHtml()).crossFade().into((ImageView) helper.getView(R.id.imageView));
            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }