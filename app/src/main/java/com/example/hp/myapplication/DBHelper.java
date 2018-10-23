package com.example.hp.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    DBHelper db;
    SQLiteDatabase sqLitedb;

    public DBHelper(Context context){
        super(context,"db_name",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users(id integer PRIMARY KEY AUTOINCREMENT,name VARCHAR(10))";
        db.execSQL(query);
    }

    public void openConnection(){
        sqLitedb = getWritableDatabase();
    }

    public void closeConnection(){
        sqLitedb.close();
    }

    public boolean executeQuery(String query){
        try {
            Log.d("asdd","SSSSSSSSSSSSSSSSSSS"+query);
            sqLitedb.execSQL(query);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Cursor selectData(String query){
        Log.d("aaa","aaaaaaaaaaaaaaaaaaaaaa"+query);
        Cursor cursor = sqLitedb.rawQuery(query,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
