package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        correoLogup.addTextChangedListener(new PassValidator(correoLogup) {
            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos


                if(!isCorreoValid())
                    correoLogup.setError( "No es correo valido" );
            }
        });


        logUp = (Button)findViewById(R.id.logup);
        logUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intencion;
        String nombre =nameLogup.getText().toString();
        String lastname =lastnameLogup.getText().toString();
        String correo =correoLogup.getText().toString();
        String password =passLogup.getText().toString();
        String repassword =repassLogup.getText().toString();
        boolean confPass =password.equals(repassword);
        boolean tamPass = password.length()<8;


        if(!nombre.equals("")&!lastname.equals("")&!correo.equals("")&!password.equals("")&!repassword.equals("")&confPass&!tamPass&isCorreoValid()){

            intencion= new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intencion);
        }


    }

    public boolean isCorreoValid(){

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(correoLogup.getText().toString());
        return matcher.matches();

    }


}

