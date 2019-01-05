package com.example.hhhhentai.ulife;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;
import main_fragment.fragment.Bitmap_handle;
import main_fragment.fragment.news_Adapter;
import main_fragment.fragment.news_class;

public class publishActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_publish_return;
    ListView lv_publish;
    private  String title;
    private String time;
    private DbHelp_NEWS help_news;
    private Database_News database_news;
    private    String user_num;
    private List<news_class> mdatas;
    private news_Adapter newsAdapter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("jjj", "onResume: ");

    }

    private Bitmap_handle bitmap_handle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        initView();
        Intent intent = getIntent();
         user_num = intent.getStringExtra("user_num");
        load_data();
        lv_publish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(publishActivity.this,newsActivity.class);
                intent.putExtra("news_title",title);//新闻标题
                intent.putExtra("news_time",time);//新闻时间
                startActivity(intent);
            }
        });
        lv_publish.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(publishActivity.this);
              //  dialog.setIcon(R.drawable.ic_launcher_background);
                dialog.setTitle("提示");
                dialog.setMessage("是否删除记录？");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo
                        database_news.delete_newsinfo(title,time);
                        load_data();
                        Log.i("jjj", "click: ");
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                //todo 显示窗口
                dialog.create().show();
                return true;
            }
        });
    }

    private void load_data() {
        mdatas = new ArrayList<news_class>();
        Cursor cursor = null;
        cursor = database_news.query_newsinfo(null,"SendusrPhone_text = ?",new String[]{user_num},null,null,"SendId_int desc");
        while(cursor.moveToNext())
        {
            title = cursor.getString(cursor.getColumnIndex("NewsTitle_text"));
            String class_fy =cursor.getString(cursor.getColumnIndex("NewsClass_text"));
            Integer tokyohot =cursor.getInt(cursor.getColumnIndex("NewsHot_int"));
            //测试图片
            String imgData = null;
            imgData = bitmap_handle.readImage(cursor);
            time =cursor.getString(cursor.getColumnIndex("NewsTime_text"));
            news_class news = new news_class(title, tokyohot, class_fy, time, imgData);
            mdatas.add(news);
        }
        //todo 添加到数据源
        //todo 配置数据源
        newsAdapter = new news_Adapter(publishActivity.this,mdatas,lv_publish);
        lv_publish.setAdapter(newsAdapter);
    }

    private void initView() {
          lv_publish =findViewById(R.id.lv_publish);
          iv_publish_return=findViewById(R.id.iv_publish_return);
          iv_publish_return.setOnClickListener(this);
        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);

        bitmap_handle = new Bitmap_handle();
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
