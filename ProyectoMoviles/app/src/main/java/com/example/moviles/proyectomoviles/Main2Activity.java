package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements
        View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navimain2);
        Button mapa = (Button) findViewById(R.id.Mapa);
        mapa.setOnClickListener(this);
        Button camara = (Button) findViewById(R.id.Camara);
        camara.setOnClickListener(this);



        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        Intent intencion;

                        if(menuItem.isChecked()) menuItem.setChecked(false);
                        else menuItem.setChecked(true);

                        drawerLayout.closeDrawers();


                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Toast.makeText(getApplicationContext(),"Home", Toast.LENGTH_SHORT).show();
                                Main2Fragment fragment = new Main2Fragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame,fragment);
                                fragmentTransaction.commit();
                                break;

                            case R.id.menu_seccion_2:
                                intencion= new Intent(getApplicationContext(),Instituciones.class);
                                startActivity(intencion );
                                break;

                            case R.id.menu_opcion_2:
                                intencion= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intencion );
                                break;
                            default:
                                return true;

                        }

                        return true;
                    }
                });
    }
    @Override
    public void onClick(View v) {
        Intent intencion;
        if(v.getId() == R.id.Mapa)
            intencion = new Intent(getApplicationContext(), MapsActivity.class);
        else
            intencion = new Intent(getApplicationContext(), Camara.class);
        startActivity(intencion);
    }
}
