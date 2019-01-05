package com.example.hhhhentai.ulife;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhhhentai.Constant.Constant;

import java.util.Calendar;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Set_userInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_userInfo_return, iv_userInfo_birthday, iv_userInfo_sex;
    Button btn_userInfo_sure;
    TextView tv_userInfo_birthday, tv_userInfo_sex;
    private TextView tv_per;
    EditText et_PersonName;
    EditText et_PersonSig;
    EditText et_PersonSchool;
    EditText et_PersonHome;
    EditText et_PersonOffice;
    EditText et_PersonShow;
    String user_num;
    Calendar cal;
    Intent intent;

    private DbHelp_NEWS help_news;
    private Database_News database_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);
        //TODO 要从数据库中加载出个人信息
        init();
    }

    public void init() {
        intent = getIntent();
        user_num = intent.getStringExtra("user_num");
        cal = Calendar.getInstance();
        iv_userInfo_return = (ImageView) findViewById(R.id.iv_userInfo_return);
        iv_userInfo_birthday = (ImageView) findViewById(R.id.iv_userInfo_birthday);
        iv_userInfo_sex = (ImageView) findViewById(R.id.iv_userInfo_sex);
        btn_userInfo_sure = (Button) findViewById(R.id.btn_userInfo_sure);
        tv_userInfo_birthday = (TextView) findViewById(R.id.tv_userInfo_birthday);
        tv_userInfo_sex = (TextView) findViewById(R.id.tv_userInfo_sex);

        et_PersonName = (EditText) findViewById(R.id.et_PersonName);
        et_PersonSig = (EditText) findViewById(R.id.et_PersonSig);
        et_PersonSchool = (EditText) findViewById(R.id.et_PersonSchool);
        et_PersonHome = (EditText) findViewById(R.id.et_PersonHome);
        et_PersonOffice = (EditText) findViewById(R.id.et_PersonOffice);
        et_PersonShow = (EditText) findViewById(R.id.et_PersonShow);

        tv_per = (TextView)findViewById(R.id.tv_per);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.5f/12.4f + 0.5f),
                (int) (Constant.displayHeight * 0.5f/12.4f + 0.5f));
        params.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_userInfo_return.setLayoutParams(params);

        tv_per.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.04f);

        iv_userInfo_return.setOnClickListener(this);
        btn_userInfo_sure.setOnClickListener(this);
        iv_userInfo_birthday.setOnClickListener(this);
        iv_userInfo_sex.setOnClickListener(this);

        //创建信息的数据库
        help_news = new DbHelp_NEWS(this);
        //获取数据可读写对象
        database_news = new Database_News(help_news);

        Cursor cursor = database_news.query_personinfo(user_num);
        cursor.moveToFirst();
        String PersonName_text = cursor.getString(cursor.getColumnIndex("PersonName_text"));
        String PersonSig_text = cursor.getString(cursor.getColumnIndex("PersonSig_text"));
        String PersonBirth_text = cursor.getString(cursor.getColumnIndex("PersonBirth_text"));
        String PersonSex_text = cursor.getString(cursor.getColumnIndex("PersonSex_text"));
        String PersonSchool_text = cursor.getString(cursor.getColumnIndex("PersonSchool_text"));
        String PersonHome_text = cursor.getString(cursor.getColumnIndex("PersonHome_text"));
        String PersonOffice_text = cursor.getString(cursor.getColumnIndex("PersonOffice_text"));
        String PersonShow_text = cursor.getString(cursor.getColumnIndex("PersonShow_text"));

        et_PersonName.setText(PersonName_text);
        et_PersonSig.setText(PersonSig_text);
        tv_userInfo_birthday.setText(PersonBirth_text);
        tv_userInfo_sex.setText(PersonSex_text);
        et_PersonSchool.setText(PersonSchool_text);
        et_PersonHome.setText(PersonHome_text);
        et_PersonOffice.setText(PersonOffice_text);
        et_PersonShow.setText(PersonShow_text);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_userInfo_return: {
                finish();
                break;
            }
            case R.id.iv_userInfo_birthday: {
                DatePickerDialog dpd = new DatePickerDialog(Set_userInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date = i + "-" + (i1 + 1) + "-" + i2;
                        tv_userInfo_birthday.setText(date);
                    }
                },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                dpd.show();

                break;

            }

            case R.id.iv_userInfo_sex: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("请选择您的性别");
                dialog.setPositiveButton("男", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sex = "男";
                        tv_userInfo_sex.setText(sex);
                    }
                });
                dialog.setNeutralButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sex = "女";
                        tv_userInfo_sex.setText(sex);
                    }
                });
                dialog.create().show();
                break;
            }

            case R.id.btn_userInfo_sure: {
                //TODO 将所有个人的数据插入到数据库中
                String PersonPhone = intent.getStringExtra("user_num");
                String PersonName = et_PersonName.getText().toString();
                String PersonSig = et_PersonSig.getText().toString();
                String PersonBirth = tv_userInfo_sex.getText().toString();
                String PersonSex = tv_userInfo_sex.getText().toString();
                String PersonSchool = et_PersonSchool.getText().toString();
                String PersonHome = et_PersonHome.getText().toString();
                String PersonOffice = et_PersonOffice.getText().toString();
                String PersonShow = et_PersonShow.getText().toString();
                database_news.update_personinfo(PersonPhone, PersonName, PersonSig, PersonBirth, PersonSex, PersonSchool, PersonHome, PersonOffice, PersonShow, "", "");
            }
        }

    }
}
