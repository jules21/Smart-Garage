package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;
public class Garage {

    private int id;
    private String name;
    private String address;
    private String created_at;


    public Garage(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class Model extends LocalDatabaseModel {

        private static final String TABLE_GARAGE = "garage";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // GARAGE Table - column names
        private static final String KEY_ADDRESS = "address";
        private static final String KEY_NAME = "name";

        // GARAGE table create statement
        private static final String CREATE_TABLE_GARAGE = "CREATE TABLE "
                + TABLE_GARAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT,"
                +KEY_CREATED_AT + " TEXT" + ")";


        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_GARAGE);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_GARAGE);
        }
    }
}
