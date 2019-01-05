package com.example.hhhhentai.ulife;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hhhhentai.DbHelp.DbHelp;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;
import main_fragment.fragment.Bitmap_handle;

public class newsActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelp_NEWS help_news;
    private Database_News database_news;
    private DbHelp help_user;
    private SQLiteDatabase database_user;
    private ImageView news_back;
    private Bitmap_handle bitmap_handle;
    private TextView news_title;
    private TextView user;
    private ImageView user_pic;
    private ImageView news_pic;
    private TextView news_time;
    private TextView content;
    private Integer mScreenHeight;
    private Integer mScreenWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        loadData();

    }

    private void loadData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("news_title");
        String time  = intent.getStringExtra("news_time");
        String news_content;
        String news_publisher;
        String img_path;
        String news_class;
        //todo 检索数据库，找到新闻 记录
        Cursor cursor = database_news.query_newsinfo(null,"NewsTitle_text = ? and NewsTime_text = ?",new String[]{title,time},null,null,null);
        if(cursor.moveToFirst()) {
            //cursor 默认下标是-1
            news_content = cursor.getString(cursor.getColumnIndex("NewsContent_text"));
            content.setText(news_content);
            news_title.setText(cursor.getString(cursor.getColumnIndex("NewsTitle_text")));
            news_publisher =cursor.getString(cursor.getColumnIndex("SendusrPhone_text"));
            news_class=cursor.getString(cursor.getColumnIndex("NewsClass_text"));
            int news_hot = cursor.getInt(cursor.getColumnIndex("NewsHot_int"));
            database_news.update_news_count(title, time, news_hot);

            //加载图片
            img_path = bitmap_handle.readImage(cursor);
            Bitmap imgbitmap = bitmap_handle.pictureTobitmap(img_path);
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            mScreenHeight = dm.heightPixels;//屏幕高度
            mScreenWeight = dm.widthPixels;  //屏幕宽度
            news_pic.setMaxWidth(mScreenWeight);
            news_pic.setMinimumWidth(mScreenWeight);
            news_pic.setMaxHeight(mScreenWeight);

            news_pic.setImageBitmap(imgbitmap);
            news_pic.setAdjustViewBounds(true);
            String user_num = cursor.getString(cursor.getColumnIndex("SendusrPhone_text"));

            Cursor cursor1 = database_user.query("user", null, "account = ?", new String[]{user_num}, null, null, null);
            String t = cursor.getString(cursor.getColumnIndex("NewsTime_text"));

            String month = t.substring(4, 6);
            String day = t.substring(6, 8);
            String hour = t.substring(8, 10);
            String minute = t.substring(10, 12);
            news_time.setText(month + "/" + day + " " + hour + ":" + minute);
            Log.i("jjj", "onCreate: " + hour + ":" + minute);
            if (cursor1.moveToFirst()) {
                user.setText(cursor1.getString(cursor1.getColumnIndex("name")));
            } else {
                Log.i("jjj", "onCreate: " + "else");
            }

            //记录历史
            database_news.insert_historyinfo(user_num, news_publisher,title, news_content,news_class,img_path,news_hot+1,time);
        }
        }

    private void initView() {
        user = findViewById(R.id.news_user);
        user_pic=findViewById(R.id.news_user_pic);
        news_time =findViewById(R.id.news_time);
        news_title=findViewById(R.id.news_title);
        content =findViewById(R.id.news_content);
        news_back=findViewById(R.id.news_back);
        news_pic = findViewById(R.id.news_pic);
        bitmap_handle = new Bitmap_handle();
        news_back.setOnClickListener(this);
        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        help_user= new DbHelp(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);
        database_user=help_user.getWritableDatabase();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.news_back:
                finish();
                break;
        }

    }
}
