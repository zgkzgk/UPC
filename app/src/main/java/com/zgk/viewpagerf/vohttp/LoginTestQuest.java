package com.zgk.viewpagerf.vohttp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zgk.viewpagerf.MyActivitys.EnquiryActivity;
import com.zgk.viewpagerf.MyActivitys.MainActivity;
import com.zgk.viewpagerf.MyActivitys.TestActivity;
import com.zgk.viewpagerf.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class LoginTestQuest {
    public String Token;
    public String RealName;
    private String LX;
    public void LoginRequest(final String accountNumber, final String password, final Context context, final TestActivity testActivity) {
        //请求地址
        String url = "http://jwxt.upc.edu.cn/app.do?method=authUser&xh="+accountNumber+"&pwd="+password;    //教务系统
        String tag = "Login";    //注②
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
                            JSONObject jsonObject = (JSONObject) new JSONObject(response);  //注③
                            Token = jsonObject.getString("token");  //注④
                            RealName=jsonObject.getString("userrealname");
                            String XYMC=jsonObject.getString("userdwmc");
                            String type=jsonObject.getString("usertype");
                            if(type.equals("2")){
                                LX="学生";
                            }
                            else LX="教师";
                            if (Token.equals("-1")){
                                Toast.makeText(context, "账号或密码错误，请重新输入！",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context, jsonObject.getString("msg"),
                                        Toast.LENGTH_SHORT).show();
                                //sp为新建xml文件的文件名，MODE模式，PRIVATE私有
                                SharedPreferences sharedPreferences=context.getSharedPreferences("sp",MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("username",accountNumber);
                                editor.putString("password",password);
                                editor.putString("RealName",RealName);
                                editor.putString("XYMC",XYMC);
                                editor.putString("TYPE",LX);
                                editor.commit();
                                testActivity.finish();
                                startUserActivity(context, MainActivity.class,Token);
                            }
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
       });
// {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("AccountNumber", accountNumber);  //注⑥
//                params.put("Password", password);
//                return params;
//            }
//        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    public static void startUserActivity(Context context , Class cls,String Token){
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra("token", Token);
        context.startActivity(intent);
}
}
