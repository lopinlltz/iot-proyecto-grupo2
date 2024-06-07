package com.example.proyecto_final_iot.Administrador.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto_final_iot.Administrador.Adapter.SitioAdminAdapter;
import com.example.proyecto_final_iot.Administrador.Adapter.UsuarioListAdminAdapter;
import com.example.proyecto_final_iot.Administrador.Adapter.UsuarioSelectAdapter;
import com.example.proyecto_final_iot.Administrador.Data.Supervisor_Data;
import com.example.proyecto_final_iot.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin_usuario_detalles extends AppCompatActivity {

    TextView id_nombreUser_tw, id_apellidoUser_tw, id_dniUSer_tw,
            id_correoUser_tw, id_telefonoUser_tw,
            id_domicilioUser_tw;
    ImageView dataImage;
    private UsuarioListAdminAdapter adapter;
    private RecyclerView recyclerView;
    Button editButton_user , backButton_back_det;
    FirebaseFirestore db;
    FirebaseFirestore firestore_lista_detalle_usuario;
    List<Supervisor_Data> data_List_detalle_user;

    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String id_nombreUser = intent.getStringExtra("id_nombreUser");
        String id_apellidoUser = intent.getStringExtra("id_apellidoUser");
        String id_dniUSer = intent.getStringExtra("id_dniUSer");
        String id_correoUser = intent.getStringExtra("id_correoUser");
        String id_telefonoUser = intent.getStringExtra("id_telefonoUser");
        String id_domicilioUser = intent.getStringExtra("id_domicilioUser");


        setContentView(R.layout.activity_admin_usuario_detalles);

        FirebaseApp.initializeApp(this);

        id_nombreUser_tw = findViewById(R.id.id_nombreUser);
        id_apellidoUser_tw = findViewById(R.id.id_apellidoUser);
        id_dniUSer_tw = findViewById(R.id.id_dniUSer);
        id_correoUser_tw = findViewById(R.id.id_correoUser);
        id_telefonoUser_tw = findViewById(R.id.id_telefonoUser);
        id_domicilioUser_tw = findViewById(R.id.id_domicilioUser);
        dataImage = findViewById(R.id.imagenview_detalles);


        id_nombreUser_tw.setText(id_nombreUser);
        id_apellidoUser_tw.setText(id_apellidoUser);
        id_dniUSer_tw.setText(id_dniUSer);
        id_correoUser_tw.setText(id_correoUser);
        id_telefonoUser_tw.setText(id_telefonoUser);
        id_domicilioUser_tw.setText(id_domicilioUser);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dataImage.setImageURI(Uri.parse(bundle.getString("dataImage")));
            if (bundle != null && bundle.getString("dataImage") != null) {
                String imageUrl = bundle.getString("dataImage");
                Glide.with(this)
                        .load(imageUrl)
                        .into(dataImage);
            }

        }

        editButton_user = findViewById(R.id.editButton_user);
        editButton_user.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        Intent intent = new Intent(Admin_usuario_detalles.this, Admin_usuario_editar.class);
        intent.putExtra("id_nombreUser", id_nombreUser);
        intent.putExtra("id_apellidoUser", id_apellidoUser);
        intent.putExtra("id_dniUSer", id_dniUSer);
        intent.putExtra("id_correoUser", id_correoUser);
        intent.putExtra("id_telefonoUser", id_telefonoUser);
        intent.putExtra("id_domicilioUser", id_domicilioUser);
        startActivity(intent);
    }
    });

        backButton_back_det= findViewById(R.id.backButton_back_det);
        backButton_back_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_usuario_detalles.this, Admin_lista_usuario.class);
                startActivity(intent);
            }
        });

}
    }
    
