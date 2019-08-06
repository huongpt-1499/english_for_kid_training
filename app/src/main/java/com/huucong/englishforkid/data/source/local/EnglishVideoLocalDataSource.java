package com.huucong.englishforkid.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.data.source.EnglishVideoDataSource;
import com.huucong.englishforkid.data.source.local.config.EnglishVideoDbHelper;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;


public class EnglishVideoLocalDataSource implements EnglishVideoDataSource.LocalDataSource {
    private static EnglishVideoLocalDataSource instance;
    private EnglishVideoDbHelper mDbHelper;

    public EnglishVideoLocalDataSource(EnglishVideoDbHelper englishVideoDbHelper) {
        this.mDbHelper = englishVideoDbHelper;
    }

    public static synchronized EnglishVideoLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new EnglishVideoLocalDataSource(EnglishVideoDbHelper.getInstance(context));
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    public List<EnglishVideo> getEnglishVideos(final String category) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        final String selection =
            EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_CATEGORY + " LIKE ?";
        final String[] selectionArgs = {category};
        Cursor cursor = database.query(EnglishVideoDbHelper.EnglishVideoEntry.TABLE_NAME,
            null, selection, selectionArgs, null, null, null);
        List<EnglishVideo> englishVideos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                EnglishVideo englishVideo = new EnglishVideo();
                englishVideo.setTitle(cursor.getString(
                    cursor.getColumnIndex(
                        EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_TITLE)));
                englishVideo.setThumbnail(cursor.getString(
                    cursor.getColumnIndex(
                        EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_THUMBNAIL)));
                englishVideo.setCategory(cursor.getString(
                    cursor.getColumnIndex(
                        EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_CATEGORY)));
                englishVideo.setLink(cursor.getString(
                    cursor.getColumnIndex(
                        EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_LINK)));
                englishVideo.setData(cursor.getString(
                    cursor.getColumnIndex(
                        EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_DATA)));
                englishVideos.add(englishVideo);
            } while (cursor.moveToNext());
        }
        return englishVideos;
    }

    @Override
    public boolean insertEnglishVideo(@NonNull final EnglishVideo englishVideo) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_TITLE,
            englishVideo.getTitle());
        contentValues.put(EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_THUMBNAIL,
            englishVideo.getThumbnail());
        contentValues.put(EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_CATEGORY,
            englishVideo.getCategory());
        contentValues.put(EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_DATA,
            englishVideo.getData());
        contentValues.put(EnglishVideoDbHelper.EnglishVideoEntry.COLUMN_NAME_LINK,
            englishVideo.getLink());
        long value = database.insert(EnglishVideoDbHelper.EnglishVideoEntry.TABLE_NAME,
            null, contentValues);
        database.close();
        return value != -1;
    }

    @Override
    public boolean deleteEnglishVideo(@NonNull final String id) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        String whereClause = EnglishVideoDbHelper.EnglishVideoEntry._ID +
            " LIKE ?";
        String[] whereArgs = { id };
        int value = database.delete(EnglishVideoDbHelper.EnglishVideoEntry.TABLE_NAME, whereClause,
            whereArgs);
        database.close();
        return value > 0;
    }
}
