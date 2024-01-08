package com.example.keepingup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteCreation extends AppCompatActivity {
    EditText etTitle, etNote;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);
        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String note = etNote.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("note", note);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void buClick(View view) {
        int buSelected = view.getId();
        if (buSelected == R.id.btnBack) {
            Intent intent = new Intent(this, NotesPage.class);
            startActivity(intent);
        }
    }
}