package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class Specialist {

    private int id;
    private String name;
    private String description;
    private String created_at;

    public Specialist(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class Model extends LocalDatabaseModel {


        private static final String TABLE_SPECIALIST = "specialist";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // SPECIALIST Table - column names
        private static final String KEY_NAME = "name";
        private static final String KEY_DESCRIPTION = "description";

        // SPECIALIST table create statement
        private static final String CREATE_TABLE_SPECIALIST = "CREATE TABLE "
                + TABLE_SPECIALIST + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT,"
                +KEY_CREATED_AT + " TEXT" + ")";


        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIALIST);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_SPECIALIST);
        }
    }
}
