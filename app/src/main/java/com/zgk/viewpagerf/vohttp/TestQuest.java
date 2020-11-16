package com.zgk.viewpagerf.vohttp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zgk.viewpagerf.MyActivitys.CourseActivity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.CourseModel;

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

public class TestQuest {
    public String json;
    public List<CourseModel> userList;
    //创建okHttpClient对象
    public void getDataAsync(String Token, String username, final String zc, final Context context) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://jwxt.upc.edu.cn/app.do?method=getKbcxAzc&xh="+username+"&xnxqid=2018-2019-1&zc="+zc)
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
                    Log.d("kwwl", "response.body().string()==" + json);
                        Type userListType = new TypeToken<ArrayList<CourseModel>>() {
                        }.getType();
                        Gson gson = new Gson();
                    if(json.length()==8)
                            json="[{\"jsxm\":\"\",\"jsmc\":\"本周无课\",\"jssj\":\"09:50\",\"kssj\":\"08:00\",\"kkzc\":\"1-17\",\"kcsj\":\"10102\",\"kcmc\":\"\",\"sjbz\":\"0\"}]";
                        List<CourseModel> userList ;
                        userList= gson.fromJson(json, userListType);
                        startUserActivity(context, CourseActivity.class, userList,zc);
                        Log.d("kwwl", "response.body().string()==" + json);
                }
            }
        });
    }
    public static void startUserActivity(Context context , Class cls,List<CourseModel> json,String ZC){
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
       intent.putExtra("userlist",(Serializable)json);
       intent.putExtra("ZC",ZC);
      //  Bundle bundle = new Bundle();
       // bundle.putSerializable("userlist",(Serializable)json);
        context.startActivity(intent);
    }
}
