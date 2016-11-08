package com.tecii.android.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dark Dragon on 01/11/2016.
 */
public class AdminSQLiteHepler extends SQLiteOpenHelper {
    public AdminSQLiteHepler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create table articulos(codigo int primary key, description text, precio real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
