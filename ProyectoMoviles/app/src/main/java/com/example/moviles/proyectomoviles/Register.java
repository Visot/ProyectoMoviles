package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button logUp;

    private EditText correoLogup;
    private EditText passLogup;
    private EditText nameLogup;
    private EditText repassLogup;
    private EditText lastnameLogup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        correoLogup =(EditText)findViewById(R.id.correoLogup);
        passLogup =(EditText)findViewById(R.id.passLogup);
        nameLogup =(EditText)findViewById(R.id.nameLogup);
        lastnameLogup =(EditText)findViewById(R.id.lastnameLogup);
        repassLogup =(EditText)findViewById(R.id.repassLogup);


        passLogup.addTextChangedListener(new PassValidator(passLogup) {
            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos
                if( text.length() < 8 )
                    passLogup.setError( "La contraseña es muy corta" );
            }
        });

        repassLogup.addTextChangedListener(new PassValidator(repassLogup) {
            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos
                if( !text.equals(passLogup.getText().toString()) )
                    repassLogup.setError( "La contraseña no es la misma" );
            }
        });

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

