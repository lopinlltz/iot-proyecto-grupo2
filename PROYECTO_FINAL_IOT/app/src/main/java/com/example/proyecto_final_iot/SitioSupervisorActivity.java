package com.example.proyecto_final_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SitioSupervisorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SitioSupervisorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supervisor_sitio_lista);

        recyclerView = findViewById(R.id.recycler_view_sitio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<SitioData> dataList = new ArrayList<>();
        dataList.add(new SitioData("Sitio 1", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 2", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 3", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 4", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 5", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 6", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 7", "UBICACIÓN"));
        dataList.add(new SitioData("Sitio 8", "UBICACIÓN"));

        adapter = new SitioSupervisorAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}
