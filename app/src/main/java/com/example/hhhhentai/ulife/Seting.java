package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Seting extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_seting_return;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        iv_seting_return = (ImageView)findViewById(R.id.iv_seting_return);
        iv_seting_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        finish();

    }
}
