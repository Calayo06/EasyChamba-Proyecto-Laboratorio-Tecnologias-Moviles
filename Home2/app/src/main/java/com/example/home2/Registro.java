/*
    EASYCHAMBA Registro
    Desarrollado en Java
    Desarollado por Young Developers:
        - Alosilla Sánchez Moreno Guillermo
        - Delgado Rivera Ricardo Mauricio
        - Lazo Acuña Franco Alfredo
        - Mamani Paccori Ray Jose Enrique
        - Rodriguez Lopez Paolo
*/
package com.example.home2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    /******Definimos los parametros que van a ser resivido********/
    private EditText TextNombre;
    private EditText TextApellidos;
    private EditText TextDni;
    private EditText TextEmail;
    private EditText TextPassword;
    /*private EditText TextServicio;
    private EditText TextNumero;*/
    private Button btnRegistrar;

    private ProgressDialog progressDialog;
    String nombres= "" ;
    String apellidos= "" ;
    String dni= "";
    String email= "";
    String password="";
    String servicio="";
    String numero="";

    //Declaramos un objeto firebaseAuth
    FirebaseAuth mAuth;
    //FirebaseDatabase database;
    DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();//Conectamos con la base de datos para la autenticaciopn
        mDataBase = FirebaseDatabase.getInstance().getReference();// Conectamos con la base de datos para guardar los datos ingresados

        TextNombre =(EditText) findViewById(R.id.TxtNombreR);
        TextApellidos =(EditText) findViewById(R.id.TxtApellidoR);
        TextDni =(EditText) findViewById(R.id.TxtDniR);

        TextEmail = (EditText) findViewById(R.id.TxtEmailRegistro);
        TextPassword = (EditText) findViewById(R.id.TxtPasswordRegistro);
        /*TextServicio = (EditText) findViewById(R.id.TxtServicioOfrecer);
        TextNumero = (EditText) findViewById(R.id.TxtNumero);*/

        btnRegistrar = (Button) findViewById(R.id.botonRegistrar);

        progressDialog = new ProgressDialog(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            /****** aca definimos lo que pasara cada vez q se registra un usuario******/
            @Override
            public void onClick(View view) {
                nombres = TextNombre.getText().toString();
                apellidos = TextApellidos.getText().toString();
                dni = TextDni.getText().toString();
                email = TextEmail.getText().toString();
                password = TextPassword.getText().toString();
                /*servicio = TextServicio.getText().toString();
                numero = TextNumero.getText().toString();*/

                if (!nombres.isEmpty() && !apellidos.isEmpty() && dni.length()==8 && !email.isEmpty() && !password.isEmpty()) {
                    if (password.length() <= 6) {
                        Toast.makeText(Registro.this, "El password debe tener por lo menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    /*if (dni.length()<=7) {
                        Toast.makeText(Registro.this, "El DNI debe tener 8 caracteres", Toast.LENGTH_SHORT).show();
                    }*/
                    registrarUsuario();

                } else {
                    Toast.makeText(Registro.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registro.this, "El DNI debe tener 8 caracteres", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    /*************Funcion que registrara los datos del usuario**************/
    private void registrarUsuario() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("Nombres",nombres);
                            map.put("Apellidos",apellidos);
                            map.put("Dni",dni);                        /***Creamos la base de datos con sus respectivos datos*/
                            map.put("Email",email);
                            map.put("Password",password);
                            map.put("Servicio",servicio);
                            map.put("Numero",numero);

                            String id = mAuth.getCurrentUser().getUid();

                            mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                  if (task2.isSuccessful()) {
                                      //Intent intencion = new Intent(getApplication(), Login.class);
                                      //intencion.putExtra(MainActivity.user, user);
                                      startActivity(new Intent(Registro.this,Login.class));
                                      finish();
                                  }else{
                                      Toast.makeText(Registro.this, "No se pudieron crear los datos correctamente" , Toast.LENGTH_SHORT).show();
                                  }
                                }
                            });
                            Toast.makeText(Registro.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(Registro.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                            }
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Registro.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
        finish();
    }

    /*@Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/

    /*public void onClickRegistroLogin(View view) {
        Intent onClickRegistroLogin = new Intent(this, Login.class);
        startActivity(onClickRegistroLogin);
    }*/

    /*@Override
    public void onClick(View v) {

    }*/
}
