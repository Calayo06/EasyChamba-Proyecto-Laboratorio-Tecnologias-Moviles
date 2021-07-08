package com.example.home2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
public static final String user="names";
TextView txtUser;
    CardView cardHome;
    CardView cardChat;
    CardView cardSearch;
    CardView cardAñadir;
/*************************LO UNICO QUE SE HIZO ACA ES LLAMAR A LAS OTRAS ACTIVIDADES  Y DEFINIR LOS CARVIEW************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardHome = findViewById(R.id.cardHome);
        cardChat = findViewById(R.id.cardChat);
        cardSearch = findViewById(R.id.cardSearch);
        cardAñadir = findViewById(R.id.cardAñadir);

        txtUser = (TextView) findViewById(R.id.textser);
        String user = getIntent().getStringExtra("names");
        txtUser.setText("Bienvenido "+ user + "!");


    }
    public void onClickHomeHome(View view) {
        Intent onClickHomeHome = new Intent(this, Home.class);
        startActivity(onClickHomeHome);
    }


    public void onClickHomeLogin(View view) {
        Intent onClickHomeLogin = new Intent(this, Login.class);
        startActivity(onClickHomeLogin);
    }
    public void onClickHomeBuscar(View view) {
        Intent onClickHomeBuscar = new Intent(this, BuscarServicio.class);
        startActivity(onClickHomeBuscar);
    }
    public void onClickHomeAnadirServicio(View view){
        Intent onClickHomeAnadirServicio = new Intent(this, AnadirServicio.class);
        startActivity(onClickHomeAnadirServicio);
    }
    public void onClickHomeUbicacion(View view){
        Intent onClickHomeUbicacion = new Intent(this, MiUbicacion.class);
        startActivity(onClickHomeUbicacion);
    }
}

