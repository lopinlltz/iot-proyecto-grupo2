package com.example.proyecto_final_iot.Administrador.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_final_iot.databinding.ActivityAdminSitioDetallesBinding;
import com.github.clans.fab.FloatingActionButton;
import com.example.proyecto_final_iot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Admin_sitio_detalles extends AppCompatActivity {

    FloatingActionButton boton_delete;
    FloatingActionButton boton_edit;
    TextView id_codigodeSitio, id_departamento, id_provincia, id_distrito, id_ubigeo,
            id_tipo_de_zona_det, id_tipo_de_sitio_det, id_latitud_long;
    String key="";

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sitio_detalles);

        id_codigodeSitio = findViewById(R.id.id_codigodeSitio);
        id_departamento = findViewById(R.id.id_departamento);
        id_provincia = findViewById(R.id.id_provincia);
        id_distrito = findViewById(R.id.id_distrito);
        id_ubigeo = findViewById(R.id.id_ubigeo);
        //id_tipo_de_zona = findViewById(R.id.id_tipo_de_zona);
        //id_tipo_de_sitio = findViewById(R.id.id_tipo_de_sitio);
        id_latitud_long = findViewById(R.id.id_latitud_long);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id_codigodeSitio.setText(bundle.getString("id_codigodeSitio"));
            id_departamento.setText(bundle.getString("id_departamento"));
            id_provincia.setText(bundle.getString("id_provincia"));
            id_distrito.setText(bundle.getString("id_distrito"));
            id_ubigeo.setText(bundle.getString("id_ubigeo"));
            //id_tipo_de_zona_det.setText(bundle.getString("Codigo"));
            //id_tipo_de_sitio_det.setText(bundle.getString("Codigo"));
            id_latitud_long.setText(bundle.getString("id_latitud_long"));
            key= bundle.getString("key");

        }
        //key = getIntent().getStringExtra("itemKey");


        boton_delete = (FloatingActionButton) findViewById(R.id.boton_delete);
        boton_edit = findViewById(R.id.boton_edit);

        boton_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               FirebaseFirestore.getInstance().collection("sitio").document("codigo 1").delete()
                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(Admin_sitio_detalles.this, "Elemento eliminado" , Toast.LENGTH_SHORT).show();
                   }
               });
            }

        });

        boton_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_sitio_detalles.this, Admin_sitio_editar.class);
                startActivity(intent);
            }
        });
    }

}