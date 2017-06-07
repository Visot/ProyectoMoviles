package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView navList;

    private EditText correoLogin;
    private EditText passLogin;
    private Button login;
    private Button register;
    private AdminSQLite db;
    private Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new AdminSQLite(this);
        sesion = new Sesion(this);

        correoLogin =(EditText)findViewById(R.id.mailLogin);
        passLogin =(EditText)findViewById(R.id.passLogin);

        login =(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(sesion.loggedIn()){
            intent = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(intent);
            finish();
        }

//        correoLogin.addTextChangedListener(new PassValidator(correoLogin) {
//            @Override
//            public void validate(EditText editText, String text) {
//                //Implementamos la validación que queramos
//
//
//                if(!isCorreoValid())
//                    correoLogin.setError( "No es correo valido" );
//            }
//        });



    }





    @Override
    public void onClick(View v) {
        Intent intencion;


        switch(v.getId()){
            case R.id.login:
                if (login()){
                    intencion= new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intencion );
                }
                break;

            case R.id.register:
                intencion= new Intent(getApplicationContext(), Register.class);
                startActivity(intencion );
                break;
            default:
                break;
        }

    }

    public boolean login(){
        Intent intencion;
        String correo =correoLogin.getText().toString();
        String password=passLogin.getText().toString();


        if(db.getUser(correo,password)){
            sesion.setLoggedIn(true);
            return true;
        }else {
            Toast.makeText(getApplicationContext(), "Datos Erroneos",Toast.LENGTH_SHORT).show();
            return  false;
        }

    }

}
