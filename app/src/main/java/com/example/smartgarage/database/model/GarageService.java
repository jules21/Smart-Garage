package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class GarageService {

    private int garage_id;
    private int service_id;
    private String created_at;

    public GarageService(int garage_id, int service_id) {
        this.garage_id = garage_id;
        this.service_id = service_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getGarage_id() {
        return garage_id;
    }

    public void setGarage_id(int garage_id) {
        this.garage_id = garage_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public static class Model extends LocalDatabaseModel {

        private static final String TABLE_GARAGE_SERVICE = "garage_service";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // GARAGE SERVICE Table - column names

        private static final String KEY_GARAGE_ID = "garage_id";
        private static final String KEY_SERVICE_ID = "service_id";

        // GARAGE SERVICE table create statement
        private static final String CREATE_TABLE_GARAGE_SERVICE = "CREATE TABLE "
                + TABLE_GARAGE_SERVICE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_GARAGE_ID + " INTEGER," + KEY_SERVICE_ID + " INTEGER,"
                +KEY_CREATED_AT + " TEXT" + ")";


        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_GARAGE_SERVICE);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_GARAGE_SERVICE);
        }
    }

}
