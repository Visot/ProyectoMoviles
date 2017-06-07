package com.example.moviles.proyectomoviles.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviles.proyectomoviles.AdminSQLite;
import com.example.moviles.proyectomoviles.Camara;
import com.example.moviles.proyectomoviles.Main2Activity;
import com.example.moviles.proyectomoviles.MapsActivity;
import com.example.moviles.proyectomoviles.R;
import com.example.moviles.proyectomoviles.Sesion;


public class Configuraciones extends Fragment implements
        View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Sesion sesion;

    private AdminSQLite db;

    EditText contranueva;
    EditText contrarepetir;
    private OnFragmentInteractionListener mListener;

    public Configuraciones() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Opciones.
     */
    // TODO: Rename and change types and number of parameters
    public static Opciones newInstance(String param1, String param2) {
        Opciones fragment = new Opciones();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        //db = new AdminSQLite(this);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new AdminSQLite(getActivity().getApplicationContext());
        sesion = new Sesion(getActivity().getApplicationContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.configuraciones, container, false);
        contranueva = (EditText) vista.findViewById(R.id.Nueva);
        contrarepetir = (EditText) vista.findViewById(R.id.Repetida);
        Button cambiar = (Button) vista.findViewById(R.id.Cambiar);
        cambiar.setOnClickListener(this);
        Button menu = (Button) vista.findViewById(R.id.Menu);
        menu.setOnClickListener(this);
        return vista;
    }

    /*@Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.textView_help:
                switchFragment(HelpFragment.TAG);
                break;
            case R.id.textView_settings:
                switchFragment(SettingsFragment.TAG);
                break;
        }
    }*/
    private void CambiaFragment(Class C){
        Fragment fragment=null;
        Class fragmentClass=C;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, fragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.Menu)
            CambiaFragment(Opciones.class);
        else if(v.getId() == R.id.Cambiar)
        {
            String nueva=contranueva.getText().toString();
            String repetida=contrarepetir.getText().toString();
            if(nueva.equals(repetida)){
                //db.updateUserPass(nueva,sesion.getUserValues()[2]);
            }
            else
                Toast.makeText(getActivity().getApplicationContext(), "No son iguales", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
