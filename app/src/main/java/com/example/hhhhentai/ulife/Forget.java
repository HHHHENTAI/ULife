package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.example.hhhhentai.DbHelp.DbHelp;
import com.example.hhhhentai.JsonGet.Sms;
import com.example.hhhhentai.MD5Utils.MD5Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forget extends Activity {

    private EditText et_phone_for, et_SMS_for, et_pwd_for;
    private Button btn_SMS_for, btn_forget;
    private DbHelp help;
    private SQLiteDatabase database;
    private String sms = "";



    final private String accountSid = "8fda0355b69e4681be18df9a074302de";
    final private String token = "5fbb861823844099bb5763111a4eff73";
    final private String path = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        //数据库
        help = new DbHelp(Forget.this);
        database = help.getWritableDatabase();

        for(int i = 0; i < 6;i++){
            sms = sms+(int)(Math.random()*10)+"";
        }
        Log.i("test",sms);


        et_phone_for = (EditText) findViewById(R.id.et_phone_for);
        et_SMS_for = (EditText) findViewById(R.id.et_SMS_for);
        et_pwd_for = (EditText) findViewById(R.id.et_pwd_for);
        btn_SMS_for = (Button) findViewById(R.id.btn_SMS_for);
        btn_forget = (Button) findViewById(R.id.btn_forget);
        btn_SMS_for.setOnClickListener(new OnClickListener() {
            //处理从json转过来的字符串
            Handler mHandler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == 1) {
                        String str = (String) msg.obj;
                        //使用alibaba的方法获取需要的数据
                        Sms s = JSON.parseObject(str, Sms.class);
                        Log.i("ppp",s.toString());
                    }
                };
            };

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final String phonenum = et_phone_for.getText().toString();
                if (phonenum.length() != 11) {
                    Toast.makeText(Forget.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    String judge = "Select * from user where phone = '"
                            + phonenum + "'";
                    Cursor c = database.rawQuery(judge, null);
                    if (c.moveToNext() == true) {
                        Log.i("testtp", judge);
                        //子线程获取验证码
                        new Thread() {
                            public void run() {
                                Calendar ca = Calendar.getInstance();
                                final int mYear = ca.get(Calendar.YEAR);
                                final int mMonth = ca.get(Calendar.MONTH) + 1;
                                final int mDay = ca.get(Calendar.DAY_OF_MONTH);
                                final int mHour = ca.get(Calendar.HOUR_OF_DAY);
                                final int mMinute = ca.get(Calendar.MINUTE);
                                final int mSecond = ca.get(Calendar.SECOND);
                                //时间戳
                                final String timestamp = ""
                                        + (mYear * 10000 + mMonth * 100 + mDay)
                                        + (mHour * 10000 + mMinute * 100 + mSecond)
                                        + "";
                                Log.i("testtp", timestamp);
                                //签名
                                String resultString = MD5Utils.stringToMD5(accountSid + token + timestamp);
                                try {
                                    //进行网络请求
                                    URL url = new URL(path);
                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                    conn.setRequestMethod("POST");
                                    conn.setReadTimeout(5000);
                                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                    String data = "accountSid=" + accountSid
                                            + "&templateid=1173023947"
                                            + "&param="+sms+",5"
                                            + "&to=" + phonenum
                                            + "&timestamp=" + timestamp
                                            + "&sig=" + resultString
                                            + "&respDataType=JSON";
                                    conn.setRequestProperty("Content-Length",
                                            String.valueOf(data.length()));
                                    conn.setDoOutput(true);
                                    conn.getOutputStream().write(
                                            data.getBytes());
                                    int code = conn.getResponseCode();
                                    Log.i("testtp", code + "");
                                    if (code == 200) {
                                        InputStream input = conn.getInputStream();

                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        int i;
                                        while ((i = input.read()) != -1) {
                                            baos.write(i);
                                        }
                                        String str = baos.toString();

                                        mHandler.obtainMessage(1, str).sendToTarget();
                                    } else {
//										Message msg = Message.obtain();
//										msg.what = ERROR1;
//										handler.sendMessage(msg);
                                    }

                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        // 发送短信验证码
                    } else {
                        Toast.makeText(Forget.this, "该用户还没有注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_forget.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String phonenum = et_phone_for.getText().toString();
                String SMS = et_SMS_for.getText().toString();
                String password = et_pwd_for.getText().toString();

                if (phonenum == null || phonenum.equals("")) {
                    Toast.makeText(Forget.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (phonenum.length() != 11) {
                    Toast.makeText(Forget.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else if (sms == null || sms.equals("")) {
                    Toast.makeText(Forget.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                     if(SMS.equals(sms)){
                     if(password == null || password.equals("")){
                     Toast.makeText(Forget.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                     }else{
                     String add = "Update user set password = '"+password+"' where phonenum = '"+phonenum+"'";
                     database.execSQL(add);
                     Toast.makeText(Forget.this, "找回密码成功", Toast.LENGTH_SHORT).show();
                     finish();
                     //将数据加入数据库,并返回（finish）
                     }
                     }else{
                     Toast.makeText(Forget.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                     }
                }
            }
        });
    }
}

