package DbHelp_ZXK;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelp_NEWS extends SQLiteOpenHelper {

    //数据库名称和版本号
    public static final String DB_NAME = "NEWS_INFO.db";
    public static final int VERSION = 1;

    public DbHelp_NEWS(@Nullable Context context)
    {
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

        String CREATE_SQL_NewsInfo = "create table NewsInfo(SendId_int integer primary key autoincrement," +
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
//        SeeNewsID	Integer	主键	新闻ID
        String CREATE_SQL_SeeHistory = "create table SeeHistory(SeeId_int integer primary key autoincrement," +
                "SeePhone_text text not null," +
                "SeeNewsID_int integer not null)";
        db.execSQL(CREATE_SQL_SeeHistory);

//        InfoId	Integer	自动增长	标识ID//删除这个了
//        PersonPhone	Text	主键	个人账号
//        PersonImage	Integer	非空	个人头像
//        PersonSig	Text		个人签名
//        PersonName	Text	非空	个人昵称
//        PersonSex	Text	非空	个人性别
//        PersonBirth	Text		个人生日
//        PeronJob	Text		个人职业
//        PersonSchool	Text		个人学校
//        PersonLocation	Text		个人所在地
//        PersonHome	Text		个人故乡
//        PersonOffice	Text	非空	个人邮箱
//        PersonShow	Text		个人说明
//        PersonPip	Text		个人名片
        String CREATE_SQL_PersonInfo = "create table PersonInfo" +
                "(PersonPhone_text text primary key," +
                "PersonImage_blob MEDIUMBLOB not null," +
                "PersonSig_text text," +
                "PersonName_text text not null," +
                "PersonSex_text text not null," +
                "PersonBirth_text text," +
                "PeronJob_text text," +
                "PersonSchool_text text," +
                "PersonLocation_text text," +
                "PersonHome_text text," +
                "PersonOffice_text text not null," +
                "PersonShow_text text," +
                "PersonPip_text text)";
        db.execSQL(CREATE_SQL_PersonInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
