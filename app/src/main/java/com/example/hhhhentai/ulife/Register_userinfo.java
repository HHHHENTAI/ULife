package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.DbHelp.DbHelp;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Register_userinfo extends SwipeBackActivity {

    private EditText et_pwd_reg,et_pwd_confirm_reg,et_name_reg;
    private Button btn_register;
    private LinearLayout LL_userinfo_reg,LL_pwd_reg,LL_pwd_confirm_reg,LL_name_reg,LL_btn_reg;
    private DbHelp help;
    private SQLiteDatabase database;

    private DbHelp_NEWS dbHelp_news;
    private Database_News database_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_userinfo);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        et_pwd_reg = (EditText) findViewById(R.id.et_pwd_reg);
        et_pwd_confirm_reg = (EditText) findViewById(R.id.et_pwd_confirm_reg);
        et_name_reg = (EditText) findViewById(R.id.et_name_reg);
        btn_register = (Button) findViewById(R.id.btn_register);
        LL_pwd_reg = (LinearLayout) findViewById(R.id.LL_pwd_reg);
        LL_pwd_confirm_reg = (LinearLayout) findViewById(R.id.LL_pwd_confirm_reg);
        LL_name_reg = (LinearLayout) findViewById(R.id.LL_name_reg);
        LL_btn_reg = (LinearLayout) findViewById(R.id.LL_btn_reg);
        LL_userinfo_reg = (LinearLayout) findViewById(R.id.LL_userinfo_reg);

        et_pwd_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_pwd_confirm_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_name_reg.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_register.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);

        Intent intent = getIntent();
        final String phonenum = intent.getStringExtra("phonenum");

        btn_register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String password = et_pwd_reg.getText().toString();
                String password_confirm = et_pwd_confirm_reg.getText().toString();
                String name = et_name_reg.getText().toString();

                if(password == null||password.equals("")){
                    Toast.makeText(Register_userinfo.this, "密碼不能为空", Toast.LENGTH_SHORT).show();
                }else if(password_confirm == null||password_confirm.equals("")){
                    Toast.makeText(Register_userinfo.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(name == null||name.equals("")){
                    Toast.makeText(Register_userinfo.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password_confirm)){
                    Toast.makeText(Register_userinfo.this, "请重新确认密码", Toast.LENGTH_SHORT).show();
                }else{
                    help = new DbHelp(Register_userinfo.this);
                    database = help.getWritableDatabase();
                    dbHelp_news=new DbHelp_NEWS(Register_userinfo.this);
                    database_news=new Database_News(dbHelp_news);

                    String insert = "INSERT INTO user(account,password,name,phone)VALUES ('"+phonenum+"','"+password+"','"+name+"','"+phonenum+"')";
                    database.execSQL(insert);

                    database_news.insert_personinfo(phonenum,"",name,"","","","","","","","","");

                    Toast.makeText(Register_userinfo.this, "注冊成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.7f + 0.5f));
        LL_userinfo_reg.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        LL_pwd_reg.setLayoutParams(params1);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params2.setMargins(0,(int) (Constant.displayHeight * 0.03f + 0.5f),0,0);
        LL_pwd_confirm_reg.setLayoutParams(params2);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params3.setMargins(0,(int) (Constant.displayHeight * 0.03f + 0.5f),0,0);
        LL_name_reg.setLayoutParams(params3);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.8f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params4.setMargins(0,(int) (Constant.displayHeight * 0.07f + 0.5f),0,0);
        LL_btn_reg.setLayoutParams(params4);

    }
}

