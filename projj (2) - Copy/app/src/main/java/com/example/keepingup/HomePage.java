package com.example.keepingup;

import static com.example.keepingup.MainActivity.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    Button btnNotes, btnLists, btnDiaries, btnSafe;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString("USER_NAME", "");
        if (userName.isEmpty()) {
            userName = getIntent().getStringExtra("USER_NAME");
        }
        name = findViewById(R.id.Name);
        name.setText("Hello, " + userName);

        btnNotes = findViewById(R.id.btnNotes);
        btnLists = findViewById(R.id.btnLists);
        btnDiaries = findViewById(R.id.btnDiaries);
        btnSafe= findViewById(R.id.btnSafe);
    }

    public void buClick(View view) {
        int buSelected = view.getId();

        if (buSelected == R.id.btnLists) {
            Intent intent = new Intent(this, ListsPage.class);
            startActivity(intent);
        } else if (buSelected == R.id.btnNotes) {
            Intent intent = new Intent(this, NotesPage.class);
            startActivity(intent);
        } else if (buSelected == R.id.btnDiaries){
            Intent intent = new Intent(this,CalendarPage.class);
            startActivity(intent);
        } else if (buSelected == R.id.btnSafe){
            Intent intent = new Intent(this,AddImagePage.class);
            startActivity(intent);
        }
    }
}
