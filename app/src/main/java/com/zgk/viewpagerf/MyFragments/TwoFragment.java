package com.zgk.viewpagerf.MyFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgk.viewpagerf.MyActivitys.ShareActivity;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.ShareQuest;

public class TwoFragment extends Fragment {
    private RecyclerView share_RV;
    private SwipeRefreshLayout mSwipeLayout;//下拉刷新
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_two, null);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        share_RV=view.findViewById(R.id.share_rv);
        //下拉刷新配置
        mSwipeLayout =view.findViewById(R.id.swipe_ly);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mSwipeLayout.setRefreshing(false);
                        ShareQuest shareQuest=new ShareQuest();
                        shareQuest.NewsRequest(view.getContext(),share_RV);
                        //添加需要刷新的内容，比如重新执行一遍布局文件。
                    }
            }, 500); // 0.5秒后发送消息，停止刷新
        }
        });
        ShareQuest shareQuest=new ShareQuest();
        shareQuest.NewsRequest(view.getContext(),share_RV);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), ShareActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}