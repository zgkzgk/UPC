package com.zgk.viewpagerf.MyAdapters.MyAdapter;

public class ShareModel {
    //分享标题
    private String title;
    //分享内容
    private String content;
    private String nickname;
    //get标题
    public String getTitle() {
        return title;
    }
    //set标题
    public void setTitle(String newsTitle) {
        this.title = newsTitle;
    }
    //get内容
    public String getContent() {
        return content;
    }
    //set内容
    public void setContent(String newscontent) {
        this.content = newscontent;
    }
    public String getNickname() {
        return nickname;
    }
    //set内容
    public void setNickname(String nickname1) {
        this.nickname = nickname1;
    }
}
