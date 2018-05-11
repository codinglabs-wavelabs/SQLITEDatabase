package com.example.s.sqlitedatabase;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DataBaseVersion = 1;
    private static final String Database_name = "Database";
    private static final String Table_name = "PersonLogin";


    private static final String col_name = "Name";
    private static final String col_email = "Email";
    private static final String col_pass = "Password";


    public DataBaseHandler(Context context) {
        super(context, Database_name, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + Table_name + "("
                + col_name + " varchar(50)," + col_email + " varchar(50),"
                + col_pass + " varchar(10)" + ")";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public long insertData(String name, String email, String pass) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, name);
        contentValues.put(col_email, email);
        contentValues.put(col_pass, pass);
        return db.insert(Table_name, null, contentValues);

    }

    public String retrieveData(String uName) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String dataRetrieval = "SELECT * FROM " + Table_name;
        String password = "Not Found";

        Cursor cursor = sqLiteDatabase.rawQuery(dataRetrieval, null);
        if (cursor.moveToFirst()) {
            do {
                if (uName.equals(cursor.getString(0))) {
                    password = cursor.getString(2);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return password;
    }
}