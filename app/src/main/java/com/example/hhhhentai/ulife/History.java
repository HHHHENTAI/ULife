package com.example.hhhhentai.ulife;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;
import main_fragment.fragment.news_Adapter;
import main_fragment.fragment.news_class;

public class History extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_history_reutrn;
    ListView lv_history;
    private DbHelp_NEWS help_news;
    private Database_News database_news;

    private List<news_class> mdatas;
    private news_Adapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        iv_history_reutrn = (ImageView) findViewById(R.id.iv_history_return);
        iv_history_reutrn.setOnClickListener(this);
        lv_history = (ListView)findViewById(R.id.lv_history);

        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);

        mdatas = new ArrayList<news_class>();
        Cursor cursor = database_news.query_newsinfo(null, null, null, null, null, null);
        while(cursor.moveToNext())
        {

            String title = cursor.getString(cursor.getColumnIndex("NewsTitle_text"));
            String class_fy =cursor.getString(cursor.getColumnIndex("NewsClass_text"));
            Integer tokyohot =cursor.getInt(cursor.getColumnIndex("NewsHot_int"));
            Integer imgID =cursor.getInt(cursor.getColumnIndex("NewsImage_int"));
            String time =cursor.getString(cursor.getColumnIndex("NewsTime_text"));
            //news_class news = new  news_class(title,tokyohot,class_fy,time,R.drawable.ic_launcher_background);
            //mdatas.add(news);

        }
        //todo 添加到数据源

        //todo 配置数据源
        newsAdapter = new news_Adapter(History.this,mdatas,lv_history);
        lv_history.setAdapter(newsAdapter);
    }

    @Override
    public void onClick(View view)
    {
        finish();
    }
}
