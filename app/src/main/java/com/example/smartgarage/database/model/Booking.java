package com.example.smartgarage.database.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController;
import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class Booking {

    private int id;
    private int garage_id;
    private int service_id;
    private int user_id;
    private String description;
    private String created_at;

    private static final String TABLE_BOOKING = "booking";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    private static final String KEY_SERVICE_ID = "service_id";
    private static final String KEY_GARAGE_ID = "garage_id";
    private static final String KEY_USER_ID = "user_id";


    public Booking(int garage_id, int service_id, int user_id, String description) {
        this.garage_id = garage_id;
        this.service_id = service_id;
        this.user_id = user_id;
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public long save(DatabaseController db){
//        //save or update the book, throw an exception on failure.
//        SQLiteDatabase database = db.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("TITLE", this.getTitle());
//        values.put("DESCRIPTION", this.getDescription());
//
//        // insert row
//        long book_id = database.insert("BOOK", null, values);
//
//        return book_id;
//    }

//    crud
//    public long save(DatabaseController db){
//        //save or update the book, throw an exception on failure.
//        SQLiteDatabase database = db.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_USER_ID, this.getUser_id());
//        values.put(KEY_GARAGE_ID, this.getGarage_id());
//        values.put(KEY_SERVICE_ID, this.getService_id());
//        values.put(KEY_CREATED_AT, db.getDateTime());
//
//
//        // insert row
//        long booking_id = database.insert(TABLE_BOOKING, null, values);
//
//        return booking_id;
//    }

    public static class Model extends LocalDatabaseModel {

        // BOKKING SERVICE table create statement
        private static final String CREATE_TABLE_BOOKING = "CREATE TABLE "
                + TABLE_BOOKING + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_GARAGE_ID + " INTEGER," + KEY_SERVICE_ID + " INTEGER,"+ KEY_USER_ID + " INTEGER,"
                +KEY_CREATED_AT + " TEXT" + ")";

        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            database.execSQL(CREATE_TABLE_BOOKING);
        }
    }

}
