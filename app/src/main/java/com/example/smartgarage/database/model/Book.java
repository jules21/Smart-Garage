package com.example.smartgarage.database.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartgarage.database.DatabaseController;

import com.example.smartgarage.database.DatabaseController.LocalDatabaseModel;


public final class Book {

    private long id = -1;
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Book(String title, String description){
        this.title = title;
        this.description = description;
    }

    private Book(long id, String title){
        this.title = title;
        this.id = id;
    }




    public long save(DatabaseController db){
        //save or update the book, throw an exception on failure.
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TITLE", this.getTitle());
        values.put("DESCRIPTION", this.getDescription());

        // insert row
        long book_id = database.insert("BOOK", null, values);

        return book_id;
    }

    //More non static methods (getters, setters, database methods) here

//    public static Book getById(DatabaseController db, long id){
//        //Do select query and get an existing book from the database.
//    }

    //More static methods here

    public static class Model extends LocalDatabaseModel {

        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
            //Implement update logic for this model/table
        }
        @Override
        public void onCreate(SQLiteDatabase database){
            //Implement create logic for this model/table
            database.execSQL("CREATE TABLE BOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TITLE TEXT, "
                    + "DESCRIPTION TEXT);");
        }
    }
}
