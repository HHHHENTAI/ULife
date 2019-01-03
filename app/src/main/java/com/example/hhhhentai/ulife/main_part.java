package com.example.hhhhentai.ulife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import main_fragment.fragment.Fragment_life_tools;
import main_fragment.fragment.Fragment_show;
import main_fragment.fragment.Fragment_upload;
import main_fragment.fragment.Fragment_user;

//TODO 赵效慷and江守鑫---
public class main_part extends AppCompatActivity implements View.OnClickListener {

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
        //设置下方四个控件的监听
        btn_show = (ImageView) findViewById(R.id.btn_show);
        btn_upload = (ImageView) findViewById(R.id.btn_upload);
        btn_life_tools = (ImageView) findViewById(R.id.btn_life_tools);
        btn_user = (ImageView) findViewById(R.id.btn_user);

        btn_show.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
        btn_life_tools.setOnClickListener(this);
        btn_user.setOnClickListener(this);
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
