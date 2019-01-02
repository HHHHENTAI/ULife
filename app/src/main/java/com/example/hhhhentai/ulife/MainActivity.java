package com.example.hhhhentai.ulife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xutils.x;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        //dsadsadsa
        //123

        //跳转按键，测试，by江守鑫
         textView =  (TextView)findViewById(R.id.wzh);
         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this,main_part.class));
             }
         });
        //我是盛光明的测试
        //我是吴泽豪的测试
        //test123
        //test

    }
}
