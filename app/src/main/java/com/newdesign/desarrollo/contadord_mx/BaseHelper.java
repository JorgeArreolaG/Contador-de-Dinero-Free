package com.newdesign.desarrollo.contadord_mx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseHelper extends SQLiteOpenHelper {
    String tabla="CREATE TABLE BITACORAS(ID INTEGER PRIMARY KEY, TOTAL TEXT, B1000 TEXT, B500 TEXT, B200 TEXT, B100 TEXT, B50 TEXT, B20 TEXT, M20 TEXT, M10 TEXT, M5 TEXT, M2 TEXT, M1 TEXT, M05 TEXT )";
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table bitacoras");
        db.execSQL(tabla);
    }
}
