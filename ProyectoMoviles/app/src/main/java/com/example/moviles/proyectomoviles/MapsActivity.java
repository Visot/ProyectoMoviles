package com.example.moviles.proyectomoviles;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener,AdapterView.OnItemSelectedListener{

    private static final int MY_PERMISSION_FINE_LOCATION = 101;
    private GoogleMap mMap;
    private Spinner cmbOpt;
    ArrayAdapter<CharSequence> adapter;
    private static final LatLng CTIC = new LatLng(-12.016858, -77.049769);
    private static final LatLng BIBLIOTECAFC = new LatLng(-12.017008, -77.0498252);
    private static final LatLng TIA_GRASA = new LatLng(-12.0173706,-77.0504637);
    private static final LatLng ESTADISTICA= new LatLng(-12.0172362,-77.0505866);
    private static final LatLng BC= new LatLng(-12.0180023,-77.0492364);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        cmbOpt = (Spinner) findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(this, R.array.opcionmap, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpt.setOnItemSelectedListener(this);
        cmbOpt.setAdapter(adapter);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button login = (Button) findViewById(R.id.camara);
        login.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(CTIC).title("CTIC").icon(BitmapDescriptorFactory.fromResource(R.drawable.minilogo)));
        mMap.addMarker(new MarkerOptions().position(BIBLIOTECAFC).title("Biblioteca FC").icon(BitmapDescriptorFactory.fromResource(R.drawable.minilibro)));
        mMap.addMarker(new MarkerOptions().position(BC).title("Biblioteca Central").icon(BitmapDescriptorFactory.fromResource(R.drawable.minilibro)));
        mMap.addMarker(new MarkerOptions().position(TIA_GRASA).title("Snack ciencias").icon(BitmapDescriptorFactory.fromResource(R.drawable.minicocina)));
        mMap.addMarker(new MarkerOptions().position(ESTADISTICA).title("Oficina de estadistica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CTIC));
        permiso(mMap);

    }
    public void permiso(GoogleMap mMap){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        } else {
            mMap.setMyLocationEnabled(true);

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions,@NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    Toast.makeText(getApplicationContext(), "Permisos Habilitados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Se necesita permisos de ubicacion", Toast.LENGTH_SHORT).show();
                }
            }break;


        }
    }

    @Override
    public void onClick(View v) {
        Intent intencion;
        intencion= new Intent(getApplicationContext(),Camara.class);
        startActivity(intencion );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (pos == 0) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        if (pos == 1) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        if (pos == 2) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
