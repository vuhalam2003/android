package com.example.bai15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NAME = "tbllop";
    // Column names
    public static final String COLUMN_MA_LOP = "malop";
    public static final String COLUMN_TEN_LOP = "tenlop";
    public static final String COLUMN_SI_SO = "siso";

    // SQL statement to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_MA_LOP + " TEXT PRIMARY KEY, " +
                    COLUMN_TEN_LOP + " TEXT, " +
                    COLUMN_SI_SO + " INTEGER);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}