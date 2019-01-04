package DbHelp_ZXK;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.hhhhentai.ulife.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;

public class Database_News {
    private DbHelp_NEWS dbHelp_news;
    private SQLiteDatabase database_news;


    public Database_News(DbHelp_NEWS dbHelp_news) {
        this.dbHelp_news = dbHelp_news;
        this.database_news = dbHelp_news.getWritableDatabase();
    }


    public byte[] readImage() {
        Cursor cur = database_news.query("NewsInfo", new String[]{"_id", "avatar"}, null, null, null, null, null);
        byte[] imgData = null;
        if (cur.moveToNext()) {
            //将Blob数据转化为字节数组
            imgData = cur.getBlob(cur.getColumnIndex("avatar"));
        }
        return imgData;
    }


    public void insert_newsinfo(int NewsId_int, String SendusrPhone_text, String NewsTitle_text, String NewsContent_text, String NewsClass_text, byte[] NewsImage_blob, int NewsHot_int, String NewsTime_text) {
        //使用 ContentValues 来对要添加的数据进行组装
        ContentValues values_insert = new ContentValues();
        values_insert.put("NewsId_int", NewsId_int);
        values_insert.put("SendusrPhone_text", SendusrPhone_text);
        values_insert.put("NewsTitle_text", NewsTitle_text);
        values_insert.put("NewsContent_text", NewsContent_text);
        values_insert.put("NewsClass_text", NewsClass_text);
        values_insert.put("NewsImage_blob", NewsImage_blob);
        values_insert.put("NewsHot_int", NewsHot_int);
        values_insert.put("NewsTime_text", NewsTime_text);
        database_news.insert("NewsInfo ", null, values_insert);
    }

    public void delete_newsinfo(String delete) {
        database_news.delete("NewsInfo ", "NewsId_int=?", new String[]{delete});
    }

    public void update_newsinfo(int NewsId_oldint, int NewsId_newint, String SendusrPhone_text, String NewsTitle_text, String NewsContent_text, String NewsClass_text, byte[] NewsImage_blob, int NewsHot_int, String NewsTime_text) {
        ContentValues values_update = new ContentValues();
        values_update.put("NewsId_int", NewsId_newint);
        values_update.put("SendusrPhone_text", SendusrPhone_text);
        values_update.put("NewsTitle_text", NewsTitle_text);
        values_update.put("NewsContent_text", NewsContent_text);
        values_update.put("NewsClass_text", NewsClass_text);
        values_update.put("NewsImage_blob", NewsImage_blob);
        values_update.put("NewsHot_int", NewsHot_int);
        values_update.put("NewsTime_text", NewsTime_text);
        //第二个参数是修改的字段及修改的值(已经存放到ContentValues中)
        //第三个参数是WHERE语句
        //第四个参数是WHERE语句中占位符的填充值
        //如果第三四个参数为null，那就将每条记录都改掉
        database_news.update("NewsInfo", values_update, "NewsId_int=?", new String[]{NewsId_oldint + ""});
    }
    public void update_news_count(String title,String time,int NewsHot_int)
    {
        ContentValues values_update = new ContentValues();
        values_update.put("NewsHot_int", NewsHot_int+1);
        database_news.update("NewsInfo", values_update, "NewsTitle_text = ? and NewsTime_text = ?", new String[]{title,time});
    }

    public Cursor query_newsinfo(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return database_news.query("NewsInfo", columns, selection, selectionArgs, groupBy, having, orderBy);
    }


    public void insert_historyinfo(int SeeId_int, String SeePhone_text, int SeeNewsID_int) {
        //使用 ContentValues 来对要添加的数据进行组装
        ContentValues values_insert = new ContentValues();
        values_insert.put("SeeId_int", SeeId_int);
        values_insert.put("SeePhone_text", SeePhone_text);
        values_insert.put("SeeNewsID_int", SeeNewsID_int);
        database_news.insert("SeeHistory", null, values_insert);
    }

    public void delete_historyinfo(String delete) {
        database_news.delete("SeeHistory ", "SeeId_int=?", new String[]{delete});
    }

    public void update_historyinfo(int SeeId_oldint, int SeeId_newint, String SeePhone_text, int SeeNewsID_int) {
        ContentValues values_update = new ContentValues();
        values_update.put("SeeId_int", SeeId_newint);
        values_update.put("SeePhone_text", SeePhone_text);
        values_update.put("SeeNewsID_int", SeeNewsID_int);
        database_news.update("SeeHistory", values_update, "SeeId_int=?", new String[]{SeeId_oldint + ""});
    }

    public Cursor query_historyinfo() {
        return database_news.query("SeeHistory", null, null, null, null, null, null);
    }

}
