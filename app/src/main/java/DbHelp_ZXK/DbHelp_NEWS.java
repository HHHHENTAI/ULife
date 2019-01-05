package DbHelp_ZXK;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelp_NEWS extends SQLiteOpenHelper {

    //数据库名称和版本号
    public static final String DB_NAME = "NEWS_INFO.db";
    public static final int VERSION = 1;

    public DbHelp_NEWS(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //定义一条创建表的语句并建表
//        SendId	Integer	主键，自动增长	数据ID
//        NewsId	Integer	非空	发布新闻ID
//        SendusrPhone	Text	非空	发布者账号
//        NewsTitle	Text	非空	新闻标题
//        NewsContent	Text	非空	新闻内容
//        NewsClass	Text	非空	新闻分类
//        NewsImage	Integer  非空	新闻图片
//        NewsHot	Integer	非空	新闻热度
//        NewsTime	Text	非空	发布时间

        String CREATE_SQL_NewsInfo = "create table NewsInfo(" +
                "SendId_int integer primary key autoincrement," +
                "NewsId_int integer not null," +
                "SendusrPhone_text text not null," +
                "NewsTitle_text text not null," +
                "NewsContent_text text not null," +
                "NewsClass_text text not null," +
                "NewsImage_blob text not null," +
                "NewsHot_int integer not null," +
                "NewsTime_text text not null)";
        db.execSQL(CREATE_SQL_NewsInfo);

//        SeeId	Integer	自动增长	标识ID
//        SeePhone	Text	非空	浏览账号
//        NewsTitle_text	text	新闻ID
        //NewsContent_text  text    新闻内容
        //NewsTime_text     text    新闻时间
        String CREATE_SQL_SeeHistory = "create table SeeHistory(" +
                "SeeId_int integer primary key autoincrement," +
                "SeePhone_text text not null," +
                "NewsTitle_text text," +
                "NewsContent_text text," +
                "NewsTime_text text not null)";
        db.execSQL(CREATE_SQL_SeeHistory);

//        PersonPhone	Text	主键	个人账号
//        PersonImage	Integer	非空	个人头像
//        PersonSig	Text		个人签名
//        PersonName	Text	个人昵称
//        PersonSex	Text	非空	个人性别
//        PersonBirth	Text		个人生日
//        PeronJob	Text		个人职业
//        PersonSchool	Text		个人学校
//        PersonLocation	Text		个人所在地
//        PersonHome	Text		个人故乡
//        PersonOffice	Text	个人邮箱
//        PersonShow	Text		个人说明

        String CREATE_SQL_PersonInfo = "create table PersonInfo(" +
                "PersonPhone_text text primary key," +
                "PersonImage_blob text," +
                "PersonName_text text," +
                "PersonSig_text text," +
                "PersonBirth_text text," +
                "PersonSex_text text," +
                "PersonSchool_text text," +
                "PersonHome_text text," +
                "PersonOffice_text text," +
                "PersonShow_text text," +
                "PeronJob_text text," +
                "PersonLocation_text text)";


        db.execSQL(CREATE_SQL_PersonInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
