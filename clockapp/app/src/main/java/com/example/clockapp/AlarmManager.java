package com.example.clockapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AlarmManager {

    private SQLiteDatabase db;
    private AlarmDatabaseHelper dbHelper;

    public AlarmManager(Context context) {
        dbHelper = new AlarmDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarms = new ArrayList<>();
        Cursor cursor = db.query("alarms", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Alarm alarm = new Alarm();
            alarm.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            alarm.setHour(cursor.getInt(cursor.getColumnIndexOrThrow("hour")));
            alarm.setMinute(cursor.getInt(cursor.getColumnIndexOrThrow("minute")));
            alarm.setLabel(cursor.getString(cursor.getColumnIndexOrThrow("label")));
            alarm.setSnoozeEnabled(cursor.getInt(cursor.getColumnIndexOrThrow("snooze")) == 1);
            alarm.setEnabled(cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) == 1);
            alarms.add(alarm);
        }
        cursor.close();
        return alarms;
    }

    public Alarm getAlarm(int id) {
        Cursor cursor = db.query("alarms", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Alarm alarm = new Alarm();
            alarm.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            alarm.setHour(cursor.getInt(cursor.getColumnIndexOrThrow("hour")));
            alarm.setMinute(cursor.getInt(cursor.getColumnIndexOrThrow("minute")));
            alarm.setLabel(cursor.getString(cursor.getColumnIndexOrThrow("label")));
            alarm.setSnoozeEnabled(cursor.getInt(cursor.getColumnIndexOrThrow("snooze_enabled")) == 1);
            alarm.setEnabled(cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) == 1);
            cursor.close();
            return alarm;
        }
        return null;
    }

    public void addAlarm(Alarm alarm) {
        ContentValues values = new ContentValues();
        values.put("hour", alarm.getHour());
        values.put("minute", alarm.getMinute());
        values.put("label", alarm.getLabel());
        values.put("snooze_enabled", alarm.isSnoozeEnabled() ? 1 : 0);
        values.put("enabled", alarm.isEnabled() ? 1 : 0);
        db.insert("alarms", null, values);
    }

    public void updateAlarm(Alarm alarm) {
        ContentValues values = new ContentValues();
        values.put("hour", alarm.getHour());
        values.put("minute", alarm.getMinute());
        values.put("label", alarm.getLabel());
        values.put("snooze_enabled", alarm.isSnoozeEnabled() ? 1 : 0);
        values.put("enabled", alarm.isEnabled() ? 1 : 0);
        db.update("alarms", values, "id=?", new String[]{String.valueOf(alarm.getId())});
    }

    public void deleteAlarm(int id) {
        db.delete("alarms", "id=?", new String[]{String.valueOf(id)});
    }
}
