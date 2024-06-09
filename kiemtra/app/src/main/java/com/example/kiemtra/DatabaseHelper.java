package com.example.kiemtra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact_management.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DONVI = "DonVi";
    public static final String TABLE_NHANVIEN = "NhanVien";

    public static final String COLUMN_DONVI_ID = "id";
    public static final String COLUMN_DONVI_NAME = "name";
    public static final String COLUMN_DONVI_EMAIL = "email";
    public static final String COLUMN_DONVI_WEBSITE = "website";
    public static final String COLUMN_DONVI_LOGO = "logo";
    public static final String COLUMN_DONVI_ADDRESS = "address";
    public static final String COLUMN_DONVI_PHONE = "phone";
    public static final String COLUMN_DONVI_PARENT_UNIT_ID = "parent_unit_id";

    public static final String COLUMN_NHANVIEN_ID = "id";
    public static final String COLUMN_NHANVIEN_NAME = "name";
    public static final String COLUMN_NHANVIEN_POSITION = "position";
    public static final String COLUMN_NHANVIEN_EMAIL = "email";
    public static final String COLUMN_NHANVIEN_PHONE = "phone";
    public static final String COLUMN_NHANVIEN_AVATAR = "avatar";
    public static final String COLUMN_NHANVIEN_UNIT_ID = "unit_id";

    // Create table SQL statements
    private static final String CREATE_TABLE_DONVI = "CREATE TABLE " + TABLE_DONVI + " ("
            + COLUMN_DONVI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DONVI_NAME + " TEXT, "
            + COLUMN_DONVI_EMAIL + " TEXT, "
            + COLUMN_DONVI_WEBSITE + " TEXT, "
            + COLUMN_DONVI_LOGO + " TEXT, "
            + COLUMN_DONVI_ADDRESS + " TEXT, "
            + COLUMN_DONVI_PHONE + " TEXT, "
            + COLUMN_DONVI_PARENT_UNIT_ID + " INTEGER);";

    // Modify the CREATE_TABLE_NHANVIEN statement to include the avatar BLOB column
    private static final String CREATE_TABLE_NHANVIEN = "CREATE TABLE " + TABLE_NHANVIEN + " ("
            + COLUMN_NHANVIEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NHANVIEN_NAME + " TEXT, "
            + COLUMN_NHANVIEN_POSITION + " TEXT, "
            + COLUMN_NHANVIEN_EMAIL + " TEXT, "
            + COLUMN_NHANVIEN_PHONE + " TEXT, "
            + COLUMN_NHANVIEN_AVATAR + " BLOB, "  // Add the avatar BLOB column
            + COLUMN_NHANVIEN_UNIT_ID + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_NHANVIEN_UNIT_ID + ") REFERENCES " + TABLE_DONVI + "(" + COLUMN_DONVI_ID + "));";

    // Add this method to insert an employee with avatar image
    public long addNhanVien(String name, String position, String email, String phone, byte[] avatar, Integer unitId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NHANVIEN_NAME, name);
        values.put(COLUMN_NHANVIEN_POSITION, position);
        values.put(COLUMN_NHANVIEN_EMAIL, email);
        values.put(COLUMN_NHANVIEN_PHONE, phone);
        values.put(COLUMN_NHANVIEN_AVATAR, avatar); // Insert avatar as BLOB
        if (unitId != null) {
            values.put(COLUMN_NHANVIEN_UNIT_ID, unitId);
        }
        return db.insert(TABLE_NHANVIEN, null, values);
    }

    // Add this method to retrieve the avatar image from the database
    public byte[] getAvatarById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NHANVIEN, new String[]{COLUMN_NHANVIEN_AVATAR}, COLUMN_NHANVIEN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        byte[] avatar = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_NHANVIEN_AVATAR);
                if (columnIndex != -1) {
                    avatar = cursor.getBlob(columnIndex);
                }
            }
            cursor.close();
        }
        return avatar;
    }




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DONVI);
        db.execSQL(CREATE_TABLE_NHANVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONVI);
        onCreate(db);
    }

    public long addDonVi(String name, String email, String website, String logo, String address, String phone, Integer parentUnitId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DONVI_NAME, name);
        values.put(COLUMN_DONVI_EMAIL, email);
        values.put(COLUMN_DONVI_WEBSITE, website);
        values.put(COLUMN_DONVI_LOGO, logo);
        values.put(COLUMN_DONVI_ADDRESS, address);
        values.put(COLUMN_DONVI_PHONE, phone);
        if (parentUnitId != null) {
            values.put(COLUMN_DONVI_PARENT_UNIT_ID, parentUnitId);
        }
        return db.insert(TABLE_DONVI, null, values);
    }

    // Read
    public Cursor getDonViById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DONVI, null, COLUMN_DONVI_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    // Update
    public int updateDonVi(long id, String name, String email, String website, String logo, String address, String phone, Integer parentUnitId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DONVI_NAME, name);
        values.put(COLUMN_DONVI_EMAIL, email);
        values.put(COLUMN_DONVI_WEBSITE, website);
        values.put(COLUMN_DONVI_LOGO, logo);
        values.put(COLUMN_DONVI_ADDRESS, address);
        values.put(COLUMN_DONVI_PHONE, phone);
        if (parentUnitId != null) {
            values.put(COLUMN_DONVI_PARENT_UNIT_ID, parentUnitId);
        }
        return db.update(TABLE_DONVI, values, COLUMN_DONVI_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Delete
    public int deleteDonVi(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DONVI, COLUMN_DONVI_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Search by ID
    public Cursor searchDonViById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DONVI, null, COLUMN_DONVI_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    // Get all
    public Cursor getAllDonVi() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DONVI, null, null, null, null, null, null);
    }
}
