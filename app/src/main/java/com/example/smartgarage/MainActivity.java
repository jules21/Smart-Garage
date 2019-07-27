package com.example.smartgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartgarage.UI.Technician;
import com.example.smartgarage.database.DatabaseController;
import com.example.smartgarage.database.model.Book;
import com.example.smartgarage.database.model.Technicial;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseController helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseController db = DatabaseController.getInstance(getApplicationContext());
        db.getWritableDatabase();


////        technitian test
//        Technicial technician1,technician2,technician3,technician4, technician;
//        technician1 = new Technicial("jules","jules21@gmail.com","0785126331", "secret","kigali Rwanda");
//        technician2 = new Technicial("fabien","fabien21@gmail.com","0785126331", "secret","kigali Rwanda");
//        technician3 = new Technicial("joseph","qwetyt@gmail.com","0785126331", "secret","kigali Rwanda");
//        technician4 = new Technicial("patrick","patrick@gmail.com","098980989","secret", "address",1);
//        technician2.save(db);
//        technician3.save(db);
//        technician4.save(db);
//        technician1.save(db);

        // Getting all Todos

        Log.d("Get Technicial", "Getting All Technicians");
        List<Technicial> technicials = Technicial.getAll(db);
        for (Technicial tech : technicials) {
            Log.d("Tech", tech.getNames() + "Email:" + tech.getEmail());
        }
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
