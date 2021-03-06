package com.example.hhhhentai.ulife;

import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import com.alibaba.fastjson.JSON;
import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.DbHelp.DbHelp;
import com.example.hhhhentai.JsonGet.Sms;
import com.example.hhhhentai.MD5Utils.MD5Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Forget extends SwipeBackActivity{

    private EditText et_phone_for, et_SMS_for, et_pwd_for;
    private Button btn_SMS_for, btn_forget;
    private LinearLayout LL_forget,LL_phone_for,LL_SMS_for,LL_pwd_for,LL_btn_for;
    private DbHelp help;
    private SQLiteDatabase database;
    private String sms = "";

    private TimeCount time;



    final private String accountSid = "8fda0355b69e4681be18df9a074302de";
    final private String token = "5fbb861823844099bb5763111a4eff73";
    final private String path = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        //数据库
        help = new DbHelp(Forget.this);
        database = help.getWritableDatabase();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        for(int i = 0; i < 6;i++){
            sms = sms+(int)(Math.random()*10)+"";
        }
        Log.i("test",sms);

        time = new TimeCount(120000, 1000);

        LL_forget = (LinearLayout) findViewById(R.id.LL_forget);
        LL_phone_for = (LinearLayout) findViewById(R.id.LL_phone_for);
        LL_SMS_for = (LinearLayout) findViewById(R.id.LL_SMS_for);
        LL_pwd_for = (LinearLayout) findViewById(R.id.LL_pwd_for);
        LL_btn_for = (LinearLayout) findViewById(R.id.LL_btn_for);
        et_phone_for = (EditText) findViewById(R.id.et_phone_for);
        et_SMS_for = (EditText) findViewById(R.id.et_SMS_for);
        et_pwd_for = (EditText) findViewById(R.id.et_pwd_for);
        btn_SMS_for = (Button) findViewById(R.id.btn_SMS_for);
        btn_forget = (Button) findViewById(R.id.btn_forget);

        et_phone_for.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_SMS_for.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_pwd_for.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_SMS_for.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_forget.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

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
                        time.start();
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
                                            + "&templateid=1184945969"
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
        //  LL_forget,LL_phone_for,LL_SMS_for,LL_pwd_for,LL_btn_for
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.7f + 0.5f));
        LL_forget.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        LL_phone_for.setLayoutParams(params1);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params2.setMargins(0,(int) (Constant.displayHeight * 0.03f + 0.5f),0,0);
        LL_SMS_for.setLayoutParams(params2);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params3.setMargins(0,(int) (Constant.displayHeight * 0.03f + 0.5f),0,0);
        LL_pwd_for.setLayoutParams(params3);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.06f + 0.5f));
        params4.setMargins(0,(int) (Constant.displayHeight * 0.07f + 0.5f),0,0);
        LL_btn_for.setLayoutParams(params4);

    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_SMS_for.setClickable(false);
            btn_SMS_for.setText(""+millisUntilFinished / 1000 +" s");
        }

        @Override
        public void onFinish() {
            btn_SMS_for.setText("重新获取验证码");
            btn_SMS_for.setClickable(true);
        }
    }
}

