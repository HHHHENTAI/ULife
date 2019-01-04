package com.example.hhhhentai.ulife;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.util.Calendar;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;
import main_fragment.fragment.Fragment_life_tools;
import main_fragment.fragment.Fragment_show;
import main_fragment.fragment.Fragment_upload;
import main_fragment.fragment.Fragment_user;

//TODO 赵效慷and江守鑫---
public class main_part extends AppCompatActivity implements View.OnClickListener {

    private DbHelp_NEWS help_news;
    private Database_News database_news;


    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction ft;
    private ImageView btn_show;
    private ImageView btn_upload;
    private ImageView btn_life_tools;
    private ImageView btn_user;
    private String user_phone_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_part);
        init();

        //接收登录页面传来的用户登录信息 —— 手机号
        Intent phone_intent = getIntent();
        user_phone_num = phone_intent.getStringExtra("phonenum");
        addFragment();
    }

    //默认添加第一个页面
    private void addFragment() {
        //设置第一个页面下方的第一个控件高亮
        btn_show.setImageResource(R.drawable.news_icon_2);
        manager = getSupportFragmentManager();
        //开始事务
        ft = manager.beginTransaction();
        //ft.add("要显示的位置","要显示的内容")
        Fragment fragment_show = new Fragment_show().newInstance(user_phone_num);
        ft.add(R.id.content, fragment_show);
        ft.commit();
    }

    //初始化
    private void init() {
        //找下方四个控件的id
        btn_show = (ImageView) findViewById(R.id.btn_show);
        btn_upload = (ImageView) findViewById(R.id.btn_upload);
        btn_life_tools = (ImageView) findViewById(R.id.btn_life_tools);
        btn_user = (ImageView) findViewById(R.id.btn_user);
        //设置下方四个控件的监听
        btn_show.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
        btn_life_tools.setOnClickListener(this);
        btn_user.setOnClickListener(this);
        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);
    }

    //图片转为二进制数据
    public byte[] bitmabToBytes(String path) {
        //将图片转化为位图
        FileInputStream fis = null;
        try {
            //例子
            //fis = new FileInputStream("/storage/emulated/0/data/test.jpg");
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fis == null) {
            Log.i("错误", "照片路径错误");
            return new byte[0];
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        int size = bitmap.getWidth() * bitmap.getHeight() * 4;
        //创建一个字节数组输出流,流的大小为size
        ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
        try {
            //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            //将字节数组输出流转化为字节数组byte[]
            byte[] imagedata = baos.toByteArray();
            return imagedata;
        } catch (Exception e) {
        } finally {
            try {
                bitmap.recycle();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    //读照片
    public byte[] readImage(Cursor cursor) {
        byte[] imgData = null;
        //将Blob数据转化为字节数组
        imgData = cursor.getBlob(cursor.getColumnIndex("NewsImage_blob"));
        return imgData;
    }

    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        //开始事务
        ft = manager.beginTransaction();
        Fragment fragment_show = new Fragment_show().newInstance(user_phone_num);
        Fragment fragment_upload = new Fragment_upload();
        Fragment fragment_life_tools = new Fragment_life_tools();
        Fragment fragment_user = new Fragment_user().newInstance(user_phone_num);
        switch (view.getId()) {
            case R.id.btn_show:
                ft.replace(R.id.content, fragment_show);
                btn_show.setImageResource(R.drawable.news_icon_2);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.drawable.user_icon1);
                //TODO 消息具体活动界面--赵效慷  江守鑫

                //TODO TEST_START:赵效慷：测试插入数据
                //使用 ContentValues 来对要添加的数据进行组装
                ContentValues values_insert = new ContentValues();
                // 开始组装第一条数据
                int NewsId_int = 0;
                String SendusrPhone_text = "13645356724";
                String NewsTitle_text = "震惊！武汉大学生在如皋做这种事！";
                String NewsContent_text = "武汉理工大学！！！";
                String NewsClass_text = "生活";
                //Blob NewsImage_blob = ;
                int NewsHot_int = 7;
                //时间戳
                Calendar calendar = Calendar.getInstance();
                String ymd = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH) + "";
                String hms = calendar.get(Calendar.HOUR_OF_DAY) * 10000 + calendar.get(Calendar.MINUTE) * 100 + calendar.get(Calendar.SECOND) + "";
                String NewsTime_text = "";
                if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
                    NewsTime_text = ymd + "0" + hms;
                } else {
                    NewsTime_text = ymd + hms;
                }
                Log.i("timestamp", NewsTime_text);
                //存入照片
                String path = "/storage/emulated/0/data/test.jpg";
                //路径例子：path = "/storage/emulated/0/data/test.jpg";
                byte[] NewsImage_blob = bitmabToBytes(path);
                //NewsImage_blob = test;
               // database_news.insert_newsinfo(NewsId_int, SendusrPhone_text, NewsTitle_text, NewsContent_text, NewsClass_text, NewsImage_blob, NewsHot_int, NewsTime_text);
                //TODO TEST_END:赵效慷：测试插入数据结束


                //TODO TEST_START:赵效慷：测试删除数据
                //database_news.delete_newsinfo("0");
                //Log.i("delete", "删除了数据");
                //TODO TEST_END:赵效慷：测试删除数据结束

                //TODO TEST_START:赵效慷：测试修改数据

                //database_news.update_newsinfo(0, 23, SendusrPhone_text, NewsTitle_text, NewsContent_text, NewsClass_text, NewsImage_blob, NewsHot_int, NewsTime_text);
                //Log.i("update", "修改了数据");
                //TODO TEST_END:赵效慷：测试修改数据结束

                //TODO TEST_START:赵效慷：测试查询数据
                Cursor cursor = database_news.query_newsinfo(null, null, null, null, null, null);
                int i = 0;
                byte[] imgData = null;
                while (cursor.moveToNext()) {
                    i++;
                    String test_phonenum = cursor.getString(cursor.getColumnIndex("SendusrPhone_text"));
                    Log.i("qurry:phonenum", test_phonenum);
                    //将Blob数据转化为字节数组
                    imgData = readImage(cursor);
                    if (imgData != null) {
                        //将字节数组转化为位图
                        Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);

                        //TODO 注意：iv_test.setImageBitmap(imagebitmap);即可

                        Log.i("imgbyte", imgData.toString());
                    } else {
                        Log.i("imgbyte", "不成功");
                    }
                }
                Log.i("i", i + "");
                //TODO TEST_END:赵效慷：测试查询数据结束

                break;

            case R.id.btn_upload:
                ft.replace(R.id.content, fragment_upload);
                btn_show.setImageResource(R.drawable.news_icon_1);
                btn_upload.setImageResource(R.mipmap.ic_launcher);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.drawable.user_icon1);
                //TODO 发布具体活动界面--余劲龙

                break;
            case R.id.btn_life_tools:
                ft.replace(R.id.content, fragment_life_tools);
                btn_show.setImageResource(R.drawable.news_icon_1);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.mipmap.ic_launcher);
                btn_user.setImageResource(R.drawable.user_icon1);
                //TODO 工具具体活动界面--盛光明

                break;
            case R.id.btn_user:
                ft.replace(R.id.content, fragment_user);
                btn_show.setImageResource(R.drawable.news_icon_1);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.drawable.user_icon2);
                //TODO 我的具体活动界面--张松

                break;
        }
        ft.commit();
    }
}
