package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        Button lugar = (Button) findViewById(R.id.lugar);
        lugar.setOnClickListener(this);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;
                        Intent intencion;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:

                                intencion= new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(intencion );
                                break;

                            case R.id.menu_seccion_2:
                                intencion= new Intent(getApplicationContext(),Instituciones.class);
                                startActivity(intencion );
                                break;

                            case R.id.menu_opcion_2:
                                intencion= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intencion );
                                break;

                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }
    @Override
    public void onClick(View v) {
        Intent intencion;
        if(v.getId() == R.id.Mapa)
            intencion = new Intent(getApplicationContext(), MapsActivity.class);
        else if(v.getId()==R.id.Camara)
            intencion = new Intent(getApplicationContext(), Camara.class);
        else
            intencion= new Intent(getApplicationContext(), Instituciones.class);
        startActivity(intencion);
    }
}
