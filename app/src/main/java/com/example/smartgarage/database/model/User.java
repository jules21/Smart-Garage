package com.example.smartgarage.database.model;

import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;

public class User {
   private int id;
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private String created_at;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class Model extends LocalDatabaseModel {


        private static final String TABLE_USER = "users";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_CREATED_AT = "created_at";

        // USERS Table - column names
        private static final String KEY_FIRSTNAME = "firstName";
        private static final String KEY_LASTNAME = "lastName";
        private static final String KEY_EMAIL = "email";
        private static final String KEY_PASSWORD = "password";

        // User table create statement
        private static final String CREATE_TABLE_USERS = "CREATE TABLE "
                + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FIRSTNAME + " TEXT," + KEY_LASTNAME + " TEXT,"+ KEY_EMAIL + " TEXT,"+ KEY_PASSWORD + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";

        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL(CREATE_TABLE_USERS);
        }
    }

}
