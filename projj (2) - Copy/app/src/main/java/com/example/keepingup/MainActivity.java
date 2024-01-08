package com.example.keepingup;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected static final String PREFS_NAME = "MyPrefsFile";
    private static final String INITIAL_PAGE_SHOWN_KEY = "initialPageShown";
    TextView NameText;
    Button btn;
    EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean initialPageShown = prefs.getBoolean(INITIAL_PAGE_SHOWN_KEY, false);

        if (!initialPageShown) {
            setContentView(R.layout.activity_main);
            NameText = findViewById(R.id.NameText);
            Name = findViewById(R.id.Name);
            btn = findViewById(R.id.btn);
        } else {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    public void buClick(View view) {
        String userName = Name.getText().toString();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(INITIAL_PAGE_SHOWN_KEY, true);
        editor.putString("USER_NAME", userName);
        editor.apply();
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
    }
}
