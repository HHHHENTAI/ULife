package com.example.hhhhentai.ulife;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import main_fragment.fragment.DbHelp_NEWS;
import main_fragment.fragment.Fragment_life_tools;
import main_fragment.fragment.Fragment_show;
import main_fragment.fragment.Fragment_upload;
import main_fragment.fragment.Fragment_user;

//TODO 赵效慷and江守鑫---
public class main_part extends AppCompatActivity implements View.OnClickListener {

    private DbHelp_NEWS help_news;
    private SQLiteDatabase database_news;
    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction ft;
    private ImageView btn_show;
    private ImageView btn_upload;
    private ImageView btn_life_tools;
    private ImageView btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_part);
        init();
        addFragment();
    }

    //默认添加第一个页面
    private void addFragment() {
        //设置第一个页面下方的第一个控件高亮
        btn_show.setImageResource(R.mipmap.ic_launcher);
        manager = getSupportFragmentManager();
        //开始事务
        ft = manager.beginTransaction();
        //ft.add("要显示的位置","要显示的内容")
        Fragment fragment_show = new Fragment_show();
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
        database_news = help_news.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        //开始事务
        ft = manager.beginTransaction();
        Fragment fragment_show = new Fragment_show();
        Fragment fragment_upload = new Fragment_upload();
        Fragment fragment_life_tools = new Fragment_life_tools();
        Fragment fragment_user = new Fragment_user();
        switch (view.getId()) {
            case R.id.btn_show:
                ft.replace(R.id.content, fragment_show);
                btn_show.setImageResource(R.mipmap.ic_launcher);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.drawable.ic_launcher_background);
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
                int NewsImage_int = 100;
                int NewsHot_int = 7;
                String NewsTime_text = "20180103131200";
                values_insert.put("NewsId_int", NewsId_int);
                values_insert.put("SendusrPhone_text", SendusrPhone_text);
                values_insert.put("NewsTitle_text", NewsTitle_text);
                values_insert.put("NewsContent_text", NewsContent_text);
                values_insert.put("NewsClass_text", NewsClass_text);
                values_insert.put("NewsImage_int", NewsImage_int);
                values_insert.put("NewsHot_int", NewsHot_int);
                values_insert.put("NewsTime_text", NewsTime_text);
                database_news.insert("NewsInfo ", null, values_insert);
                //TODO TEST_END:赵效慷：测试插入数据结束

                //TODO TEST_START:赵效慷：测试删除数据
//                database_news.delete("NewsInfo ","NewsId_int=?",new String[]{"0"});
//                Log.i("delete", "删除了数据");
                //TODO TEST_END:赵效慷：测试删除数据结束

                //TODO TEST_START:赵效慷：测试修改数据
                ContentValues values_update = new ContentValues();
                values_update.put("NewsId_int", 23);
                values_update.put("SendusrPhone_text", SendusrPhone_text);
                values_update.put("NewsTitle_text", NewsTitle_text);
                values_update.put("NewsContent_text", NewsContent_text);
                values_update.put("NewsClass_text", NewsClass_text);
                values_update.put("NewsImage_int", NewsImage_int);
                values_update.put("NewsHot_int", NewsHot_int);
                values_update.put("NewsTime_text", NewsTime_text);
                //第二个参数是修改的字段及修改的值(已经存放到ContentValues中)
                //第三个参数是WHERE语句
                //第四个参数是WHERE语句中占位符的填充值
                //如果第三四个参数为null，那就将每条记录都改掉
                database_news.update("NewsInfo", values_update, "NewsId_int=?", new String[]{"0"});
                Log.i("update", "修改了数据");
                //TODO TEST_END:赵效慷：测试修改数据结束

                //TODO TEST_START:赵效慷：测试查询数据
                Cursor cursor = database_news.query("NewsInfo", null, null, null, null, null, null);
                int i = 0;
                while (cursor.moveToNext()) {
                    i++;
                    String test_phonenum = cursor.getString(cursor.getColumnIndex("SendusrPhone_text"));
                    Log.i("qurry:phonenum", test_phonenum);
                }
                Log.i("i", i + "");
                //TODO TEST_END:赵效慷：测试查询数据结束



                break;
            case R.id.btn_upload:
                ft.replace(R.id.content, fragment_upload);
                btn_show.setImageResource(R.drawable.ic_launcher_background);
                btn_upload.setImageResource(R.mipmap.ic_launcher);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.drawable.ic_launcher_background);
                //TODO 发布具体活动界面--余劲龙

                break;
            case R.id.btn_life_tools:
                ft.replace(R.id.content, fragment_life_tools);
                btn_show.setImageResource(R.drawable.ic_launcher_background);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.mipmap.ic_launcher);
                btn_user.setImageResource(R.drawable.ic_launcher_background);
                //TODO 工具具体活动界面--盛光明

                break;
            case R.id.btn_user:
                ft.replace(R.id.content, fragment_user);
                btn_show.setImageResource(R.drawable.ic_launcher_background);
                btn_upload.setImageResource(R.drawable.ic_launcher_background);
                btn_life_tools.setImageResource(R.drawable.ic_launcher_background);
                btn_user.setImageResource(R.mipmap.ic_launcher);
                //TODO 我的具体活动界面--张松

                break;
        }
        ft.commit();
    }
}
