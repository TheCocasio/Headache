package com.thecocasio.headache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseTable extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Headache.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "headache";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COUNTER = "headache_counter";

    public DatabaseTable(MainActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + "INTEGER PRIMARY KEY, " + COLUMN_COUNTER + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    void SetCounter(Integer n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COUNTER,n);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor ReadAllData(){
        String query = "SELECT " + COLUMN_COUNTER + " FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
