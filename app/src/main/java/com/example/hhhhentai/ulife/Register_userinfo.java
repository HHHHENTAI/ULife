package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hhhhentai.DbHelp.DbHelp;

public class Register_userinfo extends SwipeBackActivity {

    private EditText et_pwd_reg,et_pwd_confirm_reg,et_name_reg,et_phone_reg,et_SMS_reg;
    private Button btn_register,btn_SMS_reg;
    private DbHelp help;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_userinfo);

        et_pwd_reg = (EditText) findViewById(R.id.et_pwd_reg);
        et_pwd_confirm_reg = (EditText) findViewById(R.id.et_pwd_confirm_reg);
        et_name_reg = (EditText) findViewById(R.id.et_name_reg);
        btn_register = (Button) findViewById(R.id.btn_register);

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
                    String insert = "INSERT INTO user(account,password,name,phone)VALUES ('"+phonenum+"','"+password+"','"+name+"','"+phonenum+"')";
                    database.execSQL(insert);
                    Toast.makeText(Register_userinfo.this, "注冊成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}

