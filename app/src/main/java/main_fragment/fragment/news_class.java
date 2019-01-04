package main_fragment.fragment;

import com.example.hhhhentai.ulife.R;

public class news_class {

    public news_class(String news_title,Integer news_browse_count,String news_classify,String news_time,Integer news_img)
    {
            this.news_browse_count = news_browse_count;
            this.news_classify = news_classify;
            this.news_img = news_img;
            this.news_time =news_time;
            this.news_title = news_title;
    }

    public Integer getNews_browse_count() {
        return news_browse_count;
    }

    public void setNews_browse_count(Integer news_browse_count) {
        this.news_browse_count = news_browse_count;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    private  String news_title;

    public String getNews_classify() {
        return news_classify;
    }

    public void setNews_classify(String news_classify) {
        this.news_classify = news_classify;
    }

    private  String news_classify;
    private  Integer news_browse_count;

    public Integer getNews_img() {
        return news_img;
    }

    public void setNews_img(Integer news_img) {
        this.news_img = news_img;
    }

    private  Integer news_img;

    public String getNews_time() {
        return news_time;
    }

    public void setNews_time(String news_time) {
        this.news_time = news_time;
    }

    private  String news_time;







}
