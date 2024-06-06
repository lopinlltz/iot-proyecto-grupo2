package com.example.proyecto_final_iot.Superadmin.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_final_iot.NotificationHelper;
import com.example.proyecto_final_iot.R;
import com.example.proyecto_final_iot.Superadmin.Data.Admin;
import com.google.firebase.firestore.FirebaseFirestore;

public class superadmin_editar_administrador extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText editNombre;
    private EditText editApellido;
    private EditText editDni;
    private EditText editCorreo;
    private EditText editTelefono;
    private EditText editDomicilio;
    private String adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superadmin_editar_administrador);

        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editDni = findViewById(R.id.editDni);
        editCorreo = findViewById(R.id.editCorreo);
        editTelefono = findViewById(R.id.editTelefono);
        editDomicilio = findViewById(R.id.editDomicilio);

        ImageButton btnUserProfile = findViewById(R.id.imageButton6);
        ImageButton btnHome = findViewById(R.id.buttonhomesuper);
        ImageButton btnHistory = findViewById(R.id.buttonhistorialsuper);
        Button atras = findViewById(R.id.button2);
        Button guardar = findViewById(R.id.button5);

        db = FirebaseFirestore.getInstance();

        adminId = getIntent().getStringExtra("ADMIN_ID");

        db.collection("administrador").document(adminId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Admin administrador = documentSnapshot.toObject(Admin.class);
                        if (administrador != null) {
                            editNombre.setText(administrador.getNombreUser());
                            editApellido.setText(administrador.getApellidoUser());
                            editDni.setText(String.valueOf(administrador.getDniUser()));
                            editCorreo.setText(administrador.getCorreoUser());
                            editTelefono.setText(String.valueOf(administrador.getTelefonoUser()));
                            editDomicilio.setText(administrador.getDomicilioUser());
                        }
                    }
                });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Vuelve a la actividad anterior
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaveConfirmationDialog();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(superadmin_editar_administrador.this, superadmin_logs.class);
                startActivity(intent);
            }
        });

        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(superadmin_editar_administrador.this, PerfilSuperadmin.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(superadmin_editar_administrador.this, Superadmin_vista_principal1.class);
                startActivity(intent);
            }
        });
    }

    private void showSaveConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("¿Está seguro que desea guardar?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                guardarAdministrador();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void guardarAdministrador() {
        String nombre = editNombre.getText().toString().trim();
        String apellido = editApellido.getText().toString().trim();
        int dni = Integer.parseInt(editDni.getText().toString().trim());
        String correo = editCorreo.getText().toString().trim();
        int telefono = Integer.parseInt(editTelefono.getText().toString().trim());
        String domicilio = editDomicilio.getText().toString().trim();

        Admin administrador = new Admin(adminId, nombre, apellido, dni, correo, telefono, domicilio, "Hora placeholder");

        db.collection("administrador").document(adminId)
                .set(administrador)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(superadmin_editar_administrador.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                    NotificationHelper.createNotificationChannel(superadmin_editar_administrador.this);
                    NotificationHelper.sendNotification(superadmin_editar_administrador.this, "Usuarios", "Administrador editado");
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(superadmin_editar_administrador.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                });
    }
}
