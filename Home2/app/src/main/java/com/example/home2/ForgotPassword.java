/*
    EASYCHAMBA ForgotPassword
    Desarrollado en Java
    Desarollado por Young Developers:
        - Alosilla Sánchez Moreno Guillermo
        - Delgado Rivera Ricardo Mauricio
        - Lazo Acuña Franco Alfredo
        - Mamani Paccori Ray Jose Enrique
        - Rodriguez Lopez Paolo
*/
package com.example.home2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ForgotPassword extends AppCompatActivity {
    Button recuperarBoton;
    EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        recuperarBoton = (Button) findViewById(R.id.recuperarButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        recuperarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }


    public void validate(){
        String email = emailEditText.getText().toString().trim();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Correo invalido");
            return;
        }
        sendEmail(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ForgotPassword.this, Login.class);
        startActivity(intent);
        finish();
    }


    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                      if (task.isSuccessful()){
                          Toast.makeText(ForgotPassword.this,"Correo Enviado",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(ForgotPassword.this, Login.class);
                          startActivity(intent);
                          finish();
                      }
                      else{
                          Toast.makeText(ForgotPassword.this,"Correo Invalido",Toast.LENGTH_SHORT).show();
                      }
                    }
                });
    }

    public void onClickOlvidarLogin(View view) {
        Intent onClickOlvidarLogin = new Intent(this, Login.class);
        startActivity(onClickOlvidarLogin);
    }
}
