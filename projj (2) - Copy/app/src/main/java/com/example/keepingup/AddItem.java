package com.example.keepingup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {
    EditText Item;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Item = findViewById(R.id.Item);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = Item.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("item", item);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
