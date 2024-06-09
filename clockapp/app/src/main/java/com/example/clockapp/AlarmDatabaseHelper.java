package com.example.clockapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarms.db";
    private static final int DATABASE_VERSION = 1;

    public AlarmDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE alarms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hour INTEGER, " +
                "minute INTEGER, " +
                "label TEXT, " +
                "snooze_enabled INTEGER, " +
                "enabled INTEGER, " +
                "repeat INTEGER, " + // Add repeat column
                "sound TEXT)"; // Add sound column
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        onCreate(db);
    }
}
