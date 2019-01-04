package com.example.hhhhentai.JsonGet;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.alibaba.fastjson.JSON;
import com.example.hhhhentai.ulife.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetExpressageInfo extends AsyncTask<String,Void,String> {
    String com;
    String no;

    private String key = "02ef5afc818eb8717ace280c1e39b6c5";


    private ListView Lv_expressagelist;
    private Context context;

    public GetExpressageInfo(ListView lv_expressagelist,Context context) {
        Lv_expressagelist = lv_expressagelist;
        this.context=context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            //读取远程字符串
            InputStream input = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuffer buffer = new StringBuffer(); //所有行
            String line = null; //一行
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String Content = buffer.toString();
            Log.i("Content", Content);
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
            return Content;

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        //数据解析
        parseResult(result);

    }

    private void parseResult(String result) {
        // TODO Auto-generated method stub
        //自带api
        try {
            JSONObject obj1 = new JSONObject(result);
            //获取返回状况
            String reason = obj1.getString("reason");
            Log.i("返回状况：", reason);

            //物流信息
            String Result = obj1.getString("result");
            Log.i("物流信息：", Result);

            if (reason.equals("成功的返回")) {
                //提取物流信息list
                JSONObject obj2 = new JSONObject(Result);
                String list=obj2.getString("list");
                Log.i("list",list);


                List<JsonExpressageInfo> TDarray = JSON.parseArray(list, JsonExpressageInfo.class);
                //加载到ListView
                List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();

                for (int i = 0; i < TDarray.size(); i++) {
                    //数据
                    String datetime = TDarray.get(i).getDatetime();
                    String remark=TDarray.get(i).getRemark();
                    HashMap<String,Object> map=new HashMap<String,Object>();
                    map.put("Time",datetime);
                    map.put("Describe",remark);
                    data.add(map);
                }

                //创建适配器
                String []from={"Time","Describe"};
                int []to={R.id.Tv_expressageshowtime,R.id.Tv_expressageshowdec};
                SimpleAdapter adapter=new SimpleAdapter(context, data, R.layout.expressage_item_list, from, to);
                Log.i("test", data+"");
                //设置适配器
                Lv_expressagelist.setAdapter(adapter);

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
