package com.example.keepingup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class CalendarPage extends AppCompatActivity {
    Button btnBack;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        btnBack = findViewById(R.id.btnBack);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Pass the selected date to the CalendarNote activity
                Intent intent = new Intent(CalendarPage.this, CalendarNote.class);
                intent.putExtra("selectedDate", dayOfMonth + "/" + (month + 1) + "/" + year);
                startActivity(intent);
            }
        });
    }
    public void buClick(View view){
        int buSelected = view.getId();  // Get the ID of the clicked button
        if (buSelected == R.id.btnBack) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }
}