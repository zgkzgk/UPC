package com.zgk.viewpagerf.vohttp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zgk.viewpagerf.MyActivitys.NewsDetailsActivity;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.NewsAdapter;
import com.zgk.viewpagerf.MyAdapters.MyAdapter.NewsModel;
import com.zgk.viewpagerf.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsQuest {
    private NewsAdapter adapter;
    public void NewsRequest(final Context context,final RecyclerView newsRV) {
        //请求地址
        String url = "http://xxxxxxx/WebApplication1/NewsServlet";    //注①
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
                            for (int i = allnews.length()-1; i>=0; i--) {
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
                            adapter = new NewsAdapter(R.layout.news_item,newsInfos);
                            //条目点击事件
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("mHTML",newsInfos.get(position).getHtml());
                                    context.startActivity(intent);
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
}
