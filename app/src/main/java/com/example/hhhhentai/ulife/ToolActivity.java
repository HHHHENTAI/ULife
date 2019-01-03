package com.example.hhhhentai.ulife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//TODO 盛光明编写工具栏的主活动(要修订，融合fragment)
public class ToolActivity extends AppCompatActivity {

    private GridView Gv_Toolgridview1;
    private GridView Gv_Toolgridview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        init();
    }

    private void init()
    {
        Gv_Toolgridview1=(GridView)findViewById(R.id.Gv_Toolgridview1);
        Gv_Toolgridview2=(GridView)findViewById(R.id.Gv_Toolgridview2);
        //填充数据
        List<Map<String,Object>> data1=new ArrayList<Map<String,Object>>();

        HashMap<String,Object> map11=new HashMap<String, Object>();
        map11.put("icon",R.mipmap.ic_launcher);
        map11.put("content","天气");
        data1.add(map11);

        HashMap<String,Object> map12=new HashMap<String, Object>();
        map12.put("icon",R.mipmap.ic_launcher);
        map12.put("content","快递");
        data1.add(map12);

        HashMap<String,Object> map13=new HashMap<String, Object>();
        map13.put("icon",R.mipmap.ic_launcher);
        map13.put("content","便签");
        data1.add(map13);

        HashMap<String,Object> map14=new HashMap<String, Object>();
        map14.put("icon",R.mipmap.ic_launcher);
        map14.put("content","地图");
        data1.add(map14);

        HashMap<String,Object> map15=new HashMap<String, Object>();
        map15.put("icon",R.mipmap.ic_launcher);
        map15.put("content","账单");
        data1.add(map15);

        //创建学习工具栏适配器
        String []from1={"icon","content"};
        int []to1={R.id.Im_Toolicon,R.id.Tv_Toolcontent};
        SimpleAdapter adapter1=new SimpleAdapter(ToolActivity.this,data1,R.layout.tool_list_item,from1,to1);

        Gv_Toolgridview1.setAdapter(adapter1);

        Gv_Toolgridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //学习工具
        List<Map<String,Object>> data2=new ArrayList<Map<String,Object>>();

        HashMap<String,Object> map21=new HashMap<String, Object>();
        map21.put("icon",R.mipmap.ic_launcher);
        map21.put("content","课程表");
        data2.add(map21);

        HashMap<String,Object> map22=new HashMap<String, Object>();
        map22.put("icon",R.mipmap.ic_launcher);
        map22.put("content","成绩");
        data2.add(map22);

        HashMap<String,Object> map23=new HashMap<String, Object>();
        map23.put("icon",R.mipmap.ic_launcher);
        map23.put("content","借书");
        data2.add(map23);

        HashMap<String,Object> map24=new HashMap<String, Object>();
        map24.put("icon",R.mipmap.ic_launcher);
        map24.put("content","校历");
        data2.add(map24);

        HashMap<String,Object> map25=new HashMap<String, Object>();
        map25.put("icon",R.mipmap.ic_launcher);
        map25.put("content","应用");
        data2.add(map25);

        //创建学习工具栏适配器
        String []from2={"icon","content"};
        int []to2={R.id.Im_Toolicon,R.id.Tv_Toolcontent};
        SimpleAdapter adapter2=new SimpleAdapter(ToolActivity.this,data2,R.layout.tool_list_item,from2,to2);

        Gv_Toolgridview2.setAdapter(adapter2);

        Gv_Toolgridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
