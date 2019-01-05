package com.example.hhhhentai.ulife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static java.security.AccessController.getContext;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener{

    //调用系统相册-选择图片
    private static final  int IMAGE=2;
    public static final String IMAGE_NAME = "pic.jpg";
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_CROP = 1;
    ImageView iv_add;
    Button btn_camera;
    Button btn_finish;
    Button btn_cancal;
    ImageView [] iv=new ImageView[5];
    String[]  ip=new String[]{"","","","",""};
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        iv_add=findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv[0] = findViewById(R.id.iv_one);
        iv[1] =findViewById(R.id.iv_two);
        iv[2]=findViewById(R.id.iv_three);
        iv[3]=findViewById(R.id.iv_fore);
        iv[4]=findViewById(R.id.iv_five);


        btn_camera=findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_finish=findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(this);
        btn_cancal=findViewById(R.id.btn_cancle);
        btn_cancal.setOnClickListener(this);

       for(int i=0;i<5;i++){
           setsize(iv[i]);
       }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //添加图片
            case R.id.iv_add:
                //调用相册
                Log.i("TAG","照片");
                Intent intent1 =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Log.i("TAG","000");
                intent1.setType("image/*");
                startActivityForResult(intent1,IMAGE);
                break;
            //TODO 调用摄像头
            case R.id.btn_camera:

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(),IMAGE_NAME)));
                startActivityForResult(intent,REQUEST_CODE_CAMERA);

                break;
            //传递图片路径
            case R.id.btn_finish:
                Intent intent2=getIntent();
                Bundle bundle=new Bundle();
                path p=new path();
                p.setone(ip[0]);
                p.settwo(ip[1]);
                p.setthree(ip[2]);
                p.setfore(ip[3]);
                p.setfive(ip[4]);
                bundle.putSerializable("iv",p);
                intent2.putExtras(bundle);
                setResult(10,intent2);
                finish();
                break;

            case R.id.btn_cancle:
                finish();
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode==IMAGE && resultCode== Activity.RESULT_OK && data!=null ){
            Log.i("TAG","yanzheng");
            Uri selectedImage =data.getData();
            String[] filePathColumns={MediaStore.Images.Media.DATA};
            Cursor c=getContentResolver().query(selectedImage,filePathColumns,null,null,null);
            c.moveToFirst();
            int columnIndex =c.getColumnIndex(filePathColumns[0]);
            String imagePath =c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        if(requestCode==REQUEST_CODE_CAMERA){
            File file = new File(Environment.getExternalStorageDirectory(),IMAGE_NAME);
            zommPicture(Uri.fromFile(file));


        }
        if(requestCode==REQUEST_CODE_CROP){
            Bitmap bm = data.getExtras().getParcelable("data");
            iv[0].setImageBitmap(bm);
        }



    }
    public void zommPicture(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop",true);
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",135);
        intent.putExtra("outputY",135);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,REQUEST_CODE_CROP);

    }

    //设置图片的宽高
    public void setsize(ImageView iv_size){
        ViewGroup.LayoutParams params;
        params=iv_size.getLayoutParams();
        iv_size.setAdjustViewBounds(true);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        Integer h = dm.heightPixels;//屏幕高度
        Integer w = dm.widthPixels;  //屏幕宽度
        params.height=h/6;
        params.width=w/5;
    }


    //加载图片
    private void showImage(String imagePath){

        Bitmap bm=BitmapFactory.decodeFile(imagePath);
        if(count<5){
            ip[count]=imagePath;
            iv[count].setImageBitmap(bm);
            count++;
        }else Toast.makeText(PictureActivity.this,"最多选择五张图片",Toast.LENGTH_LONG).show();

    }

}

