package com.example.showsqlitedatabasevalues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_name = "Student.db";
    public static final String TABLE_NAME = "Student_table";
    public static final String col1 = "ID";
    public static final String col2 = "NAME";
    public static final String col3 = "SURNAME";
    public static final String col4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, Database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table  " +TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table if exists " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertdata (String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result ==-1)
            return false;
        else
            return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from " +TABLE_NAME,null );
        return res;


    }
}

