package com.example.keepingup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ListsPage extends AppCompatActivity {
    Button btnBack, btnAdd, btnDelete;
    ListView listView;
    ArrayAdapter<String> adapter;
    TodoListDAO todoListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_page);

        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);
        todoListDAO = new TodoListDAO(this);
        todoListDAO.open();
        loadTodoItems();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListsPage.this, AddItem.class);
                startActivityForResult(intent, 1);
            }
        });
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllTodoItems();
            }
        });
    }

    private void loadTodoItems() {
        List<String> todoItems = todoListDAO.getAllTodoItems();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, todoItems);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String itemText = data.getStringExtra("item");
            long itemId = todoListDAO.insertTodoItem(itemText);
            if (itemId != -1) {
                loadTodoItems();
            }
        }
    }

    public void buClick(View view) {
        if (view.getId() == R.id.btnBack) {
            todoListDAO.close();
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        todoListDAO.close();
        super.onDestroy();
    }
    private void deleteAllTodoItems() {
        todoListDAO.deleteAllTodoItems();
        loadTodoItems();
    }
}
