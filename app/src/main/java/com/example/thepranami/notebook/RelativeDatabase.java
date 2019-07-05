package com.example.thepranami.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import static android.os.Build.ID;

public class RelativeDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Shadi_Register";
    public static final String TABLE_NAME = "Relative_Record";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "AMOUNT";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "ADDRESS";
    public static final String COL_5 = "OTHER";

    public RelativeDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT INTEGER, NAME TEXT, ADDRESS TEXT, OTHER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //// for insertion
    public boolean insertData(Integer amount, String name, String address, String other) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, amount);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, address);
        contentValues.put(COL_5, other);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
// getData
    public android.database.Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        android.database.Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    /// for updation
    public boolean updateData(String id, Integer amount, String name, String address, String other) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, amount);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, address);
        contentValues.put(COL_5, other);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    // for deletion
    public Integer deleteData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    // for autoId
    public android.database.Cursor AutoId(){
      SQLiteDatabase database = this.getWritableDatabase();
            String query = "select ID from TABLE_NAME order by ID desc limit 1";
        android.database.Cursor cursor = database.rawQuery(query,null);
           return cursor;
    }

    // for search id is found or not
    public android.database.Cursor check() {
        SQLiteDatabase database = this.getReadableDatabase();
        String s = "select ID from Relative_Record where ID";
        android.database.Cursor cr = database.rawQuery(s, null);
        return cr;

    }
    //total

/*    c.moveToFirst();
do{
        c = db.rawQuery("select sum(amount) from transaction_table where category = Salary ;", null);
    }while(c.moveToNext());

    amount = c.getInt(0);
c.close();  */
}