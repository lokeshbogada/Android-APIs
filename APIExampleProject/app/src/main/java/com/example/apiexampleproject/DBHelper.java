package com.example.apiexampleproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME ="Example.db";
    public static final String TABLE_NAME ="Students_table";
    public static final String ID ="ID";
    public static final String NAME ="NAME";
    public static final String SUR_NAME ="SURNAME";
    public static final String MARKS ="MARKS";

    private static final String CREATE_RM_PRODUCTION_BUILD_STATUS_TABLE = "create table RM_PRODUCTION_BUILD_STATUS(build_status_id integer,build_status text)";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
      //  SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(CREATE_RM_PRODUCTION_BUILD_STATUS_TABLE);

        sqLiteDatabase.execSQL("create table " + DB_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
