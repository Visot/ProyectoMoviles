package com.example.moviles.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button logUp;
    private Button logIn;
    private Button tmp;

    private EditText correoLogup;
    private EditText passLogup;
    private EditText nameLogup;
    private EditText repassLogup;
    private EditText lastnameLogup;
    private AdminSQLite db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new AdminSQLite(this);

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

//        repassLogup.addTextChangedListener(new PassValidator(repassLogup) {
//            @Override
//            public void validate(EditText editText, String text) {
//                //Implementamos la validación que queramos
//                if( !text.equals(passLogup.getText().toString()) )
//                    repassLogup.setError( "La contraseña no es la misma" );
//            }
//        });

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

        tmp = (Button)findViewById(R.id.tmp);
        tmp.setOnClickListener(this);

        logIn = (Button)findViewById(R.id.login);
        logIn.setOnClickListener(this);
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


        switch(v.getId()){

            case R.id.logup :
                registrarUsuario();
                break;

            case R.id.login:
                intencion= new Intent(getApplicationContext(), MainActivity
                        .class);
                startActivity(intencion);
                break;

            case R.id.tmp:
                intencion= new Intent(getApplicationContext(), pestanas.class);
                startActivity(intencion);

                break;

            default:
                break;

        }


    }


    public void registrarUsuario(){
        Intent intencion;
        String name = nameLogup.getText().toString();
        String lastname = lastnameLogup.getText().toString();
        String email = correoLogup.getText().toString();
        String password =passLogup.getText().toString();
        String repassword =repassLogup.getText().toString();

        boolean confPass =password.equals(repassword);
        boolean tamPass = password.length()<8;

        if(!name.isEmpty()&& !email.isEmpty() && !lastname.isEmpty() && !password.isEmpty() && !repassword.isEmpty() && confPass && !tamPass && isCorreoValid()){
            db.addUser(name,lastname,email,password);
            Toast.makeText(getApplicationContext(),"Usuario Registrado",Toast.LENGTH_SHORT).show();
            intencion= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intencion );
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Complete los datos",Toast.LENGTH_SHORT).show();
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

