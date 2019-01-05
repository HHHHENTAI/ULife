package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hhhhentai.Constant.Constant;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Seting extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_seting_return;
    private TextView tv_logout,tv_set;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;

        iv_seting_return = (ImageView)findViewById(R.id.iv_seting_return);
        tv_logout = (TextView)findViewById(R.id.tv_logout);
        tv_set = (TextView)findViewById(R.id.tv_set);

        iv_seting_return.setOnClickListener(this);
        tv_logout.setOnClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (Constant.displayHeight * 0.5f/12.4f + 0.5f),
                (int) (Constant.displayHeight * 0.5f/12.4f + 0.5f));
        params.setMargins((int) (Constant.displayWidth * 0.03f + 0.5f),0,0,0);
        iv_seting_return.setLayoutParams(params);

        tv_set.setTextSize(COMPLEX_UNIT_PX,Constant.displayHeight * 0.04f);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.tv_logout:
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putBoolean("loged",false);
                editor.putString("acc","");
                editor.commit();
                Intent intent = new Intent(Seting.this,MainActivity.class);
                startActivity(intent);
                finish();
                SysApplication.getInstance().exit();
                break;
            case R.id.iv_seting_return:
                finish();
                break;
        }
    }
}
