package com.example.proyecto_final_iot.Supervisor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_final_iot.MainActivity;
import com.example.proyecto_final_iot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SitioDetalleActivity extends AppCompatActivity {
    TextView textViewNombreSitio;
    TextView textViewUbicacion;
    TextView textViewDepartamento;
    TextView textViewProvincia;
    TextView textViewDistrito;
    TextView textViewUbigeo;
    TextView textViewLongLat;
    TextView textViewLong;
    TextView textViewTipoZona;
    TextView textViewTipoSitio;
    Button buttonEquipoSitio;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent loginIntent = new Intent(SitioDetalleActivity.this, MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supervisor_detalles_sitio);

        Intent intent = getIntent();
        String nombreSitio = intent.getStringExtra("site_name");
        String departamento = intent.getStringExtra("departamento");
        String provincia = intent.getStringExtra("provincia");
        String distrito = intent.getStringExtra("distrito");
        String ubigeo = intent.getStringExtra("ubigeo");
        String latitud_longitud = intent.getStringExtra("latitud_longitud");
        String longitud = intent.getStringExtra("longitud");
        String tipo_zona = intent.getStringExtra("tipo_zona");
        String tipo_sitio = intent.getStringExtra("tipo_sitio");

        textViewNombreSitio = findViewById(R.id.textViewNombreSitio);
        textViewDepartamento = findViewById(R.id.textViewDepartamento);
        textViewProvincia = findViewById(R.id.textViewProvincia);
        textViewDistrito = findViewById(R.id.textViewDistrito);
        textViewUbigeo = findViewById(R.id.textViewUbigeo);
        textViewLongLat= findViewById(R.id.textViewLongLat);
        textViewLong = findViewById(R.id.textViewLong);
        textViewTipoZona = findViewById(R.id.textViewTipoZona);
        textViewTipoSitio = findViewById(R.id.textViewTipoSitio);

        textViewNombreSitio.setText(nombreSitio);
        textViewDepartamento.setText(departamento);
        textViewProvincia.setText(provincia);
        textViewDistrito.setText(distrito);
        textViewUbigeo.setText(ubigeo);
        textViewLongLat.setText(latitud_longitud);
        textViewLong.setText(longitud);
        textViewTipoZona.setText(tipo_zona);
        textViewTipoSitio.setText(tipo_sitio);

        buttonEquipoSitio =  findViewById(R.id.buttonEquipoSitio);
        buttonEquipoSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SitioDetalleActivity.this, SitioEquipoSupervisorActivity.class);
                intent.putExtra("nombreSitio", nombreSitio);
                startActivity(intent);
            }
        });

    }
}
