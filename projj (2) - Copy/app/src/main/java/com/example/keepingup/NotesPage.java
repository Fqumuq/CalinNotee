package com.example.keepingup;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class NotesPage extends AppCompatActivity {
    Button btnBack, btnAdd, btnDelete;
    ListView listView;
    ArrayList<String> notesList;
    ArrayAdapter<String> adapter;
    NotesDAO notesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_page);
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);
        notesDAO = new NotesDAO(this);
        notesDAO.open();
        notesList = (ArrayList<String>) notesDAO.getAllNotesAsList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesPage.this, NoteCreation.class);
                startActivityForResult(intent, 1);
            }
        });
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllNotes();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String note = data.getStringExtra("note");
            notesDAO.insertNote(title, note);
            notesList.clear();
            notesList.addAll(notesDAO.getAllNotesAsList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notesDAO.close();
    }
    private void deleteAllNotes() {
        notesDAO.deleteAllNotes();
        notesList.clear();
        adapter.notifyDataSetChanged();
    }

    public void buClick(View view) {
        int buSelected = view.getId();
        if (buSelected == R.id.btnBack) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }
}
