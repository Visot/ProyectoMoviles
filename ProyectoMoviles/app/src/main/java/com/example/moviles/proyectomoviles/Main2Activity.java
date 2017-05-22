package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements
        View.OnClickListener {

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
