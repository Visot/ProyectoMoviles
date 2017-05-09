package com.example.moviles.proyectomoviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener( this);
        Button register=(Button)findViewById(R.id.register);
        register.setOnClickListener( this);
    }

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
