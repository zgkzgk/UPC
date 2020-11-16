package com.zgk.viewpagerf.MyActivitys;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zgk.viewpagerf.MyAdapters.TabFragmentPagerAdapter;
import com.zgk.viewpagerf.MyApplication;
import com.zgk.viewpagerf.MyFragments.FiveFragment;
import com.zgk.viewpagerf.MyFragments.FourFragment;
import com.zgk.viewpagerf.MyFragments.OneFragment;
import com.zgk.viewpagerf.MyFragments.ThreeFragment;
import com.zgk.viewpagerf.MyFragments.TwoFragment;
import com.zgk.viewpagerf.R;

public class MainActivity extends FragmentActivity implements OnClickListener {
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;
    private String Token;
    //声明五个Tab
    private LinearLayout mTabNews;
    private LinearLayout mTabShares;
    private LinearLayout mTabMine;
    private LinearLayout mTabView;
    private LinearLayout mTabFood;
    //声明五个ImageButton
    private ImageButton mTabNewsImg;
    private ImageButton mTabSharesImg;
    private ImageButton mTabMineImg;
    private ImageButton mTabViewImg;
    private ImageButton mTabFoodImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Token=intent.getStringExtra("token");
        MyApplication myApplication=(MyApplication)this.getApplication();
        myApplication.setToken(Token);
        InitView();
        InitEvent();
    }

    /**
     * 初始化控件
     */
    private void InitEvent(){
        mTabNews.setOnClickListener(this);
        mTabShares.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
        mTabView.setOnClickListener(this);
        mTabFood.setOnClickListener(this);
        myViewPager.addOnPageChangeListener(new MyPagerChangeListener());

//把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
        list.add(new FiveFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面
        mTabNewsImg.setImageResource(R.drawable.ic_index_home_selected);
    }
    private void InitView() {

        myViewPager = findViewById(R.id.myViewPager);
            // 初始化五个LinearLayout
            mTabNews = findViewById(R.id.id_tab_news);
            mTabShares = findViewById(R.id.id_tab_share);
            mTabMine = findViewById(R.id.id_tab_mine);
            mTabView = findViewById(R.id.id_tab_view);
            mTabFood=findViewById(R.id.id_tab_food);
            // 初始化五个按钮
            mTabNewsImg = findViewById(R.id.id_tab_news_img);
            mTabSharesImg = findViewById(R.id.id_tab_share_img);
            mTabMineImg = findViewById(R.id.id_tab_mine_img);
            mTabViewImg = findViewById(R.id.id_tab_view_img);
            mTabFoodImg=findViewById(R.id.id_tab_food_img);

    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_news: myViewPager.setCurrentItem(0);
                break;
            case R.id.id_tab_share: myViewPager.setCurrentItem(1);
                break;
            case R.id.id_tab_mine: myViewPager.setCurrentItem(2);
                break;
            case R.id.id_tab_view: myViewPager.setCurrentItem(3);
                break;
            case R.id.id_tab_food: myViewPager.setCurrentItem(4);
                break;
            default:
                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     *
     */
    public class MyPagerChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    resetImg();
                    mTabNewsImg.setImageResource(R.drawable.ic_index_home_selected);
                    break;
                case 1:
                    resetImg();
                    mTabSharesImg.setImageResource(R.drawable.ic_index_target_selected);
                    break;
                case 2:
                    resetImg();
                    mTabMineImg.setImageResource(R.drawable.ic_index_find_selected);
                    break;
                case 3:
                    resetImg();
                    mTabViewImg.setImageResource(R.drawable.ic_index_mine_selected);
                    break;
                case 4:
                    resetImg();
                    mTabFoodImg.setImageResource(R.drawable.ic_index_find_selected);
                    break;
                    default:
                        break;
            }
        }
    }
    private void resetImg() {
        mTabNewsImg.setImageResource(R.drawable.ic_index_home);
        mTabSharesImg.setImageResource(R.drawable.ic_index_target);
        mTabMineImg.setImageResource(R.drawable.ic_index_find);
        mTabViewImg.setImageResource(R.drawable.ic_index_mine);
        mTabFoodImg.setImageResource(R.drawable.ic_index_find);
    }
}
