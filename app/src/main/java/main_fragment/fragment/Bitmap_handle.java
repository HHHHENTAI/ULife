package main_fragment.fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bitmap_handle {
    //图片转为二进制数据
    public byte[] bitmabToBytes(String path) {
        //将图片转化为位图
        FileInputStream fis = null;
        try {
            //例子
            //fis = new FileInputStream("/storage/emulated/0/data/test.jpg");
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fis == null) {
            Log.i("错误", "照片路径错误");
            return new byte[0];
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        int size = bitmap.getWidth() * bitmap.getHeight() * 4;
        //创建一个字节数组输出流,流的大小为size
        ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
        try {
            //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            //将字节数组输出流转化为字节数组byte[]
            byte[] imagedata = baos.toByteArray();
            return imagedata;
        } catch (Exception e) {
        } finally {
            try {
                bitmap.recycle();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    //读照片
    public byte[] readImage(Cursor cursor) {
        byte[] imgData = null;
        //将Blob数据转化为字节数组
        imgData = cursor.getBlob(cursor.getColumnIndex("NewsImage_blob"));
        return imgData;
    }
}
