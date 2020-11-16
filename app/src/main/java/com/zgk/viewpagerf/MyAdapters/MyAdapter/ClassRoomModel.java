package com.zgk.viewpagerf.MyAdapters.MyAdapter;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

public class ClassRoomModel {
    //新闻标题
    private String jxl;
    //新闻内容
    private List<JSModel> jsList;
    //get标题
    public String getjxl() {
        return jxl;
    }
    //set标题
    public void setjxl(String newsjxl) {
        this.jxl = newsjxl;
    }

    public List<JSModel>  getjsList() {
        return jsList;
    }

    public void setjsList(List<JSModel>  newsjsList) {
        this.jsList = newsjsList;
    }
    public class JSModel{
        private String jsh;
        private String jzwmc;
        public String getJsh() {
            return jsh;
        }
        //set标题
        public void setjxl(String jsh) {
            this.jsh = jsh;
        }
    }
}
