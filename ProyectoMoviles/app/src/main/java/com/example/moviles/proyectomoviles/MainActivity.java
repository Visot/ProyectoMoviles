package com.example.moviles.proyectomoviles;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;


import com.example.moviles.proyectomoviles.Fragments.Login;
import com.example.moviles.proyectomoviles.Fragments.Register;
import com.example.moviles.proyectomoviles.Fragments.Configuraciones;
import com.example.moviles.proyectomoviles.Fragments.Mapa;
import com.example.moviles.proyectomoviles.Fragments.Opciones;
import com.example.moviles.proyectomoviles.Fragments.Pestanas;
import com.example.moviles.proyectomoviles.Fragments.Main2Activity;

public class MainActivity extends AppCompatActivity implements Main2Activity.OnFragmentInteractionListener, Login.OnFragmentInteractionListener, Register.OnFragmentInteractionListener, Configuraciones.OnFragmentInteractionListener,Opciones.OnFragmentInteractionListener, Mapa.OnFragmentInteractionListener, Pestanas.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = Login.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(getFragmentManager().getBackStackEntryCount()>1)
            getFragmentManager().popBackStack();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}