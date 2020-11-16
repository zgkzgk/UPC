package com.zgk.viewpagerf.MyActivitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zgk.viewpagerf.MyAdapters.MyAdapter.ScoreModel;
import com.zgk.viewpagerf.MyApplication;
import com.zgk.viewpagerf.R;
import com.zgk.viewpagerf.vohttp.ScoreQuest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScoreActivity extends Activity {
    private Spinner spinner;
    private String term="";
    private Button score;
    private String username="",password="",method="getCjcx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        spinner=findViewById(R.id.medel);
        score=findViewById(R.id.chafen_button);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equals("全部"))  term="";
                else term =spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
        username=sharedPreferences.getString("username","");
        password=sharedPreferences.getString("password","");
        final MyApplication myApplication=(MyApplication) this.getApplication();
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScoreQuest testQuest=new ScoreQuest();
                testQuest.getDataAsync(myApplication.getToken(),method,username,term,getApplicationContext());
            }
        });
    }
}