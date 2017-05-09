package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button logUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        logUp = (Button)findViewById(R.id.logup);
        logUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intencion;
        intencion= new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intencion);
    }


}
