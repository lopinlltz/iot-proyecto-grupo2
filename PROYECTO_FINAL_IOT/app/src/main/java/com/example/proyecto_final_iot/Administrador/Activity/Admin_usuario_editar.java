package com.example.proyecto_final_iot.Administrador.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proyecto_final_iot.Administrador.Data.Supervisor_Data;
import com.example.proyecto_final_iot.MainActivity;
import com.example.proyecto_final_iot.NotificationHelper;
import com.example.proyecto_final_iot.R;
import com.example.proyecto_final_iot.Superadmin.Activity.Superadmin_vista_principal1;
import com.example.proyecto_final_iot.Superadmin.Activity.superadmin_editar_administrador;
import com.example.proyecto_final_iot.Superadmin.Data.Admin;
import com.example.proyecto_final_iot.Supervisor.Activity.EquipoDetalleActivity;
import com.example.proyecto_final_iot.Supervisor.Activity.EquipoEditarActivity;
import com.example.proyecto_final_iot.Supervisor.Activity.EquiposSupervisorActivity;
import com.example.proyecto_final_iot.Superadmin.Data.HistorialData;
import com.example.proyecto_final_iot.Supervisor.Activity.SitioSupervisorActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Admin_usuario_editar extends AppCompatActivity {

    // Variables para las vistas
    EditText id_nombreUser_et, id_apellidoUser_et, id_dniUSer_et, id_correoUser_et, id_telefonoUser_et, id_domicilioUser_et;
    Button saveButton_user_edit, saveButton_back_edit;
    Button uploadImagen, selecImage;
    ImageView dataImage,imagenview_editar;
    String imagenURL;
    Uri image;
    // Firestore
    FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    FirebaseAuth mAuth;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent loginIntent = new Intent(Admin_usuario_editar.this, MainActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuario_editar);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        // Asignar vistas a las variables
        id_nombreUser_et = findViewById(R.id.id_nombreUser);
        id_apellidoUser_et = findViewById(R.id.id_apellidoUser);
        id_dniUSer_et = findViewById(R.id.id_dniUSer);
        id_correoUser_et = findViewById(R.id.id_correoUser);
        id_telefonoUser_et = findViewById(R.id.id_telefonoUser);
        id_domicilioUser_et = findViewById(R.id.id_domicilioUser);
        dataImage = findViewById(R.id.imagenview_editar);
        selecImage = findViewById(R.id.selecImage);

        // Obtener datos del Intent
        Intent intent = getIntent();
        String id_nombreUser = intent.getStringExtra("id_nombreUser");
        String id_apellidoUser = intent.getStringExtra("id_apellidoUser");
        String id_dniUSer = intent.getStringExtra("id_dniUSer");
        String id_correoUser = intent.getStringExtra("id_correoUser");
        String id_telefonoUser = intent.getStringExtra("id_telefonoUser");
        String id_domicilioUser = intent.getStringExtra("id_domicilioUser");
        String imagenUrl = intent.getStringExtra("dataImage");

        // Establecer texto en EditTexts
        id_nombreUser_et.setText(id_nombreUser);
        id_apellidoUser_et.setText(id_apellidoUser);
        id_dniUSer_et.setText(id_dniUSer);
        id_correoUser_et.setText(id_correoUser);
        id_telefonoUser_et.setText(id_telefonoUser);
        id_domicilioUser_et.setText(id_domicilioUser);

        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.getString("dataImage") != null) {
            String imageUrl = bundle.getString("dataImage");
            Glide.with(this)
                    .load(imageUrl)
                    .into(dataImage);
        }

        // Botón para guardar los cambios
        saveButton_user_edit = findViewById(R.id.saveButton_user_edit);
        saveButton_user_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = id_nombreUser_et.getText().toString();
                String nuevoApellido = id_apellidoUser_et.getText().toString();
                String nuevoDNI = id_dniUSer_et.getText().toString();
                String nuevoCorreo = id_correoUser_et.getText().toString();
                String nuevoTelefono = id_telefonoUser_et.getText().toString();
                String nuevoDomicilio = id_domicilioUser_et.getText().toString();

                // Mostrar confirmación
                ConfirmacionPopup(nuevoNombre, nuevoApellido, nuevoDNI, nuevoCorreo, nuevoTelefono, nuevoDomicilio);
            }

        });

        saveButton_back_edit = findViewById(R.id.saveButton_back_edit);
        saveButton_back_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_usuario_editar.this, Admin_lista_usuario.class);

                intent.putExtra("id_nombreUser", id_nombreUser);
                intent.putExtra("id_apellidoUser", id_apellidoUser);
                intent.putExtra("id_dniUSer", id_dniUSer);
                intent.putExtra("id_correoUser", id_correoUser);
                intent.putExtra("id_telefonoUser", id_telefonoUser);
                intent.putExtra("id_domicilioUser", id_domicilioUser);
                startActivity(intent);
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            image = data.getData();
                            Glide.with(this).load(image).into(dataImage);
                        }
                    }
                });
        selecImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

    }
    private void subirImagenYActualizarUsuario(String id_nombreUser, String id_apellidoUser, String id_dniUser, String id_correoUser, String id_telefonoUser, String id_domicilioUser) {
        if (image != null) {
            StorageReference imagenRef = storageRef.child("imagenes_usuarios/" + id_nombreUser + ".jpg");

            imagenRef.putFile(image)
                    .addOnSuccessListener(taskSnapshot -> {
                        imagenRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();
                            editarUsuario(id_nombreUser, id_apellidoUser, id_dniUser, id_correoUser, id_telefonoUser, id_domicilioUser, imageUrl);
                        });
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Admin_usuario_editar", "Error al subir la imagen", e);
                        Toast.makeText(Admin_usuario_editar.this, "Error al subir la imagen", Toast.LENGTH_SHORT).show();
                    });
        } else {
            // Si no hay nueva imagen, actualiza solo los otros datos
            editarUsuario(id_nombreUser, id_apellidoUser, id_dniUser, id_correoUser, id_telefonoUser, id_domicilioUser, null);
        }
    }
    private void ConfirmacionPopup(String id_nombreUser, String id_apellidoUser, String id_dniUSer, String id_correoUser, String id_telefonoUser, String id_domicilioUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de guardar los cambios?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Subir imagen y actualizar usuario en Firestore
                subirImagenYActualizarUsuario(id_nombreUser, id_apellidoUser, id_dniUSer, id_correoUser, id_telefonoUser, id_domicilioUser);
                guardarHistorial("Editó el usuario: " + id_nombreUser, "Samantha", "Administrador");
                dialog.dismiss();

                // Notificación
                NotificationHelper.createNotificationChannel(Admin_usuario_editar.this);
                NotificationHelper.sendNotification(Admin_usuario_editar.this, "Usuario", "Usuario editado");
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void editarUsuario(String id_nombreUser, String id_apellidoUser, String id_dniUser, String id_correoUser, String id_telefonoUser, String id_domicilioUser, String imageUrl) {
        db.collection("supervisorAdmin")
                .whereEqualTo("id_nombreUser", id_nombreUser)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            DocumentReference usuarioRef = task.getResult().getDocuments().get(0).getReference();

                            Map<String, Object> updates = new HashMap<>();
                            updates.put("id_nombreUser", id_nombreUser);
                            updates.put("id_apellidoUser", id_apellidoUser);
                            updates.put("id_dniUSer", id_dniUser);
                            updates.put("id_correoUser", id_correoUser);
                            updates.put("id_telefonoUser", id_telefonoUser);
                            updates.put("id_domicilioUser", id_domicilioUser);
                            if (imageUrl != null) {
                                updates.put("dataImage", imageUrl);
                            }

                            usuarioRef.update(updates)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("Admin_usuario_editar", "Usuario actualizado con éxito");
                                        Toast.makeText(Admin_usuario_editar.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();

                                        // Actualizar UI
                                        id_nombreUser_et.setText(id_nombreUser);
                                        id_apellidoUser_et.setText(id_apellidoUser);
                                        id_dniUSer_et.setText(id_dniUser);
                                        id_correoUser_et.setText(id_correoUser);
                                        id_telefonoUser_et.setText(id_telefonoUser);
                                        id_domicilioUser_et.setText(id_domicilioUser);

                                        Intent intent = new Intent(Admin_usuario_editar.this, Admin_lista_usuario.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("Admin_usuario_editar", "Error al actualizar el usuario", e);
                                        Toast.makeText(Admin_usuario_editar.this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
                                    });
                        } else {
                            Log.e("Admin_usuario_editar", "El documento con Nombre " + id_nombreUser + " no existe.");
                            Toast.makeText(Admin_usuario_editar.this, "El documento con Nombre " + id_nombreUser + " no existe.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("Admin_usuario_editar", "Error al obtener el documento", task.getException());
                        Toast.makeText(Admin_usuario_editar.this, "Error al obtener el documento", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void guardarHistorial(String actividad, String usuario, String rol) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedHour = hourFormat.format(currentDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        HistorialData historial = new HistorialData(actividad, usuario, rol, formattedDate, formattedHour);

        db.collection("historialglobal")
                .add(historial)
                .addOnSuccessListener(documentReference -> {
                    // Historial guardado con éxito
                })
                .addOnFailureListener(e -> {
                    // Error al guardar el historial
                });
    }
}
