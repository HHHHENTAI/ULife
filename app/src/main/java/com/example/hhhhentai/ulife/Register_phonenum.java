package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.hhhhentai.DbHelp.DbHelp;
import com.example.hhhhentai.JsonGet.Sms;
import com.example.hhhhentai.MD5Utils.MD5Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class Register_phonenum extends Activity {

    private EditText et_acc_reg,et_pwd_reg,et_name_reg,et_phone_reg,et_SMS_reg;
    private Button btn_next,btn_SMS_reg;
    private DbHelp help;
    private SQLiteDatabase database;
    private String sms = "";

    final private String accountSid = "8fda0355b69e4681be18df9a074302de";
    final private String token = "5fbb861823844099bb5763111a4eff73";
    final private String path = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phonenum);

        help = new DbHelp(Register_phonenum.this);
        database = help.getWritableDatabase();

        et_phone_reg = (EditText) findViewById(R.id.et_phone_reg);
        et_SMS_reg = (EditText) findViewById(R.id.et_SMS_reg);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_SMS_reg = (Button) findViewById(R.id.btn_SMS_reg);

        for(int i = 0; i < 6;i++){
            sms = sms+(int)(Math.random()*10)+"";
        }
        Log.i("test",sms);

        final Handler mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 1) {
                    String str = (String) msg.obj;
                    //使用alibaba的方法获取需要的数据
                    Sms s = JSON.parseObject(str, Sms.class);
                    Log.i("ppp",s.toString());
                }
            };
        };

        btn_SMS_reg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final String phonenum = et_phone_reg.getText().toString();
                if(phonenum.length() != 11){
                    Toast.makeText(Register_phonenum.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else{
                    help = new DbHelp(Register_phonenum.this);
                    database = help.getWritableDatabase();
                    String judge = "Select * from user where phone = '"+phonenum+"'";
                    Cursor c = database.rawQuery(judge, null);
                    if(c.moveToNext() == true){
                        Toast.makeText(Register_phonenum.this, "该手机号已被注册", Toast.LENGTH_SHORT).show();
                    }else{
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
                        //发送短信验证码
                    }
                }
            }
        });

        btn_next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String phonenum = et_phone_reg.getText().toString();
                String SMS = et_SMS_reg.getText().toString();

                if(phonenum == null||phonenum.equals("")){
                    Toast.makeText(Register_phonenum.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else if(sms == null||sms.equals("")){
                    Toast.makeText(Register_phonenum.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }else if(phonenum.length()!=11){
                    Toast.makeText(Register_phonenum.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else if(!sms.equals(SMS)){
                Toast.makeText(Register_phonenum.this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent  = new Intent(Register_phonenum.this,Register_userinfo.class);
                    intent.putExtra("phonenum",phonenum);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

