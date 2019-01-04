package com.example.hhhhentai.JsonGet;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GetExpressageData extends AsyncTask<String, Void, String> {

    String companyname;
    String expressagenum;
    String com;
    String no;

    ListView Lv_expressagelist;

    Context context;
    private String key = "02ef5afc818eb8717ace280c1e39b6c5";

    public GetExpressageData(String companyname, String expressagenum,  ListView Lv_expressagelist,Context context) {
        this.companyname = companyname;
        this.expressagenum = expressagenum;
        this.Lv_expressagelist=Lv_expressagelist;
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
            //获取公司状况
            String reason = obj1.getString("reason");
            Log.i("返回状况：", reason);

            String company = obj1.getString("result");
            Log.i("公司集合：", company);
            if (reason.equals("查询支持的快递公司成功")) {
                List<JsonExpressage> Jsonarray = JSON.parseArray(company, JsonExpressage.class);
                Log.i("Jsonarray", Jsonarray.toString());

                for (int i = 0; i < Jsonarray.size(); i++) {
                    //获取公司名
                    com = Jsonarray.get(i).getCom();
                    if (com.equals(companyname)) {
                        no = Jsonarray.get(i).getNo();
                        Log.i("NO", no);
                        break;
                    }
                }
                if (no.equals("")) {
                    //不支持快递公司
                } else {
                    //查询物流信息
                    String Body = "http://v.juhe.cn/exp/index?key=" + key + "&com=" + no + "&no=" + expressagenum;
                    new GetExpressageInfo(Lv_expressagelist,context).execute(Body);
                }


            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
