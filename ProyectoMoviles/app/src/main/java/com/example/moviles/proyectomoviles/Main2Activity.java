package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviles.proyectomoviles.Fragments.Configuraciones;
import com.example.moviles.proyectomoviles.Fragments.Mapa;
import com.example.moviles.proyectomoviles.Fragments.Opciones;
import com.example.moviles.proyectomoviles.Fragments.Pestanas;

public class Main2Activity extends AppCompatActivity implements Configuraciones.OnFragmentInteractionListener,Opciones.OnFragmentInteractionListener, Mapa.OnFragmentInteractionListener, Pestanas.OnFragmentInteractionListener{

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    private Cursor fila;
    private Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navimain3);
        /*Button mapa = (Button) findViewById(R.id.Mapa);
        mapa.setOnClickListener(this);
        Button camara = (Button) findViewById(R.id.Camara);
        camara.setOnClickListener(this);*/



//        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
//        //String dni = et1.getText().toString();
//        /*Cursor fila = bd.rawQuery( "select nombre,colegio,nromesa from votantes where dni=" + dni, null);
//        if (fila.moveToFirst()) {
//            et2.setText(fila.getString(0));
//            et3.setText(fila.getString(1));
//            et4.setText(fila.getString(2));
//        } else
//            Toast.makeText(this, "No existe persona",
//                    Toast.LENGTH_SHORT).show();*/
//        bd.close();




        sesion = new Sesion(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.setOnMenuItemClickListener();
        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = Opciones.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hacer algo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        // Establecemos el actionbarToggle al drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // Llamamos a la funcion syncState para que se muestre nuestro icono del menu.
        actionBarDrawerToggle.syncState();

        if (navView != null) {
            setupNavigation(navView);
        }
    }

    private void setupNavigation(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        Intent intencion;

                        if (menuItem.isChecked()) menuItem.setChecked(false);
                        else menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                                CambiaFragment(Opciones.class);
                                break;

                            case R.id.menu_seccion_2:
                                Toast.makeText(getApplicationContext(), "Lugares", Toast.LENGTH_SHORT).show();
                                //intencion = new Intent(getApplicationContext(), Pestanas.class);
                                //startActivity(intencion);
                                CambiaFragment(Pestanas.class);
                                break;

                            case R.id.menu_opcion_2:
                                sesion.setLoggedIn(false);
                                finish();
                                intencion = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intencion);
                                return true;

                        }
/*                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                        drawerLayout.closeDrawer(GravityCompat.START);
*/

                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
        getProfile();
    }

    private void getProfile() {
        //Para obtener los datos previamente guardados
        // simplemente empleamos el método getString()
        // del objeto SharedPreferences
        View hView =  navView.getHeaderView(0);
        TextView usuario = (TextView)hView.findViewById(R.id.Usuario0);
        TextView correo=(TextView)hView.findViewById(R.id.Correo0);
        String[] datostmp;
        datostmp=sesion.getUserValues();
        usuario.setText(datostmp[0]+" "+datostmp[1]);
        correo.setText(datostmp[2]);
    }


    private void CambiaFragment(Class C){
        Fragment fragment=null;
        Class fragmentClass=C;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Intent intencion;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Configuracion", Toast.LENGTH_SHORT).show();
            CambiaFragment(Configuraciones.class);

            return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(getFragmentManager().getBackStackEntryCount()>1)
            getFragmentManager().popBackStack();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}