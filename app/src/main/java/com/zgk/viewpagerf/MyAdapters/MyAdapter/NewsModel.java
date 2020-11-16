package com.zgk.viewpagerf.MyAdapters.MyAdapter;

public class NewsModel {
    //新闻标题
    private String title;
    //新闻内容
    private String content;
    //新闻网址
    private String html;
    //get标题
    public String getTitle() {
        return title;
    }
    //set标题
    public void setTitle(String newsTitle) {
        this.title = newsTitle;
    }

    public String getContent() {
        return content;
    }

    public String getHtml(){return html;}

    public void setHtml(String newshtml){this.html=newshtml;};

    public void setContent(String newscontent) {
        this.content = newscontent;
    }
}
