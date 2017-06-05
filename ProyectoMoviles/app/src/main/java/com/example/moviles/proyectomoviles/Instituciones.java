package com.example.moviles.proyectomoviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Instituciones extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituciones);
        List items = new ArrayList();
        items.add(new lugar(R.drawable.cticuni, getString(R.string.CTIC).toString(),getString(R.string.DESCTIC).toString() ));
        items.add(new lugar(R.drawable.cismid, getString(R.string.CISMID).toString(),getString(R.string.DESCISMID).toString()));
        items.add(new lugar(R.drawable.inictel, getString(R.string.INICTEL).toString(),getString(R.string.DESINICTEL).toString()));
        items.add(new lugar(R.drawable.imca, getString(R.string.IMCA).toString(),getString(R.string.DESIMCA).toString()));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        // Crear un nuevo adaptador
        adapter = new LugarAdapter(items);
        recycler.setAdapter(adapter);//inicia el llenado de los cardview
    }
}
