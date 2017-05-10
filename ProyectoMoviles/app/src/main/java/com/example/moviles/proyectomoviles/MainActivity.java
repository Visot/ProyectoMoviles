package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView navList;

    private EditText correoLogin;
    private EditText passLogin;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correoLogin =(EditText)findViewById(R.id.mailLogin);
        passLogin =(EditText)findViewById(R.id.passLogin);

        login =(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);


        correoLogin.addTextChangedListener(new PassValidator(correoLogin) {
            @Override
            public void validate(EditText editText, String text) {
                //Implementamos la validación que queramos


                if(!isCorreoValid())
                    correoLogin.setError( "No es correo valido" );
            }
        });

        /*this.navList = (ListView) findViewById(R.id.left_drawer);

        // Load an array of options names
        final String[] names = getResources().getStringArray(
                R.array.nav_options);

        // Set previous array as adapter of the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        navList.setAdapter(adapter);*/
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
        String nombre=correoLogin.getText().toString();
        String password=passLogin.getText().toString();


        if(v.getId() == R.id.login) {
            intencion2= new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intencion2 );
        }else if(v.getId() == R.id.register){
            intencion2= new Intent(getApplicationContext(), Register.class);
            startActivity(intencion2 );
        }
    }

    public boolean validarLogueo(String c, String p){

        return true;

    }

    public boolean isCorreoValid(){

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(correoLogin.getText().toString());
        return matcher.matches();

    }
}
