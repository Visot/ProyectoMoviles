package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView navList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);

        Button register=(Button)findViewById(R.id.register);
        register.setOnClickListener(this);

        this.navList = (ListView) findViewById(R.id.left_drawer);

        // Load an array of options names
        final String[] names = getResources().getStringArray(
                R.array.nav_options);

        // Set previous array as adapter of the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        navList.setAdapter(adapter);
    }



    /*
    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }*/

    @Override
    public void onClick(View v) {
        Intent intencion2;
        if(v.getId() == R.id.login) {
            intencion2= new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intencion2 );
        }else if(v.getId() == R.id.register){
            intencion2= new Intent(getApplicationContext(), Register.class);
            startActivity(intencion2 );
        }
    }
}
