package com.zgk.viewpagerf.MyAdapters.MyAdapter;

import java.io.Serializable;

public class ScoreModel implements Serializable {
    //课程类型
    private String kclbmc;
    //总成绩
    private String zcj;
    //课程名称
    private String kcmc;
    //课程类型2
    private String kcxzmc;
    //学分
    private String xf;
    //get标题
    public String getkclbmc() {
        return kclbmc;
    }
    //set标题
    public void setkclbmc(String kclbmc) {
        this.kclbmc = kclbmc;
    }

    public String getzcj() {
        return zcj;
    }

    public String getkcxzmc(){return kcxzmc;}

    public void setkcxzmc(String kcxzmc){this.kcxzmc=kcxzmc;};
    public String getkcmc(){return kcmc;}

    public void setPrice(String kcmc){this.kcmc=kcmc;};

    public void setzcj(String newscontent) {
        this.zcj = newscontent;
    }
    public void setxf(String time){this.xf=time;};

    public String getxf() {
        return xf;
    }
}
