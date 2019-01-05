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


//    public byte[] readImage() {
//        Cursor cur = database_news.query("NewsInfo", new String[]{"_id", "avatar"}, null, null, null, null, null);
//        byte[] imgData = null;
//        if (cur.moveToNext()) {
//            //将Blob数据转化为字节数组
//            imgData = cur.getBlob(cur.getColumnIndex("avatar"));
//        }
//        return imgData;
//    }


    //TODO start咨询表的增删改查
    public void insert_newsinfo(int NewsId_int, String SendusrPhone_text, String NewsTitle_text, String NewsContent_text, String NewsClass_text, String NewsImage_blob, int NewsHot_int, String NewsTime_text) {
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

    public void update_newsinfo(int NewsId_oldint, int NewsId_newint, String SendusrPhone_text, String NewsTitle_text, String NewsContent_text,
                                String NewsClass_text, String NewsImage_blob, int NewsHot_int, String NewsTime_text) {
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

    public void update_news_count(String title, String time, int NewsHot_int) {
        ContentValues values_update = new ContentValues();
        values_update.put("NewsHot_int", NewsHot_int + 1);
        database_news.update("NewsInfo", values_update, "NewsTitle_text = ? and NewsTime_text = ?", new String[]{title, time});
    }

    public Cursor query_newsinfo(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return database_news.query("NewsInfo", columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    //TODO end咨询表的增删改查

    //TODO start浏览历史表的增删改查

    public void insert_historyinfo(String SeePhone_text, String PublishPhone_text, String NewsTitle_text, String NewsContent_text, String NewsClass_text,
                                   String NewsImage_blob, Integer NewsHot_int, String NewsTime_text) {
        //使用 ContentValues 来对要添加的数据进行组装
        ContentValues values_insert = new ContentValues();
        values_insert.put("SeePhone_text", SeePhone_text);
        values_insert.put("PublishPhone_text", PublishPhone_text);
        values_insert.put("NewsTitle_text", NewsTitle_text);
        values_insert.put("NewsContent_text", NewsContent_text);
        values_insert.put("NewsClass_text", NewsClass_text);
        values_insert.put("NewsImage_blob", NewsImage_blob);
        values_insert.put("NewsHot_int", NewsHot_int);
        values_insert.put("NewsTime_text", NewsTime_text);
        database_news.insert("SeeHistory", null, values_insert);
    }

    public void delete_historyinfo(String SeePhone_text, String NewsTitle_text, String NewsTime_text) {
        database_news.delete("SeeHistory ", "SeePhone_text = ? and NewsTitle_text=? and NewsTime_text=?", new String[]{SeePhone_text, NewsTitle_text, NewsTime_text});
    }

//    public void update_historyinfo(int SeeId_oldint, int SeeId_newint, String SeePhone_text, int SeeNewsID_int) {
//        ContentValues values_update = new ContentValues();
//        values_update.put("SeeId_int", SeeId_newint);
//        values_update.put("SeePhone_text", SeePhone_text);
//        values_update.put("SeeNewsID_int", SeeNewsID_int);
//        database_news.update("SeeHistory", values_update, "SeeId_int=?", new String[]{SeeId_oldint + ""});
//    }

    public Cursor query_historyinfo(String SeePhone_text) {

        return database_news.query("SeeHistory", null, "SeePhone_text = ?", new String[]{SeePhone_text}, null, null, null);
    }
    //TODO end浏览历史表的增删改查

    //TODO start个人信息表的增删改查
//    String CREATE_SQL_PersonInfo="create table PersonInfo" +
//            "(PersonPhone_text text primary key," +
//            "PersonImage_blob BLOB not null," +
//            "PersonSig_text text," +
//            "PersonName_text text not null," +
//            "PersonSex_text text not null," +
//            "PersonBirth_text text," +
//            "PeronJob_text text," +
//            "PersonSchool_text text," +
//            "PersonLocation_text text," +
//            "PersonHome_text text," +
//            "PersonOffice_text text not null," +
//            "PersonShow_text text," +
//            "PersonPip_text text)";

    public void insert_personinfo(String PersonPhone_text, String PersonImage_blob, String PersonName_text, String PersonSig_text, String PersonBirth_text,
                                  String PersonSex_text, String PersonSchool_text, String PersonHome_text, String PersonOffice_text, String PersonShow_text,
                                  String PeronJob_text, String PersonLocation_text) {
        ContentValues values_insert = new ContentValues();
        values_insert.put("PersonPhone_text", PersonPhone_text);
        values_insert.put("PersonImage_blob", PersonImage_blob);
        values_insert.put("PersonSig_text", PersonSig_text);
        values_insert.put("PersonName_text", PersonName_text);
        values_insert.put("PersonSex_text", PersonSex_text);
        values_insert.put("PersonBirth_text", PersonBirth_text);
        values_insert.put("PeronJob_text", PeronJob_text);
        values_insert.put("PersonSchool_text", PersonSchool_text);
        values_insert.put("PersonLocation_text", PersonLocation_text);
        values_insert.put("PersonHome_text", PersonHome_text);
        values_insert.put("PersonOffice_text", PersonOffice_text);
        values_insert.put("PersonShow_text", PersonShow_text);
        database_news.insert("PersonInfo", null, values_insert);
    }

    public void delete_personinfo(String delete) {
        database_news.delete("PersonInfo ", "PersonPhone_text=?", new String[]{delete});
    }

    public void update_personinfo(String PersonPhone_text, String PersonName_text, String PersonSig_text, String PersonBirth_text,
                                  String PersonSex_text, String PersonSchool_text, String PersonHome_text, String PersonOffice_text,
                                  String PersonShow_text, String PeronJob_text, String PersonLocation_text) {
        ContentValues values_update = new ContentValues();
        values_update.put("PersonPhone_text", PersonPhone_text);
        values_update.put("PersonSig_text", PersonSig_text);
        values_update.put("PersonName_text", PersonName_text);
        values_update.put("PersonSex_text", PersonSex_text);
        values_update.put("PersonBirth_text", PersonBirth_text);
        values_update.put("PeronJob_text", PeronJob_text);
        values_update.put("PersonSchool_text", PersonSchool_text);
        values_update.put("PersonLocation_text", PersonLocation_text);
        values_update.put("PersonHome_text", PersonHome_text);
        values_update.put("PersonOffice_text", PersonOffice_text);
        values_update.put("PersonShow_text", PersonShow_text);
        database_news.update("PersonInfo", values_update, "PersonPhone_text = ?", new String[]{PersonPhone_text});
    }

    public void update_personinfo_personimage(String PersonPhone_text, String PersonImage_blob) {
        ContentValues values_update = new ContentValues();
        values_update.put("PersonImage_blob", PersonImage_blob);
        database_news.update("PersonInfo", values_update, "PersonPhone_text = ?", new String[]{PersonPhone_text});
    }

    public Cursor query_personinfo(String PersonPhone_text) {
        return database_news.query("PersonInfo", null, "PersonPhone_text = ?", new String[]{PersonPhone_text}, null, null, null);
    }
    //TODO end个人信息表的增删改查

}
