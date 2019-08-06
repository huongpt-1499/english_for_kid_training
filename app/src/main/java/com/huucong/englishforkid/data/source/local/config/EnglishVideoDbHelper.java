package com.huucong.englishforkid.data.source.local.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class EnglishVideoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EnglishForKid.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = "TEXT";
    private static final String INTEGER_TYPE = "INTEGER";
    private static final String SQL_CREATE_ENGLISH_VIDEO_ENTRIES = "CREATE TABLE "
        + EnglishVideoEntry.TABLE_NAME + " ( "
        + EnglishVideoEntry._ID + INTEGER_TYPE + " PRIMARY KEY, "
        + EnglishVideoEntry.COLUMN_NAME_TITLE + TEXT_TYPE + ", "
        + EnglishVideoEntry.COLUMN_NAME_THUMBNAIL + TEXT_TYPE + ", "
        + EnglishVideoEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE + ", "
        + EnglishVideoEntry.COLUMN_NAME_LINK + TEXT_TYPE + ", "
        + EnglishVideoEntry.COLUMN_NAME_DATA + TEXT_TYPE + ")";
    private static final String SQL_DELETE_TABLE_ENGLISH_VIDEO =
        "DROP TABLE IF EXISTS " + EnglishVideoEntry.TABLE_NAME;
    public static EnglishVideoDbHelper instance;

    public EnglishVideoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized EnglishVideoDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new EnglishVideoDbHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENGLISH_VIDEO_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE_ENGLISH_VIDEO);
        sqLiteDatabase.execSQL(SQL_CREATE_ENGLISH_VIDEO_ENTRIES);
    }

    public static final class EnglishVideoEntry implements BaseColumns {
        public static final String TABLE_NAME = "english_video";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_THUMBNAIL = "thumbnail";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_LINK = "link";
        public static final String COLUMN_NAME_DATA = "data";
    }
}
