package com.example.hhhhentai.UseTool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hhhhentai.JsonGet.GetWeatherData;

import com.example.hhhhentai.ulife.R;
import com.example.hhhhentai.ulife.SwipeBackActivity;

public class WeatherActivity extends SwipeBackActivity implements View.OnClickListener {

    private EditText Et_weathercityname;
    private Button Bt_weatherquery;
    private ImageView Im_weatherquit;
    private ImageView Im_weatherpicture;

    private String cityname="";
    private String key="15555c8daea3e997875dd4af4a2a49eb";

    private TextView Tv_weatherdate;
    private TextView Tv_weathertemp;
    private TextView Tv_weather;
    private TextView Tv_weatherwind;
    private TextView Tv_weatherdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();

    }

    public void init() {
        Et_weathercityname = findViewById(R.id.Et_weathercityname);
        Bt_weatherquery =  findViewById(R.id.Bt_weatherquery);
        Bt_weatherquery.setOnClickListener(this);

        Im_weatherquit=findViewById(R.id.Im_weatherquit);
        Im_weatherquit.setOnClickListener(this);

        Im_weatherpicture=findViewById(R.id.Im_weatherpicture);

        Tv_weatherdate=findViewById(R.id.Tv_weatherdate);
        Tv_weathertemp=findViewById(R.id.Tv_weathertemp);
        Tv_weather=findViewById(R.id.Tv_weather);
        Tv_weatherwind=findViewById(R.id.Tv_weatherwind);
        Tv_weatherdress=findViewById(R.id.Tv_weatherdress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Bt_weatherquery:
                cityname = Et_weathercityname.getText().toString();
                Log.i("cityname",cityname);
                new GetWeatherData(Tv_weatherdate,Tv_weathertemp,Tv_weather,Tv_weatherwind,Tv_weatherdress,Im_weatherpicture,WeatherActivity.this).execute("http://v.juhe.cn/weather/index"+"?cityname="+cityname+"&key="+key);
                break;

            case R.id.Im_weatherquit:
                finish();
            default:
                break;
        }
    }
}
