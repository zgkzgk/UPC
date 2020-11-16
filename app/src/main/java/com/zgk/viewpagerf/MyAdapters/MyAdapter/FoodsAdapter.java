package com.zgk.viewpagerf.MyAdapters.MyAdapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgk.viewpagerf.R;

import java.util.List;

public class FoodsAdapter extends BaseQuickAdapter<FoodsModel, BaseViewHolder> {

        public FoodsAdapter(@LayoutRes int layoutResId, @Nullable List<FoodsModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FoodsModel item) {
            //可链式调用赋值
            helper.setText(R.id.food_title, item.getTitle())
                    .setText(R.id.food_place, "餐厅："+item.getPlace())
            .setText(R.id.food_price,"售价："+item.getPrice()+"元");
           // Glide.with(mContext).load(item.getHtml()).crossFade().override(80, 80) .into((ImageView) helper.getView(R.id.food_iv));;
            Glide.with(mContext).load(item.getHtml()).centerCrop().override(80, 80) .into((ImageView) helper.getView(R.id.food_iv));;
            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }
