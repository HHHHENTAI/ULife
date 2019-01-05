package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hhhhentai.Constant.Constant;
import com.example.hhhhentai.DbHelp.DbHelp;
import com.example.hhhhentai.background.CustomVideoView;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class MainActivity extends SwipeBackActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private LinearLayout LL_account,LL_all,LL_password,LL_sign_in,LL_sign_up,LL_fr,LL_for;
    private Button btn_sign_up,btn_sign_in;
    private EditText et_account,et_password;
    private TextView tv_pwd_for;
    private CheckBox cb_pwd_rem;
    private DbHelp help;
    private SQLiteDatabase database;

    //信息表
    private DbHelp_NEWS dbHelp_news;
    private Database_News database_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);

        help = new DbHelp(this);
        database = help.getWritableDatabase();

        //信息表的操作
        dbHelp_news = new DbHelp_NEWS(this);
        database_news = new Database_News(dbHelp_news);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;
        Log.i("test",Constant.displayWidth+" "+Constant.displayHeight);
        initView();
    }
    private void initView() {
        LL_account = (LinearLayout) findViewById(R.id.LL_account);
        LL_all = (LinearLayout) findViewById(R.id.LL_all);
        LL_password = (LinearLayout) findViewById(R.id.LL_password);
        LL_sign_in = (LinearLayout) findViewById(R.id.LL_sign_in);
        LL_sign_up = (LinearLayout) findViewById(R.id.LL_sign_up);
        LL_fr = (LinearLayout) findViewById(R.id.LL_fr);
        LL_for = (LinearLayout) findViewById(R.id.LL_for);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_pwd_for = (TextView)findViewById(R.id.tv_pwd_for);
        cb_pwd_rem = (CheckBox)findViewById(R.id.cb_pwd_rem);

        btn_sign_in.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        btn_sign_up.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        tv_pwd_for.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        cb_pwd_rem.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.018f);
        et_account.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);
        et_password.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.02f);


        //取得设置好的drawable对象
        Drawable drawable = this.getResources().getDrawable(R.drawable.checkbox_style);

        //设置drawable对象的大小
        drawable.setBounds(0,0,(int) (Constant.displayHeight * 0.02f),(int) (Constant.displayHeight * 0.02f));

        //设置CheckBox对象的位置，对应为左、上、右、下
        cb_pwd_rem.setCompoundDrawables(drawable,null,null,null);

        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        Boolean loged = preferences.getBoolean("loged",false);
        String ac = preferences.getString("acc","");
        if(loged){
            Intent intent = new Intent(MainActivity.this, main_part.class);
            intent.putExtra("phonenum",ac);
            startActivity(intent);
            finish();
        }
        String acc = preferences.getString("account","");
        String pass = preferences.getString("password","");
        boolean isRemember_password = preferences.getBoolean("remember_password",false);

        et_account.setText(acc);
        et_password.setText(pass);
        cb_pwd_rem.setChecked(isRemember_password);

        cb_pwd_rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if(arg1){
                    cb_pwd_rem.setChecked(true);
                }
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                String judge = "Select * from user where phone = '"+account+"' and password = '"+password+"'";
                Cursor c = database.rawQuery(judge, null);
                if (account == null||account.equals("")) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }else if(password == null||password.equals("")){
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(c.moveToNext() == true){
                    if(cb_pwd_rem.isChecked()){
                        //保存
                        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("remember_password", true);
                        editor.commit();
                    }else{
                        //清空
                        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("account","");
                        editor.putString("password","");
                        editor.putBoolean("remember_password", false);
                        editor.commit();
                    }
                    SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putBoolean("loged",true);
                    editor.putString("acc",account);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, main_part.class);
                    intent.putExtra("phonenum",account);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this,Register_phonenum.class);
                startActivity(intent);
            }
        });

        btn_sign_up.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String insert = "INSERT INTO user(account,password,name,phone)VALUES ('12345678910','123','nick','12345678910')";
                database.execSQL(insert);

                Cursor cursor = database_news.query_personinfo("12345678910");
                //个人信息表插入
                if (cursor.getCount() == 0) {
                    database_news.insert_personinfo("12345678910", "", "昵 称", "个 人 签 名", "", "", "", "", "", "", "", "");
                } else {
                    Toast.makeText(MainActivity.this, "管理员用户已存在！", Toast.LENGTH_SHORT).show();
                }

                et_account.setText("12345678910");
                et_password.setText("123");
                return true;
            }
        });

        tv_pwd_for.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this,Forget.class);
                startActivity(intent);
            }
        });

        videoview = (CustomVideoView) findViewById(R.id.cvv_background);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.shipin2));

        //布局设置
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.7f + 0.5f));
        LL_all.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params1.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,(int) (Constant.displayHeight * 0.01f + 0.5f));
        LL_account.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params2.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,0);
        LL_password.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.06f + 0.5f));
        params3.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,0);
        LL_fr.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.08f + 0.5f));
        params4.setMargins(0,(int) (Constant.displayHeight * 0.1f + 0.5f),0,0);
        LL_sign_in.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.08f + 0.5f));
        params5.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,0);
        LL_sign_up.setLayoutParams(params5);

        LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.07f + 0.5f));
        params6.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,0);
        LL_for.setLayoutParams(params6);


        // 播放
        videoview.start();
        // 循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp,int what, int extra) {
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    // 返回重启加载
    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }

    // 防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        super.onStop();
        videoview.stopPlayback();
    }
}
