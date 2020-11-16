package com.zgk.viewpagerf.MyAdapters.MyAdapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgk.viewpagerf.R;

import java.util.List;

public class CourseAdapter extends BaseQuickAdapter<CourseModel, BaseViewHolder> {

        public CourseAdapter(@LayoutRes int layoutResId, @Nullable List<CourseModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CourseModel item) {
            //可链式调用赋值
            helper.setText(R.id.Course_title, item.getkcmc())
                    .setText(R.id.Course_place, "餐厅："+item.getkcsj())
            .setText(R.id.Course_price,"售价："+item.getjsmc());

            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }
