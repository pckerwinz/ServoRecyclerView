package com.example.servorecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText et_booktitle_id,et_bookauthor_id,et_bookpages_id;
    Button btn_add_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_booktitle_id = findViewById(R.id.et_booktitle_id);
        et_bookauthor_id = findViewById(R.id.et_bookauthor_id);
        et_bookpages_id = findViewById(R.id.et_bookpages_id);
        btn_add_id = findViewById(R.id.btn_add_id);

        btn_add_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(et_booktitle_id.getText().toString().trim(),
                et_bookauthor_id.getText().toString().trim(),
                Integer.parseInt(et_bookpages_id.getText().toString().trim()));

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}