package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.LoginTestQuest;
import com.zgk.viewpagerf.vohttp.PhotoQuest;
import com.zgk.viewpagerf.vohttp.ReviewQuest;
import com.zgk.viewpagerf.vohttp.ShowReviewQuest;

public class ReviewActivity extends Activity {
    private TextView Title,FoodTitle,Place,Price;
    private ImageView Food_IMG;
    private EditText Review_ET;
    private Button Review_BUTTON;
    private RecyclerView Review_RV;
    private SwipeRefreshLayout mSwipeLayout;//下拉刷新
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Title=findViewById(R.id.foods_TV);
        FoodTitle=findViewById(R.id.food_title_review);
        Place=findViewById(R.id.food_place_review);
        Price=findViewById(R.id.food_price_review);
        Review_RV=findViewById(R.id.review_rv);
        SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        final String nickname=sharedPreferences.getString("RealName","");
        Food_IMG=findViewById(R.id.food_image);
        Review_ET=findViewById(R.id.review_ET);
        Review_BUTTON=findViewById(R.id.review_button);
        mSwipeLayout =findViewById(R.id.swipe_review);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            //下拉刷新
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mSwipeLayout.setRefreshing(false);
                        ShowReviewQuest shareQuest=new ShowReviewQuest();
                        shareQuest.NewsRequest(getApplicationContext(),Review_RV,Title.getText().toString());
                        //添加需要刷新的内容，比如重新执行一遍布局文件。
                    }
                }, 500); // 0.5秒后发送消息，停止刷新
            }
        });
        Title.setText(getIntent().getStringExtra("mTitle"));
        FoodTitle.setText(getIntent().getStringExtra("mTitle"));
        Price.setText("餐厅："+getIntent().getStringExtra("mPrice"));
        Place.setText("售价："+getIntent().getStringExtra("mPlace"));
        Glide.with(this).load(getIntent().getStringExtra("mHTML")).centerCrop().override(80, 80).into(Food_IMG);
        Review_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewQuest loginTestQuest=new ReviewQuest();
                loginTestQuest.ReviewRequest(Title.getText().toString(),nickname,Review_ET.getText().toString(),getApplicationContext());
                Review_ET.setText("");

            }
        });
        ShowReviewQuest shareQuest=new ShowReviewQuest();
        shareQuest.NewsRequest(getApplicationContext(),Review_RV,Title.getText().toString());
    }
}
