package com.example.smartgarage.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.smartgarage.database.model.Book;
import com.example.smartgarage.database.model.Booking;
import com.example.smartgarage.database.model.Garage;
import com.example.smartgarage.database.model.GarageService;
import com.example.smartgarage.database.model.Service;
import com.example.smartgarage.database.model.Specialist;
import com.example.smartgarage.database.model.SpecialistTech;
import com.example.smartgarage.database.model.Store;
import com.example.smartgarage.database.model.Technicial;
import com.example.smartgarage.database.model.User;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DatabaseController extends SQLiteOpenHelper {

    public static abstract class LocalDatabaseModel {

        public LocalDatabaseModel(){

        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

        }
        public abstract void onCreate(SQLiteDatabase database);
    }

    private SQLiteDatabase database;
    private int openConnections = 0;

    private static final String DATABASE = "smartgarage";
    private static final int VERSION = 1;
    private static DatabaseController instance = null;


    private DatabaseController(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    // Add you LocalDatabaseModels here.
    private final LocalDatabaseModel[] models = new LocalDatabaseModel[]
            {
                    new Book.Model(),
                    new Garage.Model(),
                    new Booking.Model(),
                    new GarageService.Model(),
                    new Store.Model(),
                    new Service.Model(),
                    new User.Model(),
                    new Technicial.Model(),
                    new SpecialistTech.Model(),
                    new Specialist.Model()
            };


    public synchronized static DatabaseController getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseController(context.getApplicationContext());
        }
        return instance;
    }



    /**
     * Must be called from the same thread as the original openDatabase call.
     */
    @Override
    public synchronized void close() {
        if(database == null || openConnections == 0){
            throw new IllegalStateException("Database already closed or has never been opened.");
        }
        openConnections--;
        if(openConnections != 0){
            return;
        }
        database = null;
        super.close();
    }

    /**
     * Do not manually call this method! Use openDatabase(), database() and close()!
     *
     * Opens the SQLiteDatabase if not already opened.
     * This implementation does the exact same thing as getWritableDatabase and thus will return a writable database.
     *
     * @return the newly opened database or the existing database.
     */
    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return getWritableDatabase();
    }

    /**
     *
     * Do not manually call this method! Use openDatabase(), database() and close()!
     *
     * Opens the SQLiteDatabase if not already opened.
     *
     * @return the newly opened database or the existing database.
     */
    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if(database == null){
            database = super.getWritableDatabase();
        }
        openConnections++;
        return database;
    }

    /**
     * Open the database. Always pair this call with close() and use database() to get the opened database!
     */
    public synchronized void openDatabase(){
        getWritableDatabase();
    }

    /**
     * Returns the opened database. Throws an exception if the database has not been opened yet!
     * @return the database.
     */
    public synchronized SQLiteDatabase database(){
        if(database == null){
            throw new IllegalStateException("Database has not been opened yet!");
        }
        return database;
    }

    @Override
    public synchronized void onCreate(SQLiteDatabase db) {
        setForeignKeyConstraintsEnabled(db);
        for(LocalDatabaseModel model: models){
            model.onCreate(db);
        }
    }

    @Override
    public synchronized void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        setForeignKeyConstraintsEnabled(db);
        for(LocalDatabaseModel model: models){
            model.onUpgrade(db, oldVersion, newVersion);
        }
    }

    @Override
    public synchronized void onOpen(SQLiteDatabase db) {
        setForeignKeyConstraintsEnabled(db);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public synchronized void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db){
        //Skip for Android 4.1 and newer as this is already handled in onConfigure
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && !db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    /* I often have some utility methods in this class too. */
    public long getCount(String table){
        return DatabaseUtils.queryNumEntries(database(), table);
    }
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}