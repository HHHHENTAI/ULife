package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

public class MainActivity extends SwipeBackActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private LinearLayout LL_all,LL_password,LL_sign_in,LL_sign_up;
    private Button btn_sign_up,btn_sign_in;
    private EditText et_account,et_password;
    private TextView tv_pwd_for;
    private CheckBox cb_pwd_rem;
    private DbHelp help;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        help = new DbHelp(this);
        database = help.getWritableDatabase();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;
        Log.i("test",Constant.displayWidth+" "+Constant.displayHeight);
        initView();
    }
    private void initView() {
        LL_all = (LinearLayout) findViewById(R.id.LL_all);
        LL_password = (LinearLayout) findViewById(R.id.LL_password);
        LL_sign_in = (LinearLayout) findViewById(R.id.LL_sign_in);
        LL_sign_up = (LinearLayout) findViewById(R.id.LL_sign_up);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_pwd_for = (TextView)findViewById(R.id.tv_pwd_for);
        cb_pwd_rem = (CheckBox)findViewById(R.id.cb_pwd_rem);

        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
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
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,(int) (Constant.displayHeight * 0.04f + 0.5f),0,(int) (Constant.displayHeight * 0.01f + 0.5f));
        LL_password.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.08f + 0.5f));
        btn_sign_up.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                (int) (Constant.displayWidth * 0.7f + 0.5f),
                (int) (Constant.displayHeight * 0.08f + 0.5f));
        btn_sign_in.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params4.setMargins(0,(int) (Constant.displayHeight * 0.2f + 0.5f),0,0);
        LL_sign_in.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params5.setMargins(0,(int) (Constant.displayHeight * 0.02f + 0.5f),0,0);
        LL_sign_up.setLayoutParams(params5);



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
