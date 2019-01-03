package com.example.hhhhentai.JsonGet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetWeatherData extends AsyncTask<String,Void,String>{

    private TextView Tv_weatherdate;
    private TextView Tv_weathertemp;
    private TextView Tv_weather;
    private TextView Tv_weatherwind;
    private TextView Tv_weatherdress;

    public GetWeatherData(TextView tv_weatherdate, TextView tv_weathertemp, TextView tv_weather, TextView tv_weatherwind, TextView tv_weatherdress) {
        Tv_weatherdate = tv_weatherdate;
        Tv_weathertemp = tv_weathertemp;
        Tv_weather = tv_weather;
        Tv_weatherwind = tv_weatherwind;
        Tv_weatherdress = tv_weatherdress;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //读取远程字符串
            InputStream input=conn.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
            StringBuffer buffer=new StringBuffer(); //所有行
            String line=null; //一行
            while((line=reader.readLine())!=null)
            {
                buffer.append(line);
            }

            String Content=buffer.toString();
            Log.i("Content",Content);
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
            JSONObject obj1=new JSONObject(result);
            //获取天气状况
            String weatherresult=obj1.getString("result");
            Log.i("天气信息：",weatherresult);

            JSONObject obj2=new JSONObject(weatherresult);
            //获取今天的天气状况
            String today=obj2.getString("today");
            Log.i("今天的天气信息：",today);

            //获取今天的天气详细状况
            JSONObject obj3=new JSONObject(today);

            //日期
            String date=obj3.getString("date_y");
            Log.i("日期：",date);
            Tv_weatherdate.setText(date);

            //温度
            String temp=obj3.getString("temperature");
            Log.i("温度：",temp);
            Tv_weathertemp.setText(temp);

            //天气
            String weather=obj3.getString("weather");
            Log.i("天气：",weather);
            Tv_weather.setText(weather);

            //风向
            String wind=obj3.getString("wind");
            Log.i("风向：",wind);
            Tv_weatherwind.setText(wind);

            //穿衣建议
            String dress=obj3.getString("dressing_advice");
            Log.i("穿衣建议：",dress);
            Tv_weatherdress.setText(dress);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}



