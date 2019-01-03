package com.example.hhhhentai.DbHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelp extends SQLiteOpenHelper {

    // 數據庫名稱
    public static final String DB_NAME = "my.db";
    public static final int VERSON = 1;

    public DbHelp(Context context) {
        super(context, DB_NAME, null, VERSON);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // 定義一條創建表的語句
        String CREATE_SQL = "create table user(_id integer primary key autoincrement,"
                + "account text,"
                + "password text,"
                + "name text,"
                + "phone text)";
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}

