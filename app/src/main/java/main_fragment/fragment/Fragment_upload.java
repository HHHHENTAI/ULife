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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hhhhentai.ulife.PictureActivity;
import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.path;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

//TODO 赵效慷and江守鑫---
public class Fragment_upload extends Fragment implements View.OnClickListener {
    EditText et_title;
    String title;
    EditText et_content;
    String content;
    ImageView iv_picture;
    ImageView iv_cancle;
    Button btn_send;
    String [] get=new String[5];
    Context context;
    private TextView myTextView;
    private Spinner mySpinner;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private DbHelp_NEWS dbHelp_news;
    private Database_News database_news;
    private Bitmap_handle bitmap_handle;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        //数据库操作初始化
        dbHelp_news = new DbHelp_NEWS(context);
        database_news = new Database_News(dbHelp_news);
        //图片操作
        bitmap_handle = new Bitmap_handle();

        dataList = new ArrayList<Map<String, Object>>();
        getData();
        myTextView = view.findViewById(R.id.tv_title);
        mySpinner = (Spinner) view.findViewById(R.id.spinner);
        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter=new SimpleAdapter(context, dataList, R.layout.item, new String[]{"image","text"}, new int[]{R.id.iv_img,R.id.tv});

        // 第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(R.layout.item);
        // 第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //初始化
    private void init(View view) {
        et_title = view.findViewById(R.id.et_title);
        et_content = view.findViewById(R.id.et_content);
        iv_picture = view.findViewById(R.id.iv_picture);
        iv_picture.setOnClickListener(this);
        iv_cancle = view.findViewById(R.id.iv_cancle);
        iv_cancle.setOnClickListener(this);
        btn_send=view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    private void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", null);
        map.put("text", "1");
        Map<String, Object> map1 = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_launcher_background);
        map.put("text", "北京");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.a6b);
        map2.put("text", "上海");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.ic_launcher_background);
        map3.put("text", "广州");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("image", R.drawable.ic_launcher_background);
        map4.put("text", "深圳");
        dataList.add(map);
        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
        dataList.add(map4);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload,null);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //TODO 点击图片
            case R.id.iv_picture:
                Intent intent1 =new Intent(context,PictureActivity.class);
                startActivityForResult(intent1,100);
                break;
            //点击发送
            case R.id.btn_send:
                title=et_title.getText().toString();
                content=et_content.getText().toString();
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
                for(int i=0;i<5;i++){
                    if(!get[i].equals("")){
                        String  pathtest = get[i];
                        byte[] imgtest={};
                        imgtest = bitmap_handle.bitmabToBytes(pathtest);
                        database_news.insert_newsinfo(i,"12345678901",title,content,"生活",imgtest,0,NewsTime_text);
                    }
                }

                //database_news.insert_newsinfo(0,"12345678901",title,content,"生活",imgtest,0,NewsTime_text);
                break;

            case R.id.btn_cancle:
                et_title.setText("");
                et_content.setText("");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10){
            path  p = (path) data.getSerializableExtra("iv");
            get[0]=p.getone();
            get[1]=p.gettwo();
            get[2]=p.getthree();
            get[3]=p.getfore();
            get[4]=p.getfive();

        }
    }
}
