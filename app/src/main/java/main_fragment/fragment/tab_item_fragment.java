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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.hhhhentai.ulife.R;

import com.example.hhhhentai.ulife.newsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tab_item_fragment extends Fragment {

    private ListView listView;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    private   List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();//列表数据源

    public tab_item_fragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.show_list,null);
        return view;
    }

    /*  在onCreateView 后触发的事件  */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo 页面显示
        //初始化
        init(view);
        //为listView设置Adapter
        choose_classify();
        //监听事件
        setOnclick();
    }

    private void setOnclick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                      Intent intent= new Intent(context,newsActivity.class);
                      intent.putExtra("news_title","");//新闻作者
                      intent.putExtra("news_time","");//新闻时间

                      startActivity(intent);

            }
        });
    }

    private void choose_classify() {
        switch (getArguments().getInt("position"))
        {
            case 0:
                data_to_adapter("推荐");
                Log.i("test","推荐");

                break;
            case 1:
                 data_to_adapter("学习");
                break;
            case 2:
                 data_to_adapter("游戏");
                break;
            case 3:
                 data_to_adapter("生活");
                break;

            default:break;
        }
    }


    /*    初始化   */
    private void init(View view) {

        listView= view.findViewById(R.id.list_show);


    }


    /*     适配器设置   */
    private void data_to_adapter(String classify) {
        //1.todo 定义数据源
        //todo 查询数据库  获取id等信息
        //Cursor cursor   =  db.rawQuery("select * from newsInfo where classify = ? ",new String[]{classify});
        //whlie(cursor.movetoNext()){}
        Log.i("test","chinasofti");

        //todo 添加到数据源
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("news_title","11");
        map.put("news_classify","22");
        map.put("news_browse_count","33");
        map.put("news_img",R.drawable.ic_launcher_background);
        map.put("news_time","44");
        data.add(map);

        //todo 配置数据源
        String from[] = {"news_title","news_classify","news_browse_count","news_img","news_time"};
        int to[] = {R.id.list_title,R.id.list_classify,R.id.list_browse_num,R.id.list_img,R.id.list_time};
        SimpleAdapter adapter = new SimpleAdapter(context,data,R.layout.list_item,from,to);
        listView.setAdapter(adapter);
    }


    /* Bundle 传参数 */
    public static tab_item_fragment newInstance(int position,String user_num)
    {
        Log.i("jjj", "newInstance: "+user_num);
        Bundle bundle =new Bundle();
        bundle.putInt("position",position);
        bundle.putString("user_num",user_num);
        tab_item_fragment item = new tab_item_fragment();
        item.setArguments(bundle);
        return item;
    }

}
