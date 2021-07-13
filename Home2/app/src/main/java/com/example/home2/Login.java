package com.example.home2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView olvidasteContrasena;
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        TextEmail = (EditText) findViewById(R.id.emailEditText);
        TextPassword = (EditText) findViewById(R.id.TxtPasswordLogin);

        btnLogin = (Button) findViewById(R.id.recuperarButton);

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(this);
        olvidasteContrasena = (TextView) findViewById(R.id.txtOlvidasteContrasena);
    }

    private void iniciarSesion() {
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un Users", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Iniciando Sesion");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    /******Aca solo validamos de que el correo y el password esten asociados ****/
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            int post = email.indexOf("@");
                            String user = email.substring(0,post);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Bienvenido " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(),MainActivity.class);
                            intencion.putExtra(MainActivity.user, user);
                            startActivity(intencion);

                        } else {
                            if(task.getException() instanceof FirebaseAuthInvalidUserException){
                                Toast.makeText(Login.this, "El usuario no es valido ", Toast.LENGTH_LONG).show();
                            }
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "El usuario no existe", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }

                });
    }
    @Override
    public void onClick(View view){
        iniciarSesion();
    }

    public void onClickLoginOlvidar(View view) {
        Intent onClickLoginOlvidar = new Intent(this, ForgotPassword.class);
        startActivity(onClickLoginOlvidar);
    }

    public void onClickLoginRegistro(View view) {
        Intent onClickLoginRegistro = new Intent(this, Registro.class);
        startActivity(onClickLoginRegistro);
    }
    public void onClickLoginHome(View view) {
        Intent onClickLoginHome = new Intent(this, MainActivity.class);
        startActivity(onClickLoginHome);
    }

}