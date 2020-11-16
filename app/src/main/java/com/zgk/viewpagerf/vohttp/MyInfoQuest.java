package com.zgk.viewpagerf.vohttp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zgk.viewpagerf.MyActivitys.CourseActivity;
import com.zgk.viewpagerf.MyActivitys.EnquiryActivity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.CourseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyInfoQuest {
    public String json;
    public List<CourseModel> userList;
    //创建okHttpClient对象
    public void getDataAsync(String Token, final Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String currDate=simpleDateFormat.format(date);
        Log.d("当前时间",currDate);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://jwxt.upc.edu.cn/app.do?method=getCurrentTime&currDate="+currDate)
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
                    JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
                    String ZC=jsonObject.get("zc").getAsString();
                    String XQ=jsonObject.get("xnxqh").getAsString();
                    SharedPreferences sharedPreferences=context.getSharedPreferences("sp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("ZC",ZC);
                    editor.putString("XQ",XQ);
                    editor.commit();
                    //startUserActivity(context, EnquiryActivity.class);
                }
            }
        });
    }
    public static void startUserActivity(Context context , Class cls){
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity(intent);
    }
}
