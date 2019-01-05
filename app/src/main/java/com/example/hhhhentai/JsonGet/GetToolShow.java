package com.example.hhhhentai.JsonGet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetToolShow extends AsyncTask<String,Void,String>{

        private ImageView Im_ToolshowPicture;
        private TextView Tv_Toolshowsentence;

        public GetToolShow(ImageView im_ToolshowPicture, TextView tv_Toolshowsentence) {
            Im_ToolshowPicture = im_ToolshowPicture;
            Tv_Toolshowsentence = tv_Toolshowsentence;
        }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL(strings[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();

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
        //接受结果
       // Log.i("ToolGetshow",result);
        //数据解析
        parseResult(result);

    }
    private void parseResult(String result) {
        // TODO Auto-generated method stub
        //自带api
        try {
            JSONObject obj=new JSONObject(result);
            //获取图2
            String picture=obj.getString("picture2");
            Log.i("大图2的地址：",picture);
            x.image().bind(Im_ToolshowPicture, picture);
            //获取中文
            String Ccontent=obj.getString("note");
            Log.i("中文的内容：",Ccontent);
            Tv_Toolshowsentence.setText(Ccontent);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
