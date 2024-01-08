package com.example.keepingup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalendarNote extends AppCompatActivity {
    EditText Note;
    TextView txtNote;
    TextView txt;
    String selectedDate;
    CalendarNotesDAO calendarNotesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_note);

        Note = findViewById(R.id.Note);
        txtNote = findViewById(R.id.txtNote);
        txt = findViewById(R.id.txt);

        calendarNotesDAO = new CalendarNotesDAO(this);
        calendarNotesDAO.open();

        // Receive the selected date from CalendarPage
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("selectedDate");
        txtNote.setText("Add note for " + selectedDate);

        // Load existing note (if any) for the selected date
        String existingNote = calendarNotesDAO.getCalendarNote(selectedDate);
        if (existingNote != null) {
            txt.setText(selectedDate + ":\n" + existingNote);
        }
    }

    public void buClick(View view) {
        int buSelected = view.getId();  // Get the ID of the clicked button

        if (buSelected == R.id.btnBack) {
            finish(); // Go back to the previous activity
        } else if (buSelected == R.id.btnSave) {
            // Save the note for the selected date
            String note = Note.getText().toString();
            calendarNotesDAO.addCalendarNote(selectedDate, note);

            // Update the TextView with the entered note
            txt.setText(selectedDate + ":\n" + note);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        calendarNotesDAO.close();  // Close the database connection when the activity is destroyed
    }
}
