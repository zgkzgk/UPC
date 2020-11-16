package com.zgk.viewpagerf.MyAdapters.MyAdapter;

public class FoodsModel {
    //美食标题
    private String title;
    //位置
    private String place;
    //价格
    private int price;
    //图片地址
    private String html;
    //get标题
    public String getTitle() {
        return title;
    }
    //set标题
    public void setTitle(String newsTitle) {
        this.title = newsTitle;
    }

    public String getPlace() {
        return place;
    }

    public String getHtml(){return html;}

    public void setHtml(String newshtml){this.html=newshtml;};
    public int getPrice(){return price;}

    public void setPrice(int newshtml){this.price=newshtml;};

    public void setPlace(String newscontent) {
        this.place = newscontent;
    }
}
