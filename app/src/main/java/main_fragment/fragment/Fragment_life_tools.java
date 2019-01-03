package main_fragment.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.hhhhentai.UseTool.WeatherActivity;
import com.example.hhhhentai.ulife.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO 赵效慷and江守鑫---  盛光明加入了工具栏界面，并设置了天气的监听
public class Fragment_life_tools extends Fragment {
    private GridView Gv_Toolgridview1;
    private GridView Gv_Toolgridview2;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life_tools, null);

        return view;
    }

    private void init(View view) {
        Gv_Toolgridview1 = (GridView) view.findViewById(R.id.Gv_Toolgridview1);
        Gv_Toolgridview2 = (GridView) view.findViewById(R.id.Gv_Toolgridview2);
        //填充数据
        List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();

        HashMap<String, Object> map11 = new HashMap<String, Object>();
        map11.put("icon", R.mipmap.ic_launcher);
        map11.put("content", "天气");
        data1.add(map11);

        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("icon", R.mipmap.ic_launcher);
        map12.put("content", "快递");
        data1.add(map12);

        HashMap<String, Object> map13 = new HashMap<String, Object>();
        map13.put("icon", R.mipmap.ic_launcher);
        map13.put("content", "便签");
        data1.add(map13);

        HashMap<String, Object> map14 = new HashMap<String, Object>();
        map14.put("icon", R.mipmap.ic_launcher);
        map14.put("content", "地图");
        data1.add(map14);

        HashMap<String, Object> map15 = new HashMap<String, Object>();
        map15.put("icon", R.mipmap.ic_launcher);
        map15.put("content", "账单");
        data1.add(map15);

        //创建学习工具栏适配器
        String[] from1 = {"icon", "content"};
        int[] to1 = {R.id.Im_Toolicon, R.id.Tv_Toolcontent};
        SimpleAdapter adapter1 = new SimpleAdapter(context, data1, R.layout.tool_list_item, from1, to1);

        Gv_Toolgridview1.setAdapter(adapter1);

        Gv_Toolgridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ID",position+"");

                switch (position) {
                    case 0:
                        Intent weatherintent=new Intent(context,WeatherActivity.class);
                        startActivity(weatherintent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;


                }

            }
        });

        //学习工具
        List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();

        HashMap<String, Object> map21 = new HashMap<String, Object>();
        map21.put("icon", R.mipmap.ic_launcher);
        map21.put("content", "课程表");
        data2.add(map21);

        HashMap<String, Object> map22 = new HashMap<String, Object>();
        map22.put("icon", R.mipmap.ic_launcher);
        map22.put("content", "成绩");
        data2.add(map22);

        HashMap<String, Object> map23 = new HashMap<String, Object>();
        map23.put("icon", R.mipmap.ic_launcher);
        map23.put("content", "借书");
        data2.add(map23);

        HashMap<String, Object> map24 = new HashMap<String, Object>();
        map24.put("icon", R.mipmap.ic_launcher);
        map24.put("content", "校历");
        data2.add(map24);

        HashMap<String, Object> map25 = new HashMap<String, Object>();
        map25.put("icon", R.mipmap.ic_launcher);
        map25.put("content", "应用");
        data2.add(map25);

        //创建学习工具栏适配器
        String[] from2 = {"icon", "content"};
        int[] to2 = {R.id.Im_Toolicon, R.id.Tv_Toolcontent};
        SimpleAdapter adapter2 = new SimpleAdapter(context, data2, R.layout.tool_list_item, from2, to2);

        Gv_Toolgridview2.setAdapter(adapter2);

        Gv_Toolgridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}

