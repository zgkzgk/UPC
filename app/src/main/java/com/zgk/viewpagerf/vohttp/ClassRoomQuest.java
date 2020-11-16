package com.zgk.viewpagerf.vohttp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zgk.viewpagerf.MyActivitys.ClassRoom2Activity;
import com.zgk.viewpagerf.MyActivitys.Score2Activity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.ClassRoomModel;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.CourseModel;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.ScoreModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class ClassRoomQuest {
    public String json;
    public List<ScoreModel> scoreList;
    //创建okHttpClient对象
    public void getDataAsync(String Token,String method ,String sjd,String jxl,String xh,final Context context) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://jwxt.upc.edu.cn/app.do?method="+method+"&idleTime="+sjd+"&xqid="+xh+"&jxlid="+jxl)
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
                   // Log.d("教学楼","response.body().string()=="+ response.body().string());
                    json = response.body().string();
                    String xhjsh="",jxl="";
                    Log.d("教学楼","response.body().string()=="+ json);
                    if (json.length()==4) xhjsh="无空闲教室";
                    else {
                        Type userListType = new TypeToken<ArrayList<ClassRoomModel>>() {
                        }.getType();
                        Gson gson = new Gson();
                        List<ClassRoomModel> userList = gson.fromJson(json, userListType);
                        jxl=userList.get(0).getjxl();
                        List<ClassRoomModel.JSModel> XHList = userList.get(0).getjsList();
                        for (ClassRoomModel.JSModel XHList1 : XHList) {
                            xhjsh = xhjsh + " " + XHList1.getJsh().substring(XHList1.getJsh().length()-3);
                        }
                    }
//                    for (ClassRoomModel.JSModel XHList1: WLList){
//                        wljsh=wljsh+" "+XHList1.getJsh();
//                    }
//                    for (ClassRoomModel.JSModel XHList1: NJList){
//                        njjsh=njjsh+" "+XHList1.getJsh();
//                    }
                    startUserActivity(context, ClassRoom2Activity.class,jxl,xhjsh);
                    Log.d("教室名称","response.body().string()=="+xhjsh);
                }
            }
        });
    }
    public static void startUserActivity(Context context , Class cls,String jxl,String jsh){
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra("jxl",jxl);
        intent.putExtra("jsh",jsh);
        context.startActivity(intent);
    }
}
