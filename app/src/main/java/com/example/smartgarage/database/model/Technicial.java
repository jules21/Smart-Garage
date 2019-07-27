package com.example.smartgarage.database.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.smartgarage.database.DatabaseController;
import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

import java.util.ArrayList;
import java.util.List;

public class Technicial {
    private int id;
    private String names;
    private String email;
    private String phone;
    private String password;
    private String address;
    private int garage_id;
    private String created_at;

    public Technicial() {
    }

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    public Technicial(String names, String email, String phone, String password, String address) {
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    public Technicial(String names, String email, String phone, String password, String address, int garage_id) {
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.garage_id = garage_id;
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

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGarage_id() {
        return garage_id;
    }

    public void setGarage_id(int garage_id) {
        this.garage_id = garage_id;
    }

    //    crud operation

    //    insert record
    public long save(DatabaseController db){
        //save or update the book, throw an exception on failure.
        SQLiteDatabase database = db.getWritableDatabase();

        Log.e(LOG, "create meachanician account");

        ContentValues values = new ContentValues();
        values.put(Model.KEY_NAMES, this.getNames());
        values.put(Model.KEY_EMAIL, this.getEmail());
        values.put(Model.KEY_PHONE, this.getPhone());
        values.put(Model.KEY_PASSWORD, this.getPassword());
        values.put(Model.KEY_ADDRESS, this.getAddress());
        values.put(Model.KEY_GARAGE_ID, this.getGarage_id());
        values.put(Model.KEY_CREATED_AT, db.getDateTime());

        // insert row
        long technician_id = database.insert(Model.TABLE_TECHNICIAL, null, values);

        return technician_id;
    }

    /*
     * get single Technician
     */
    public Technicial single(DatabaseController helper, long technician_id) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Model.TABLE_TECHNICIAL + " WHERE "
                + Model.KEY_ID + " = " + technician_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();


        this.setId(c.getInt(c.getColumnIndex(Model.KEY_ID)));
        this.setNames(c.getString(c.getColumnIndex(Model.KEY_NAMES)));//
        this.setEmail(c.getString(c.getColumnIndex(Model.KEY_EMAIL)));
        this.setPhone(c.getString(c.getColumnIndex(Model.KEY_PASSWORD)));
        this.setPassword(c.getString(c.getColumnIndex(Model.KEY_PASSWORD)));
        this.setAddress(c.getString(c.getColumnIndex(Model.KEY_ADDRESS)));
        this.setGarage_id(c.getInt(c.getColumnIndex(Model.KEY_GARAGE_ID)));

        this.setCreated_at(c.getString(c.getColumnIndex(Model.KEY_CREATED_AT)));

        return this;
    }

    /*
     * getting all Technicians
     * */

    public static List<Technicial> getAll(DatabaseController helper) {

        List<Technicial> technicians = new ArrayList<Technicial>();
        String selectQuery = "SELECT  * FROM " + Model.TABLE_TECHNICIAL;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        Technicial tech = new Technicial();
        if (c.moveToFirst()) {
            do {
                tech.setId(c.getInt(c.getColumnIndex(Model.KEY_ID)));
                tech.setNames(c.getString(c.getColumnIndex(Model.KEY_NAMES)));//
                tech.setEmail(c.getString(c.getColumnIndex(Model.KEY_EMAIL)));
                tech.setPhone(c.getString(c.getColumnIndex(Model.KEY_PASSWORD)));
                tech.setPassword(c.getString(c.getColumnIndex(Model.KEY_PASSWORD)));
                tech.setAddress(c.getString(c.getColumnIndex(Model.KEY_ADDRESS)));
                tech.setGarage_id(c.getInt(c.getColumnIndex(Model.KEY_GARAGE_ID)));
                tech.setCreated_at(c.getString(c.getColumnIndex(Model.KEY_CREATED_AT)));

                // adding to todo list
                technicians.add(tech);
            } while (c.moveToNext());
        }

        return technicians;
    }
    /*
     * Updating a Technician
     */
    public int update(DatabaseController helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Model.KEY_NAMES, this.getNames());
        values.put(Model.KEY_EMAIL, this.getEmail());
        values.put(Model.KEY_PHONE, this.getPhone());
        values.put(Model.KEY_PASSWORD, this.getPassword());
        values.put(Model.KEY_ADDRESS, this.getAddress());
        values.put(Model.KEY_GARAGE_ID, this.getGarage_id());
        values.put(Model.KEY_CREATED_AT, helper.getDateTime());

        // updating row
        return db.update(Model.TABLE_TECHNICIAL, values, Model.KEY_ID + " = ?",
                new String[] { String.valueOf(this.getId()) });
    }

    /*
     * Deleting a Technician
     */
    public void delete(DatabaseController helper, long technician_id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(Model.TABLE_TECHNICIAL, Model.KEY_ID + " = ?",
                new String[] { String.valueOf(technician_id) });
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */

    public boolean checkUser(DatabaseController helper, String email) {

        // array of columns to fetch
        String[] columns = {
                Model.KEY_EMAIL
        };
        SQLiteDatabase db = helper.getReadableDatabase();

        // selection criteria
        String selection = Model.KEY_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(Model.TABLE_TECHNICIAL, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(DatabaseController helper, String email, String password) {

        // array of columns to fetch
        String[] columns = {
                Model.KEY_ID
        };
        SQLiteDatabase db = helper.getReadableDatabase();
        // selection criteria
        String selection = Model.KEY_EMAIL + " = ?" + " AND " + Model.KEY_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(Model.TABLE_TECHNICIAL, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public static class Model extends LocalDatabaseModel {


        private static final String TABLE_TECHNICIAL = "technicial";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // Technicial Table - column names

        private static final String KEY_EMAIL = "email";
        private static final String KEY_PASSWORD = "password";
        private static final String KEY_NAMES = "names";
        private static final String KEY_PHONE = "phone";
        private static final String KEY_ADDRESS = "address";
        private static final String KEY_GARAGE_ID = "garage_id";

        // TECHNICIAL table create statement
        private static final String CREATE_TABLE_TECHNICIAL = "CREATE TABLE "
                + TABLE_TECHNICIAL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAMES + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_PASSWORD + " TEXT,"+ KEY_PHONE + " TEXT,"
                + KEY_ADDRESS + " TEXT,"+ KEY_GARAGE_ID + " INTEGER,"
                +KEY_CREATED_AT + " TEXT" + ")";

        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNICIAL);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_TECHNICIAL);
        }
    }
}
