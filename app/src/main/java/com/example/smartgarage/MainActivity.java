package com.example.smartgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.View;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartgarage.UI.GarageActivity;
import com.example.smartgarage.UI.MechanicianActivity;
import com.example.smartgarage.UI.MechanicianList;
import com.example.smartgarage.UI.StoreActivity;
import com.example.smartgarage.database.DatabaseController;

public class MainActivity extends AppCompatActivity {

    DatabaseController helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findMe(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    public void getSpareparts(View view) {
        Intent i = new Intent(MainActivity.this, StoreActivity.class);
        startActivity(i);
    }

    public void getMechanician(View view) {
        Intent i = new Intent(MainActivity.this, MechanicianList.class);
        startActivity(i);
    }

    public void findGarage(View view) {
        Intent i = new Intent(MainActivity.this, GarageActivity.class);
        startActivity(i);
    }
}
