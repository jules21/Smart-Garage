package com.example.smartgarage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MActivity extends AppCompatActivity {

    // url to fetch contacts json
    private static final String URL = "https://api.androidhive.info/json/contacts.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
    }


}