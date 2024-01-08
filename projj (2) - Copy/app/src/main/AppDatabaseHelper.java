import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_database";
    private static final int DATABASE_VERSION = 1;

    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables for notes, to-do lists, and diaries
        createNotesTable(db);
        createTodoListsTable(db);
        createDiariesTable(db);
        // Add more tables as needed
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
}
