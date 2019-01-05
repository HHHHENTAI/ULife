package com.example.hhhhentai.UseTool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hhhhentai.JsonGet.GetExpressageData;
import com.example.hhhhentai.ulife.R;

public class ExpressageActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView Im_expressagequit;
    private Button Bt_expressagequery;

    private EditText Et_expressagecp;
    private EditText Et_expressagenum;
    private ListView Lv_expressagelist;

    private String key="02ef5afc818eb8717ace280c1e39b6c5";

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expressage);
        init();
    }

    private void init() {
        Im_expressagequit = findViewById(R.id.Im_expressagequit);
        Im_expressagequit.setOnClickListener(this);

        Bt_expressagequery = findViewById(R.id.Bt_expressagequery);
        Bt_expressagequery.setOnClickListener(this);

        Et_expressagecp = findViewById(R.id.Et_expressagecp);
        Et_expressagenum = findViewById(R.id.Et_expressagenum);

        Lv_expressagelist=findViewById(R.id.Lv_expressagelist);

        context=this;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Im_expressagequit:
                finish();
                break;
            case R.id.Bt_expressagequery:

                String companyname=Et_expressagecp.getText().toString();
                String expressagenum=Et_expressagenum.getText().toString();

                new GetExpressageData(companyname,expressagenum,Lv_expressagelist,context).execute("http://v.juhe.cn/exp/com?key="+key);

                //new GetExpressageData(companyname,expressagenum).execute(" http://v.juhe.cn/exp/index?key=02ef5afc818eb8717ace280c1e39b6c5&com=sto&no=3392110977509");
                break;
            default:
                break;
        }

    }
}
