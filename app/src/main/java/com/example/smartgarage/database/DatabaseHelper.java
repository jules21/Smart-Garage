package com.example.smartgarage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartgarage.R;
import com.example.smartgarage.database.model.Technicial;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Name
    public static String DATABASE_NAME = "smartgarage";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_SPECIALIST = "specialist";
    private static final String TABLE_GARAGE = "garage";
    private static final String TABLE_TECHNICIAL = "technicial";
    private static final String TABLE_TECHNICIAL_SPECIALIST = "technicial_specialist";
    private static final String TABLE_STORE = "store";
    private static final String TABLE_SERVICE = "service";
    private static final String TABLE_GARAGE_SERVICE = "garage_service";
    private static final String TABLE_BOOKING = "booking";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // USERS Table - column names
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    // Technicial Table - column names
        // email and password defined
    private static final String KEY_NAMES = "names";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_GARAGE_ID = "garage_id";

    // SPECIALIST Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

  // Technicial SPECIALIST Table - column names
    private static final String KEY_TECHNICIAL_ID = "technicial_id";
    private static final String KEY_SPECIALIST_ID = "specialist_id";


  // GARAGE Table - column names
    //BOTH NAME AND ADDRESS DEFINED ABOVE

    // STORE Table - column names
    //BOTH NAME AND ADDRESS DEFINED ABOVE
    //
    // SERVICE Table - column names
    //BOTH NAME AND DESCRIPTION DEFINED ABOVE

    // GARAGE SERVICE Table - column names
    //Garage id DEFINED ABOVE
    private static final String KEY_SERVICE_ID = "service_id";

    // BOOKING SERVICE Table - column names
    //BOTH Garage id AND service id DEFINED ABOVE
    private static final String KEY_USER_ID = "user_id";


    // Table Create Statements
    // User table create statement
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FIRSTNAME + " TEXT," + KEY_LASTNAME + " TEXT,"+ KEY_EMAIL + "TEXT,"+ KEY_PASSWORD + "TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // TECHNICIAL table create statement
    private static final String CREATE_TABLE_TECHNICIAL = "CREATE TABLE "
            + TABLE_TECHNICIAL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMES + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_PASSWORD + "TEXT,"+ KEY_PHONE + "TEXT,"
            + KEY_ADDRESS + " TEXT," + KEY_GARAGE_ID + "INTEGER,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // SPECIALIST table create statement
    private static final String CREATE_TABLE_SPECIALIST = "CREATE TABLE "
            + TABLE_SPECIALIST + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // TECHNICIAL SPECIALIST table create statement
    private static final String CREATE_TABLE_TECHNICIAL_SPECIALIST = "CREATE TABLE "
            + TABLE_TECHNICIAL_SPECIALIST + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TECHNICIAL_ID + " INTEGER," + KEY_SPECIALIST_ID + " INTEGER,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // GARAGE table create statement
    private static final String CREATE_TABLE_GARAGE = "CREATE TABLE "
            + TABLE_GARAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT,"
            +KEY_CREATED_AT + " DATETIME" + ")";

 // GARAGE table create statement
    private static final String CREATE_TABLE_STORE = "CREATE TABLE "
            + TABLE_STORE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // SERVICE table create statement
    private static final String CREATE_TABLE_SERVICE = "CREATE TABLE "
            + TABLE_SERVICE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // GARAGE SERVICE table create statement
    private static final String CREATE_TABLE_GARAGE_SERVICE = "CREATE TABLE "
            + TABLE_GARAGE_SERVICE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_GARAGE_ID + " INTEGER," + KEY_SERVICE_ID + " INTEGER,"
            +KEY_CREATED_AT + " DATETIME" + ")";

    // BOKKING SERVICE table create statement
    private static final String CREATE_TABLE_BOOKING = "CREATE TABLE "
            + TABLE_BOOKING + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_GARAGE_ID + " INTEGER," + KEY_SERVICE_ID + " INTEGER,"+ KEY_USER_ID + " INTEGER,"
            +KEY_CREATED_AT + " DATETIME" + ")";



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase(sqLiteDatabase, oldVersion, newVersion);
    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            // creating required tables
            db.execSQL(CREATE_TABLE_USERS);
            db.execSQL(CREATE_TABLE_TECHNICIAL);
            db.execSQL(CREATE_TABLE_SPECIALIST);
            db.execSQL(CREATE_TABLE_TECHNICIAL_SPECIALIST);
            db.execSQL(CREATE_TABLE_GARAGE);
            db.execSQL(CREATE_TABLE_STORE);
            db.execSQL(CREATE_TABLE_SERVICE);
            db.execSQL(CREATE_TABLE_GARAGE_SERVICE);
            db.execSQL(CREATE_TABLE_BOOKING);
        }
        if (oldVersion < 2) {
            // on upgrade drop older tables
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

            // create new tables
            onCreate(db);
        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
        /*
     * Creating a mechanician
     */
    public long createTechnician(Technicial technicial) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMES, technicial.getNames());
        values.put(KEY_EMAIL, technicial.getEmail());
        values.put(KEY_PHONE, technicial.getPhone());
        values.put(KEY_ADDRESS, technicial.getAddress());
        values.put(KEY_GARAGE_ID, technicial.getGarage_id());
        values.put(KEY_PASSWORD, technicial.getPassword());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long technicial_id = db.insert(TABLE_TECHNICIAL, null, values);

        return technicial_id;
    }
}
