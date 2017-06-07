package com.example.moviles.proyectomoviles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by broman on 06/06/17.
 */

public class Sesion {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public Sesion(Context context){
        this.context = context;
        prefs = context.getSharedPreferences("myapp",context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean login){
        editor.putBoolean("logInMode",login);
        editor.commit();
    }

    public boolean loggedIn(){
        return prefs.getBoolean("logInMode", false);
    }
}
