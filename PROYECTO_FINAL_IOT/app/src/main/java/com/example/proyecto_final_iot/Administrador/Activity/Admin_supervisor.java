package com.example.proyecto_final_iot.Administrador.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_iot.Administrador.Adapter.SupervisorListAdminAdapter;
import com.example.proyecto_final_iot.Administrador.Data.Supervisor_Data;
import com.example.proyecto_final_iot.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin_supervisor extends AppCompatActivity implements Serializable, SupervisorListAdminAdapter.QuantityListener {

    private SupervisorListAdminAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    SupervisorListAdminAdapter.QuantityListener adapter2;

    private boolean isChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_supervisor);


        recyclerView = findViewById(R.id.recyclerView_lista_supervisor);

        List<Supervisor_Data> data_ListSupervisor = new ArrayList<>();
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 1"));
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 2"));
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 3"));
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 4"));
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 5"));
        data_ListSupervisor.add(new Supervisor_Data("Nombre de Supervisor 6"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SupervisorListAdminAdapter(data_ListSupervisor, this);
        recyclerView.setAdapter(adapter);

    }
        @Override
        public void onQuantityChange(List<String> supervisor_List) {
            Toast.makeText(this, "Seleccionaste este Supervisor " , Toast.LENGTH_SHORT).show();
        }
}