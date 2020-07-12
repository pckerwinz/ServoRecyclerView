package com.example.servorecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText book_id_txt,et_booktitle_id_update, et_bookauthor_id_update, et_bookpages_id_update;
    Button btn_update_id, btn_delete_id;
    String id, title, author, pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        et_booktitle_id_update = findViewById(R.id.et_booktitle_id_update);
        et_bookauthor_id_update = findViewById(R.id.et_bookauthor_id_update);
        et_bookpages_id_update = findViewById(R.id.et_bookpages_id_update);
        btn_update_id = findViewById(R.id.btn_update_id);
        btn_delete_id = findViewById(R.id.btn_delete_id);

        getAndSetIntentData();

        //set actionbar
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle(title);

        btn_update_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = et_booktitle_id_update.getText().toString().trim();
                author = et_bookauthor_id_update.getText().toString().trim();
                pages = et_bookpages_id_update.getText().toString().trim();

                myDB.updateData(id, title, author, pages);
            }
        });

        btn_delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    public void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")
            && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            // getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //setting data
            et_booktitle_id_update.setText(title);
            et_bookauthor_id_update.setText(author);
            et_bookpages_id_update.setText(pages);
        }
        else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }


}