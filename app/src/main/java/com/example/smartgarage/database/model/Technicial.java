package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class Technicial {
    private int id;
    private String names;
    private String email;
    private String phone;
    private String password;
    private String address;
    private int garage_id;
    private String created_at;

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
                + KEY_NAMES + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_PASSWORD + "TEXT,"+ KEY_PHONE + "TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_GARAGE_ID + "INTEGER,"
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
