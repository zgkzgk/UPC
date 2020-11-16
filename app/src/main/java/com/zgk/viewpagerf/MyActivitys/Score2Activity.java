package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zgk.viewpagerf.MyAdapters.MyAdapter.ScoreAdapter;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.ScoreModel;
import com.zgk.viewpagerf.R;

import java.util.List;

public class Score2Activity extends Activity {
    private List<ScoreModel> ScoreList;
    private RecyclerView ScoreRV;
    private ScoreAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score2);
        ScoreRV=findViewById(R.id.score_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ScoreRV.setLayoutManager(layoutManager);
        Intent intent=getIntent();
        ScoreList= (List<ScoreModel>) getIntent().getSerializableExtra("ScoreList");
        adapter = new ScoreAdapter(R.layout.courses_item,ScoreList);
        ScoreRV.setAdapter(adapter);
    }
}
