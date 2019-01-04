package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.os.Bundle;
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

import com.alibaba.fastjson.JSON;
import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.DbHelp.DbHelp;
import com.example.hhhhentai.JsonGet.Sms;
import com.example.hhhhentai.MD5Utils.MD5Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Register_phonenum extends SwipeBackActivity {

    private EditText et_phone_reg,et_SMS_reg;
    private TimeCount time;
    private Button btn_next,btn_SMS_reg;
    private LinearLayout LL_phonenum_reg,LL_phone_reg,LL_SMS_reg,LL_btn_next;
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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        time = new TimeCount(120000, 1000);

        et_phone_reg = (EditText) findViewById(R.id.et_phone_reg);
        et_SMS_reg = (EditText) findViewById(R.id.et_SMS_reg);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_SMS_reg = (Button) findViewById(R.id.btn_SMS_reg);
        LL_phonenum_reg = (LinearLayout) findViewById(R.id.LL_phonenum_reg);
        LL_phone_reg = (LinearLayout) findViewById(R.id.LL_phone_reg);
        LL_SMS_reg = (LinearLayout) findViewById(R.id.LL_SMS_reg);
        LL_btn_next = (LinearLayout)findViewById(R.id.LL_btn_next);

        et_phone_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_SMS_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_next.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_SMS_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

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
                        time.start();
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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.7f + 0.5f));
        LL_phonenum_reg.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        LL_phone_reg.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params2.setMargins(0,(int) (Constant.displayHeight * 0.03f + 0.5f),0,0);
        LL_SMS_reg.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params3.setMargins(0,(int) (Constant.displayHeight * 0.09f + 0.5f),0,0);
        LL_btn_next.setLayoutParams(params3);
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_SMS_reg.setClickable(false);
            btn_SMS_reg.setText(""+millisUntilFinished / 1000 +" s");
        }

        @Override
        public void onFinish() {
            btn_SMS_reg.setText("重新获取验证码");
            btn_SMS_reg.setClickable(true);

        }
    }
}

