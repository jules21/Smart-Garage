package com.example.smartgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartgarage.database.DatabaseController;
import com.example.smartgarage.database.model.Book;

public class MainActivity extends AppCompatActivity {

    DatabaseController helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseController db = DatabaseController.getInstance(getApplicationContext());
        db.getWritableDatabase();
        Book book = new Book("Alice in Wonderland", "hi");
        book.save(db);
        db.close();
//
        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();

    }
    public void testDB(View v){

        DatabaseController helper = DatabaseController.getInstance(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Toast.makeText(this,
                    "Database available",Toast.LENGTH_SHORT).show();
//Code to read data from the database
        } catch(SQLiteException e) {
            Toast.makeText(this,
                    "Database unavailable",Toast.LENGTH_SHORT).show();
        }
    }

}
