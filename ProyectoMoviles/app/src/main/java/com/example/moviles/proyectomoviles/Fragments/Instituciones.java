package com.example.moviles.proyectomoviles.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviles.proyectomoviles.Camara;
import com.example.moviles.proyectomoviles.Fragments.Opciones;
import com.example.moviles.proyectomoviles.LugarAdapter;
import com.example.moviles.proyectomoviles.MapsActivity;
import com.example.moviles.proyectomoviles.R;
import com.example.moviles.proyectomoviles.lugar;

import java.util.ArrayList;
import java.util.List;


public class Instituciones extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List items;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Instituciones() {
        // Required empty public constructor
    }
    public static Opciones newInstance(String param1, String param2) {
        Opciones fragment = new Opciones();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        items = new ArrayList();
        items.add(new lugar(R.drawable.cticuni, getString(R.string.CTIC).toString(),getString(R.string.DESCTIC).toString() ));
        items.add(new lugar(R.drawable.cismid, getString(R.string.CISMID).toString(),getString(R.string.DESCISMID).toString()));
        items.add(new lugar(R.drawable.inictel, getString(R.string.INICTEL).toString(),getString(R.string.DESINICTEL).toString()));
        items.add(new lugar(R.drawable.imca, getString(R.string.IMCA).toString(),getString(R.string.DESIMCA).toString()));

        // Obtener el Recycler

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.activity_instituciones, container, false);
        recycler = (RecyclerView) vista.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        // Crear un nuevo adaptador
        adapter = new LugarAdapter(items);
        recycler.setAdapter(adapter);//inicia el llenado de los cardview
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituciones);*/
        return vista;
        /*
        * */
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
    @Override
    public void onClick(View v) {
        Intent intencion;
        if(v.getId() == R.id.Mapa)
            intencion = new Intent(getActivity(), MapsActivity.class);
        else
            intencion = new Intent(getActivity(), Camara.class);
        startActivity(intencion);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other layout.fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
