package com.example.moviles.proyectomoviles;


import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdminSQLite extends SQLiteOpenHelper{

    public static final String TAG = AdminSQLite.class.getSimpleName();


    public static final String DB_name = "user.db";
    public static final int DB_version = 1;

    public static final String Users_table = "users";
    public static final String column_name = "names";
    public static final String column_lastname = "lastname";
    public static final String column_pass = "pass";
    public static final String column_email = "email";


//   Query para la tabla de usuarios
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + Users_table  + " ( "
            + column_name + " TEXT, "
            + column_lastname + " TEXT, "
            + column_email + " TEXT PRIMARY KEY, "
            + column_pass + " TEXT "
            + ");";

    public AdminSQLite(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte,
                          int versionNue) {
        db.execSQL("drop table if exists " + Users_table);
        onCreate(db);
    }

    public void addUser (String name, String lastname, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tupla = new ContentValues();
        tupla.put(column_name,name);
        tupla.put(column_lastname,lastname);
        tupla.put(column_email,email);
        tupla.put(column_pass,pass);

        long id = db.insert(Users_table,null, tupla);
        db.close();
        Log.d(TAG,"Usuario Registrado "+id);


    }

    public boolean getUser(String email, String pass){

        String selectQuery = "select * from" + Users_table + " where " +
                column_email + " = " + " '"+email+"' " + " and " + column_pass + " = " + " '"+pass+"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }
}