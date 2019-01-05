package main_fragment.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.JsonGet.GetToolShow;
import com.example.hhhhentai.UseTool.ExpressageActivity;
import com.example.hhhhentai.UseTool.SchoolDataActivity;
import com.example.hhhhentai.UseTool.WeatherActivity;
import com.example.hhhhentai.ulife.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

//TODO 赵效慷and江守鑫---  盛光明加入了工具栏界面，并设置了天气的监听
public class Fragment_life_tools extends Fragment {
    private GridView Gv_Toolgridview1;
    private GridView Gv_Toolgridview2;

    private ImageView Im_ToolshowPicture;
    private TextView Tv_Toolshowsentence;

    private Context context;

    private LinearLayout Li_ToolShow,LL_Tooltitle1,LL_Tooltitle2;
    private TextView Tv_Tooltitle1,Tv_Tooltitle2;


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
//        private LinearLayout Li_ToolShow,LL_Tooltitle1,LL_Tooltitle2;
//        private TextView Tv_Tooltitle1,Tv_Tooltitle2;
        Gv_Toolgridview1 = (GridView) view.findViewById(R.id.Gv_Toolgridview1);
        Gv_Toolgridview2 = (GridView) view.findViewById(R.id.Gv_Toolgridview2);

        Im_ToolshowPicture =(ImageView)view.findViewById(R.id.Im_ToolshowPicture);
        Tv_Toolshowsentence=(TextView)view.findViewById(R.id.Tv_Toolshowsentence);
        Li_ToolShow = (LinearLayout) view.findViewById(R.id.Li_ToolShow);
        LL_Tooltitle1 = (LinearLayout) view.findViewById(R.id.LL_Tooltitle1);
        LL_Tooltitle2 = (LinearLayout) view.findViewById(R.id.LL_Tooltitle2);
        Tv_Tooltitle1 = (TextView) view.findViewById(R.id.Tv_Tooltitle1);
        Tv_Tooltitle2 = (TextView) view.findViewById(R.id.Tv_Tooltitle2);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.35f + 0.5f));
        params.setMargins(0,0,0,0);
        Li_ToolShow.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params1.setMargins((int) (Constant.displayWidth * 0.01f + 0.5f),0,0,0);
        LL_Tooltitle1.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params2.setMargins((int) (Constant.displayWidth * 0.01f + 0.5f),0,0,0);
        LL_Tooltitle2.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (Constant.displayHeight * 0.25f + 0.5f));
        params3.setMargins(0,0,0,0);
        Im_ToolshowPicture.setLayoutParams(params3);


        Tv_Toolshowsentence.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        Tv_Tooltitle1.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        Tv_Tooltitle2.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

        //获取网络图片

        new GetToolShow(Im_ToolshowPicture,Tv_Toolshowsentence).execute("http://open.iciba.com/dsapi");

        //填充数据
        List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();

        HashMap<String, Object> map11 = new HashMap<String, Object>();
        map11.put("icon",R.mipmap.tool_weather);
        map11.put("content", "天气");
        data1.add(map11);

        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("icon", R.mipmap.tool_expressage);
        map12.put("content", "快递");
        data1.add(map12);

        HashMap<String, Object> map13 = new HashMap<String, Object>();
        map13.put("icon", R.mipmap.tool_lable);
        map13.put("content", "便签");
        data1.add(map13);

        HashMap<String, Object> map14 = new HashMap<String, Object>();
        map14.put("icon", R.mipmap.tool_map);
        map14.put("content", "地图");
        data1.add(map14);

        HashMap<String, Object> map15 = new HashMap<String, Object>();
        map15.put("icon", R.mipmap.tool_bill);
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
                        Intent expressageintent=new Intent(context,ExpressageActivity.class);
                        startActivity(expressageintent);
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
        map21.put("icon", R.mipmap.tool_course);
        map21.put("content", "课程表");
        data2.add(map21);

        HashMap<String, Object> map22 = new HashMap<String, Object>();
        map22.put("icon", R.mipmap.tool_grade);
        map22.put("content", "成绩");
        data2.add(map22);

        HashMap<String, Object> map23 = new HashMap<String, Object>();
        map23.put("icon", R.mipmap.tool_borrowbook);
        map23.put("content", "借书");
        data2.add(map23);

        HashMap<String, Object> map24 = new HashMap<String, Object>();
        map24.put("icon", R.mipmap.tool_schooldate);
        map24.put("content", "校历");
        data2.add(map24);

        HashMap<String, Object> map25 = new HashMap<String, Object>();
        map25.put("icon", R.mipmap.tool_application);
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

                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        Intent schooldataintent=new Intent(context,SchoolDataActivity.class);
                        startActivity(schooldataintent);
                        break;
                    case 4:
                        break;
                    default:
                        break;


                }

            }
        });

    }
}

