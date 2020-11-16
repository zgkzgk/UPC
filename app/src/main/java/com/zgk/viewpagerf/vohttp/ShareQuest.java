package com.zgk.viewpagerf.vohttp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hb.dialog.dialog.ConnectingDialog;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyImageMsgDialog;
import com.zgk.viewpagerf.MyActivitys.MainActivity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.NewsModel;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.ShareAdapter;
import com.zgk.viewpagerf.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class ShareQuest {
    private ShareAdapter adapter;
    private ConnectingDialog connectingDialog;
    public void NewsRequest(final Context context,final RecyclerView newsRV) {
        //请求地址
        String url = "http://xxxxxxxxx/WebApplication1/ShareServlet";    //注①
        String tag = "News";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            JSONArray allnews = jsonObject.getJSONArray("news");
                            final ArrayList<NewsModel> newsInfos = new ArrayList<>();
                            for (int i = 0; i < allnews.length(); i++) {
                                JSONObject jsonObject1 = allnews.getJSONObject(i);    //注②
                                NewsModel newsModel = new NewsModel();    //注③
                                newsModel.setTitle(jsonObject1.getString("NewsTitle"));
                                newsModel.setContent(jsonObject1.getString("NewsContent"));
                                newsModel.setHtml(jsonObject1.getString("NewsHtml"));
                                newsInfos.add(newsModel);
                            }
                            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            newsRV.setLayoutManager(layoutManager);
                            adapter = new ShareAdapter(R.layout.share_item,newsInfos);
                            //条目点击事件
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                                    MyAlertDialog myAlertDialog = new MyAlertDialog(context).builder()
                                            .setTitle("确认下载吗？")
                                            .setMsg("确认下载"+newsInfos.get(position).getContent()+"吗？")
                                            .setPositiveButton("确认", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    connectingDialog = new ConnectingDialog(context);
                                                    connectingDialog.setMessage("正在下载中");
                                                    connectingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                        @Override
                                                        public void onCancel(DialogInterface dialog) {

                                                        }
                                                    });
                                                    connectingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                                        @Override
                                                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                                            return false;
                                                        }
                                                    });
                                                    connectingDialog.show();
                                                    downloadFile3(newsInfos.get(position).getHtml(),newsInfos.get(position).getContent(),context);
                                                }
                                            }).setNegativeButton("取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            });
                                    myAlertDialog.show();
                                }
                            });
                            newsRV.setAdapter(adapter);
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Toast.makeText(context, "无网络连接",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NewsTitle", "1");  //注⑥
                //params.put("Password", password);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    private void downloadFile3(final String url,final String FileName,final Context context){
        //下载路径，如果路径无效了，可换成你的下载路径
        final long startTime = System.currentTimeMillis();
        Log.i("DOWNLOAD","startTime="+startTime);

        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                e.printStackTrace();
                Log.i("DOWNLOAD","download failed");
            }
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Sink sink = null;
                BufferedSink bufferedSink = null;
                try {
                    String mSDCardPath= Environment.getExternalStorageDirectory().getAbsolutePath();
                    File dest = new File(mSDCardPath,FileName);
                    Log.i("DOWNLOAD",dest.getAbsolutePath());
                    sink = Okio.sink(dest);
                    bufferedSink = Okio.buffer(sink);
                    bufferedSink.writeAll(response.body().source());
                    bufferedSink.close();
                    Log.i("DOWNLOAD","download success");
                    Looper.prepare();
                    connectingDialog.dismiss();
                    Toast.makeText(context, "下载成功，下载路径为"+dest.getAbsolutePath(),
                            Toast.LENGTH_LONG).show();
                    Looper.loop();
                    Log.i("DOWNLOAD","totalTime="+ (System.currentTimeMillis() - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("DOWNLOAD","download failed");
                } finally {
                    if(bufferedSink != null){
                        bufferedSink.close();
                    }
                }
            }
        });
    }
}
