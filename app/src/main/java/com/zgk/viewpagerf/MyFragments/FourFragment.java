package com.zgk.viewpagerf.MyFragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.NewsQuest;
import com.zgk.viewpagerf.vohttp.PhotoQuest;

public class FourFragment extends Fragment{
    private RecyclerView sceneryRV;
    private SwipeRefreshLayout mSwipeLayout;//下拉刷新
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_four, null);
        sceneryRV=view.findViewById(R.id.scenery_rv);
        mSwipeLayout =view.findViewById(R.id.swipe_scenery);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mSwipeLayout.setRefreshing(false);
                        PhotoQuest newsQuest=new PhotoQuest();
                        newsQuest.NewsRequest(view.getContext(),sceneryRV);
                        //添加需要刷新的内容，比如重新执行一遍布局文件。
                    }
                }, 500); // 0.5秒后发送消息，停止刷新
            }
        });
        PhotoQuest newsQuest=new PhotoQuest();
        newsQuest.NewsRequest(view.getContext(),sceneryRV);
        return view;
    }

}