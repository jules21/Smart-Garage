package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class Store {

    private int id;
    private String name;
    private String address;
    private String created_at;

    public Store(String name, String address) {
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


        private static final String TABLE_STORE = "store";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // STORE Table - column names
        //BOTH NAME AND ADDRESS DEFINED ABOVE
        private static final String KEY_ADDRESS = "address";
        private static final String KEY_NAME = "name";

        // STORE table create statement
        private static final String CREATE_TABLE_STORE = "CREATE TABLE "
                + TABLE_STORE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT,"
                +KEY_CREATED_AT + " TEXT" + ")";


        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_STORE);
        }
    }
}
