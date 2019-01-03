package com.example.hhhhentai.JsonGet;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetWeatherData extends AsyncTask<String,Void,String>{

    private String key="15555c8daea3e997875dd4af4a2a49eb";
    private int city_id;
    private String weather_date;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String result = "";
            String Body="";
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //传递短信API参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            //设置属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStreamWriter dos=new OutputStreamWriter(conn.getOutputStream(),"UTF-8");


            Body="key="+key;
            Log.i("Body",Body);
            dos.write(Body);
            dos.flush();

            //读取返回数据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null)
            {
                if (firstLine)
                {
                    firstLine = false;
                } else
                {
                    result = result + System.lineSeparator();
                }
                result += line;
                Log.i("Result1",result);
            }
            Log.i("Result",result);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
    }

}



