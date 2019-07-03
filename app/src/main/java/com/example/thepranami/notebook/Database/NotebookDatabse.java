package com.example.thepranami.notebook.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotebookDatabse extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notebook_db";
    public static final String TABLE_NAME = "donor_tbl";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DONOR_ID";
    public static final String COL_3 = "AMOUNT";
    public static final String COL_4 = "NAME";
    public static final String COL_5 = "ADDRESS";
    public static final String COL_6 = "MOBILE";
    public static final String COL_7 = "OTHER";
    public NotebookDatabse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public NotebookDatabse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DONOR_ID INTEGER, AMOUNT INTEGER, NAME TEXT, ADDRESS TEXT, MOBILE TEXT, OTHER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //insert data
    public boolean insertData(Integer donor_id, Integer amount, String name, String address, String mobile, String other) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, donor_id);
        contentValues.put(COL_3, amount);
        contentValues.put(COL_4, name);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, mobile);
        contentValues.put(COL_7, other);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
