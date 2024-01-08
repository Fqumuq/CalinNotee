package com.example.keepingup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoListDAO {
    private SQLiteDatabase database;
    private AppDatabaseHelper dbHelper;

    public TodoListDAO(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertTodoItem(String itemText) {
        ContentValues values = new ContentValues();
        values.put("item", itemText);
        return database.insert("todo_lists", null, values);
    }

    public List<String> getAllTodoItems() {
        List<String> todoItemsList = new ArrayList<>();
        Cursor cursor = database.query("todo_lists", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String itemText = cursor.getString(cursor.getColumnIndex("item"));
                todoItemsList.add(itemText);
            }
            cursor.close();
        }
        return todoItemsList;
    }
    public void deleteAllTodoItems() {
        database.execSQL("DELETE FROM todo_lists;");
    }

}
