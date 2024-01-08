import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

    public Cursor getAllNotes() {
        return database.query("notes", null, null, null, null, null, null);
    }

    // Add update and delete methods as needed
}
