package com.example.moviles.proyectomoviles;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nromesa integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte,
                          int versionNue) {
        db.execSQL("drop table if exists votantes");
        db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nromesa integer)");
    }
}