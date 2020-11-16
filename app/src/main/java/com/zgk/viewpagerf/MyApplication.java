package com.zgk.viewpagerf;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zgk.viewpagerf.MyActivitys.MainActivity;
import com.zgk.viewpagerf.MyActivitys.TestActivity;

public class MyApplication extends Application {
    private String Token="";
    private int LoginFlag=0;
    public String getToken() {
        return Token;
    }
    public void setToken(String Token) {
        this.Token = Token;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
