package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Seting extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_seting_return;
    private TextView tv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        iv_seting_return = (ImageView)findViewById(R.id.iv_seting_return);
        tv_logout = (TextView)findViewById(R.id.tv_logout);

        iv_seting_return.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
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
