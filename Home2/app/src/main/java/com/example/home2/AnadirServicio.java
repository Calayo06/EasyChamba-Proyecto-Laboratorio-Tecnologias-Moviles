package com.example.home2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AnadirServicio extends AppCompatActivity {
    private EditText TextServicio;
    private EditText TextNumeroCelular;
    private Button btnAñadirServicio;

    private ProgressDialog progressDialog;
    //FirebaseDatabase database;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference mRootReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_servicio);
        btnAñadirServicio = findViewById(R.id.botonAñadirServicio);
        TextServicio = findViewById(R.id.TxtEditarServicio);
        TextNumeroCelular = findViewById(R.id.TxtEditarNumeroCelular);
        mRootReference=FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        btnAñadirServicio.setOnClickListener(new View.OnClickListener() {
            /******Aca la funcion onClick lo unico que ara es añadir el servicio que ofrece el usuario*********/
            @Override
            public void onClick(View v) {
                String servicio = TextServicio.getText().toString();
                String numero = TextNumeroCelular.getText().toString();
                Map<String, Object> datosUsuario = new HashMap<>();
                //ACA SE ENVIAN LOS DATOS
                datosUsuario.put("Servicio", servicio);
                datosUsuario.put("Numero", numero);
                //PARA QUE PUEDAN SER ALMACENADOS DENTRO DEL USUSARIO TENEMOS QUE DEFINIR SU ID DE ATENTICACION
                String id = mAuth.getCurrentUser().getUid();
                mRootReference.child("Users").child(id).updateChildren(datosUsuario);
                startActivity(new Intent(AnadirServicio.this,MainActivity.class));
                Toast.makeText(AnadirServicio.this, "El servicio fue añadido", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AnadirServicio.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}


