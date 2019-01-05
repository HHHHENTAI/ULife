package com.example.hhhhentai.ulife;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import DbHelp_ZXK.Database_News;
import DbHelp_ZXK.DbHelp_NEWS;

public class ChangeHeadphoto extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv_headPhoto_takePhoto,iv_headPhoto_choose,iv_headPhoto_cancel,iv_changeHeadPhoto_return,iv_headphoto;

    Uri imageUri;
    File imageFile;
    String imagePath;
    Bitmap bitmapdown;
    final static int CAMERA =1;
    final static int ICON =2;
    final static int CAMERAPRESS =3;
    final static int ICONPRESS=4;
    private DbHelp_NEWS help_news;
    private Database_News database_news;
    Intent intent;
    String PersonPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_headphoto);
        init();
    }

    private void init()
    {
        iv_headphoto = (ImageView)findViewById(R.id.iv_headphoto) ;
        iv_headPhoto_takePhoto = (ImageView)findViewById(R.id.iv_headPhoto_takePhoto);
        iv_headPhoto_choose = (ImageView)findViewById(R.id.iv_headPhoto_choose);
        iv_headPhoto_cancel = (ImageView)findViewById(R.id.iv_headPhoto_cancel);
        iv_changeHeadPhoto_return = (ImageView)findViewById(R.id.iv_changeHeadPhoto_return);

        iv_changeHeadPhoto_return.setOnClickListener(this);
        iv_headPhoto_takePhoto.setOnClickListener(this);
        iv_headPhoto_choose.setOnClickListener(this);
        iv_headPhoto_cancel.setOnClickListener(this);

        //数据库操作封装函数
        help_news=new DbHelp_NEWS(this);
        database_news = new Database_News(help_news);

        intent = getIntent();
        PersonPhone = intent.getStringExtra("user_num");
    }



    public void  startCamera()
    {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        imageFile = new File(path, "heard.png");
        try
        {
            if (imageFile.exists())
            {
                imageFile.delete();
            }
            imageFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        imageUri = Uri.fromFile(imageFile);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent, CAMERA); //启动照相
    }

    public void startIcon()
    {
        Intent intent1 = new Intent(Intent.ACTION_PICK);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent1, ICON);
    }




    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_headPhoto_takePhoto://打开相机拍照
            {
                if( Build.VERSION.SDK_INT>=23)
                {
                    //Toast.makeText(this,"当前的版本号"+Build.VERSION.SDK_INT,Toast.LENGTH_LONG).show();
                    //android 6.0权限问题
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||
                            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERAPRESS);
                        Toast.makeText(this,"执行了权限请求",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        startCamera();
                    }

                }
                else
                {
                    startCamera();
                }
                break;
            }
            case R.id.iv_headPhoto_choose:
            {

                if( Build.VERSION.SDK_INT>=23)
                {
                    //Toast.makeText(this,"当前的版本号"+Build.VERSION.SDK_INT,Toast.LENGTH_LONG).show();
                    //android 6.0权限问题
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||
                            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED   )
                    {
                        Toast.makeText(this,"执行了权限请求",Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERAPRESS);
                    }
                    else
                    {
                        startIcon();
                    }
                }
                else
                {
                    startIcon();
                }
                break;
            }
            case R.id.iv_headPhoto_cancel:
            {
                finish();
            }
            case R.id.iv_changeHeadPhoto_return:
            {
                Intent intent1 = new Intent();
                intent1.putExtra("imagePath",imagePath);
                setResult(RESULT_OK,intent1);
                finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent();
        intent1.putExtra("imagePath",imagePath);
        setResult(RESULT_OK,intent1);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode)
        {
            case CAMERA:
            {
                Bitmap bitmap1 = null;
                try
                {
                    bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    imagePath = getPath(this, imageUri);
                    bitmapdown = bitmap1;
                    iv_headphoto.setImageBitmap(bitmapdown);
                    Log.i("chenzhu","imagePath"+imagePath);
                    database_news.update_personinfo_personimage(PersonPhone,imagePath);
                }
                catch (FileNotFoundException e)
                {
                    imageFile = null;
                    e.printStackTrace();
                }
                Log.d("chenzhu","imagePath"+imagePath);

                break;
            }

            case ICON:
            {
                DisplayMetrics metric = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metric);
                String dst = getPath(this, data.getData());
                Log.i("tree2",dst);

                imageFile = new File(dst);
                imagePath = dst;
                Bitmap bitmap = ThumbnailUtils.extractThumbnail(getBitmapFromFile(imageFile), 1000, 1000);
                bitmapdown = bitmap;
                iv_headphoto.setImageBitmap(bitmapdown);
                Log.d("chenzhu","imagePath"+imagePath);
                database_news.update_personinfo_personimage(PersonPhone,imagePath);
                break;
            }
        }
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CAMERAPRESS:
            {
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    //获取到了权限
                    startCamera();
                }
                else
                {
                    Toast.makeText(this,"对不起你没有同意该权限",Toast.LENGTH_LONG).show();
                }
                break;
            }

            case ICONPRESS:
            {
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    //获取到了权限
                    startIcon();
                }
                else
                {
                    Toast.makeText(this,"对不起你没有同意该权限",Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    public Bitmap getBitmapFromFile(File dst)
    {
        if (null != dst && dst.exists())
        {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //opts.inJustDecodeBounds = false;
            opts.inSampleSize = 2;

            try
            {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            }
            catch (OutOfMemoryError e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getPath(final Context context, final Uri uri)
    {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri))
        {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri))
            {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type))
                {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri))
            {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri))
            {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type))
                {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }
                else if ("video".equals(type))
                {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }
                else if ("audio".equals(type))
                {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme()))
        {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme()))
        {
            return uri.getPath();
        }
        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs)
    {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try
        {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst())
            {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }
        finally
        {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri)
    {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri)
    {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static boolean isMediaDocument(Uri uri)
    {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static boolean isGooglePhotosUri(Uri uri)
    {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
