package com.zgk.viewpagerf.MyAdapters.MyAdapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgk.viewpagerf.R;

import java.util.List;

public class ScoreAdapter extends BaseQuickAdapter<ScoreModel, BaseViewHolder> {

        public ScoreAdapter(@LayoutRes int layoutResId, @Nullable List<ScoreModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ScoreModel item) {
            //可链式调用赋值
            helper.setText(R.id.Course_title, item.getkcmc())
                    .setText(R.id.Course_place, "成绩："+item.getzcj())
            .setText(R.id.Course_price,"学分："+item.getxf());

            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }
