package com.zgk.viewpagerf.MyAdapters.MyAdapter;

import android.util.Log;

import com.zgk.viewpagerf.MyActivitys.CourseActivity;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseModel implements Serializable {
    //课程名称
    private String kcmc;
    //上课地点
    private String jsmc;
    //教师姓名
    private String jsxm;
    //上课周次
    private String kkzc;
    //上课时间
    private String kcsj;
    public CourseModel(String kcmc, String jsmc, String jsxm) {
        this.kcmc = kcmc;
        this.jsmc = jsmc;
        this.jsxm = jsxm;
    }
    //get标题
    public String getkcmc() {
        return kcmc;
    }
    //set标题
    public void setkcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getjsmc() {
        return jsmc;
    }

    public String getkkzc(){return kkzc;}

    public void setkkzc(String kkzc){this.kkzc=kkzc;};
    public String getjsxm(){return jsxm;}

    public void setPrice(String jsxm){this.jsxm=jsxm;};

    public void setjsmc(String newscontent) {
        this.jsmc = newscontent;
    }
    public void setkcsj(String time){this.kcsj=time;};

    public String getkcsj() {
        return kcsj;
    }
    /**
     * 周二第1,2节{第11-19周}
     *
     * @return 返回值为这节课从第几节课开始
     */
    public int getMinCourse() {
        if (!getkcsj().equals("712")){
            Log.d("TAG", "getMinCourse: "+Integer.parseInt(getkcsj().substring(1,3)));
            return Integer.parseInt(getkcsj().substring(1,3));}
        else return 11;
    }
    public int getMaxCourse() {
        if (!getkcsj().equals("712"))
        return Integer.parseInt(getkcsj().substring(3));
        else return 12;
    }
    public int getStep() {
        return getMaxCourse() - getMinCourse() + 1;
    }
    /**
     * 获取本节课程从第几周开始
     *
     * 周五第1,2节{第19-19周|单周}
     *
     * @return 开始周
     */
    public int getMinWeek() {
        return Integer.parseInt(getkkzc().substring(0,getkkzc().indexOf("-")));
    }
    //结束周
    public int getMaxWeek() {
        return Integer.parseInt(getkkzc().substring(getkkzc().indexOf("-")));
    }
    public char getDayOfWeek() {
        return getkcsj().charAt(0);
    }
}
