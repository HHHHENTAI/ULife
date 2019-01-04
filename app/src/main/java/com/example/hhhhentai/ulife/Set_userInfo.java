package com.example.hhhhentai.ulife;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Set_userInfo extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_userInfo_return,iv_userInfo_birthday,iv_userInfo_sex;
    Button btn_userInfo_sure;
    TextView tv_userInfo_birthday,tv_userInfo_sex;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);

        //TODO 要从数据库中加载出个人信息

        init();

    }

    public void init()
    {
        cal = Calendar.getInstance();

        iv_userInfo_return = (ImageView)findViewById(R.id.iv_userInfo_return);
        iv_userInfo_birthday = (ImageView)findViewById(R.id.iv_userInfo_birthday);
        iv_userInfo_sex = (ImageView)findViewById(R.id.iv_userInfo_sex);
        btn_userInfo_sure = (Button)findViewById(R.id.btn_userInfo_sure) ;
        tv_userInfo_birthday = (TextView)findViewById(R.id.tv_userInfo_birthday);
        tv_userInfo_sex = (TextView)findViewById(R.id.tv_userInfo_sex);

        iv_userInfo_return.setOnClickListener(this);
        btn_userInfo_sure.setOnClickListener(this);
        iv_userInfo_birthday.setOnClickListener(this);
        iv_userInfo_sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_userInfo_return:
            {
                finish();
                break;
            }
            case R.id.iv_userInfo_birthday:
            {
                DatePickerDialog dpd = new DatePickerDialog(Set_userInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
                    {
                        String date = i+"-"+i1+"-"+i2;
                        tv_userInfo_birthday.setText(date);
                    }
                },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                dpd.show();

                break;

            }

            case R.id.iv_userInfo_sex:
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("请选择您的性别");
                dialog.setPositiveButton("男", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        String sex="男";
                        tv_userInfo_sex.setText(sex);
                    }
                });
                dialog.setNeutralButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        String sex="女";
                        tv_userInfo_sex.setText(sex);
                    }
                });
                dialog.create().show();

                break;
            }

            case R.id.btn_userInfo_sure:
            {
                //TODO 将所有个人的数据插入到数据库中
            }
        }

    }
}
