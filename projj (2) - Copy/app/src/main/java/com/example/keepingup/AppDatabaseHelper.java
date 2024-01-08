package com.example.keepingup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_IMAGES = "images";
    private static final String COLUMN_IMAGE_PATH = "imagePath";

    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("AppDatabaseHelper", "onCreate: Creating tables");
        createNotesTable(db);
        createTodoListsTable(db);
        createDiariesTable(db);
        createImagesTable(db);
    }

    private void createNotesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT);");
    }
    private void createTodoListsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todo_lists (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, completed INTEGER);");
    }
    private void createDiariesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE diaries (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, entry TEXT);");
    }
    private void createImagesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IMAGE_PATH + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
