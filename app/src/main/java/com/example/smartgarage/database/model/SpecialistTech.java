package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class SpecialistTech {
    private int technicial_id;
    private int specialist_id;
    private String created_at;

    public SpecialistTech(int technicial_id, int specialist_id) {
        this.technicial_id = technicial_id;
        this.specialist_id = specialist_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTechnicial_id() {
        return technicial_id;
    }

    public void setTechnicial_id(int technicial_id) {
        this.technicial_id = technicial_id;
    }

    public int getSpecialist_id() {
        return specialist_id;
    }

    public void setSpecialist_id(int specialist_id) {
        this.specialist_id = specialist_id;
    }

    public static class Model extends LocalDatabaseModel {


        private static final String TABLE_TECHNICIAL_SPECIALIST = "technicial_specialist";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // Technicial SPECIALIST Table - column names
        private static final String KEY_TECHNICIAL_ID = "technicial_id";
        private static final String KEY_SPECIALIST_ID = "specialist_id";

        // TECHNICIAL SPECIALIST table create statement
        private static final String CREATE_TABLE_TECHNICIAL_SPECIALIST = "CREATE TABLE "
                + TABLE_TECHNICIAL_SPECIALIST + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TECHNICIAL_ID + " INTEGER," + KEY_SPECIALIST_ID + " INTEGER,"
                +KEY_CREATED_AT + " TEXT" + ")";


        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNICIAL_SPECIALIST);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_TECHNICIAL_SPECIALIST);
        }
    }
}
