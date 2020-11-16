package com.zgk.viewpagerf.vohttp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zgk.viewpagerf.MyActivitys.Score2Activity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.ScoreModel;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScoreQuest {
    public String json;
    public List<ScoreModel> scoreList;
    //创建okHttpClient对象
    public void getDataAsync(String Token,String method ,String xh,String term,final Context context) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://jwxt.upc.edu.cn/app.do?method="+method+"&xh="+xh+"&xnxqid="+term)
                .addHeader("token",Token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    Log.d("kwwl","获取数据成功了");
                    Log.d("kwwl","response.code()=="+response.code());
                    json = response.body().string();
                    Type scoreListType = new TypeToken<ArrayList<ScoreModel>>(){}.getType();
                    Gson gson = new Gson();
                    scoreList = gson.fromJson(json, scoreListType);
                    startUserActivity(context, Score2Activity.class,scoreList);
                    Log.d("kwwl","response.body().string()=="+json);
                }
            }
        });
    }
    public static void startUserActivity(Context context , Class cls,List<ScoreModel> json){
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
       intent.putExtra("ScoreList",(Serializable)json);
        context.startActivity(intent);
    }
}
