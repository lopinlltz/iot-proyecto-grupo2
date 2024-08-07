package com.example.proyecto_final_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_final_iot.Superadmin.Activity.Superadmin_vista_principal1;

public class ContrasenaOlvidada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contrasena_olvidada);
        Button buttonenviarCodigo = findViewById(R.id.enviarcodigo);
        Button buttonatras = findViewById(R.id.atras);
        buttonenviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContrasenaOlvidada.this, Codigorecibido.class);
                startActivity(intent);
            }
        });
        buttonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContrasenaOlvidada.this, Codigorecibido.class);
                startActivity(intent);
            }
        });

    }
}