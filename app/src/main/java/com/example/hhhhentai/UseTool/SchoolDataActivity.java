package com.example.hhhhentai.UseTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.hhhhentai.ulife.R;

import org.xutils.x;

public class SchoolDataActivity extends AppCompatActivity {

    private ImageView Im_schooldate;
    private int dw_h []=new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_data);
        x.view().inject(this);

        loadPicture();

    }

    private void loadPicture() {
        Im_schooldate = findViewById(R.id.Im_SchoolData);
        Im_schooldate.setImageResource(R.drawable.ic_schooldate);


        /*//加载校历
       String url="http://dept.whut.edu.cn/xb/cycx/xxxl/201802/P020180227536893410235.jpg";
        x.image().bind(Im_schooldate, url);
       */



        int width, height;//ImageView调整后的宽高

        //获取屏幕宽高
        WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int sWidth = metrics.widthPixels;
        int sHeight = metrics.heightPixels;
        Log.i("屏幕", sWidth + "\t" + sHeight);

        //获取图片宽高
        Drawable drawable = Im_schooldate.getDrawable();
        int dWidth = drawable.getIntrinsicWidth();
        int dHeight = drawable.getIntrinsicHeight();
        Log.i("原图", dWidth + "\t" + dHeight);

        //屏幕宽高比,一定要先把其中一个转为float
        float sScale = (float) sWidth / sHeight;
        //图片宽高比
        float dScale = (float) dWidth / dHeight;

        /*
        缩放比
        如果sScale>dScale，表示在高相等的情况下，控屏幕比较宽，这时候要适应高度，缩放比就是两则的高之比，图片宽度用缩放比计算
        如果sScale<dScale，表示在高相等的情况下，图片比较宽，这时候要适应宽度，缩放比就是两则的宽之比，图片高度用缩放比计算
         */


        float scale = 1.0f;
        if (sScale > dScale) {
            scale = (float) dHeight / sHeight;
            height = sHeight;//图片高度就是屏幕高度
            width = (int) (dWidth * scale);//按照缩放比算出图片缩放后的宽度
        } else if (sScale < dScale) {
            scale = (float) dWidth / sWidth;
            width = sWidth;
            height = (int) (dHeight / scale);//这里用除
        } else {
            //最后两者刚好比例相同，其实可以不用写，刚好充满
            width = sWidth;
            height = sHeight;
        }
        //重设ImageView宽高
        Log.i("修图", width + "\t" + height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        Im_schooldate.setLayoutParams(params);
        //这样就获得了一个既适应屏幕有适应内部图片的ImageView，不用再纠结该给ImageView设定什么尺寸合适了

    }

}
