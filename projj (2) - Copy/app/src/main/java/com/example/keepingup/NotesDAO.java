package com.example.keepingup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotesDAO {
    private SQLiteDatabase database;
    private AppDatabaseHelper dbHelper;
    public NotesDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public long insertNote(String title, String content) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        return database.insert("notes", null, values);
    }

    public List<String> getAllNotesAsList() {
        List<String> notesList = new ArrayList<>();
        Cursor cursor = database.query("notes", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String fullNote = title + ": " + content;
                notesList.add(fullNote);
            }
            cursor.close();
        }
        return notesList;
    }
    public void deleteAllNotes() {
        database.execSQL("DELETE FROM notes;");
    }
}
