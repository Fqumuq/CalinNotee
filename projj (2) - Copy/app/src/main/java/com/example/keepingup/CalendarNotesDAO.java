package com.example.keepingup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class CalendarNotesDAO {
    private SQLiteDatabase database;
    private AppDatabaseHelper dbHelper;
    public CalendarNotesDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public long addCalendarNote(String date, String note) {
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("entry", note);
        if (getCalendarNoteEntryCount(date) > 0) {
            String whereClause = "date=?";
            String[] whereArgs = {date};
            return database.update("diaries", values, whereClause, whereArgs);
        } else {
            return database.insert("diaries", null, values);
        }
    }

    public String getCalendarNote(String date) {
        String[] columns = {"entry"};
        String selection = "date=?";
        String[] selectionArgs = {date};
        Cursor cursor = database.query("diaries", columns, selection, selectionArgs, null, null, null);
        String note = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                note = cursor.getString(cursor.getColumnIndex("entry"));
            }
            cursor.close();
        }
        return note;
    }
    private int getCalendarNoteEntryCount(String date) {
        String[] columns = {"entry"};
        String selection = "date=?";
        String[] selectionArgs = {date};
        Cursor cursor = database.query("diaries", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
