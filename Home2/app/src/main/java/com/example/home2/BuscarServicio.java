/*
    EASYCHAMBA BuscarServicio
    Desarollado en Java
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
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.home2.Adapter.AdapterUsuario;
import com.example.home2.pojo.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class BuscarServicio extends AppCompatActivity {
    /*ArrayList<Users> list;
    DatabaseReference ref;
    RecyclerView rv;
    SearchView searchView;
    AdapterUsuario adapter;
    LinearLayoutManager lm;*/
    DatabaseReference mDatabase;
    SearchView searchView;
    AdapterUsuario mAdapter;
    RecyclerView mRecyclerView;
    ArrayList<Users> mUsuariosList = new ArrayList<>();


    /*************** ACA SE AñADIERON ELEMENTOS A LA LISTA PARA QUE SE PUEDA MOSTRAR
     LOS SERVICIOS QUE SE ESTAN OFRECIENDO******************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_servicio);

        /*ref = FirebaseDatabase.getInstance().getReference();
        rv = findViewById(R.id.rv);*/
        searchView = (SearchView) findViewById(R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getUsersFromFirebase();
        
    }

    private void getUsersFromFirebase(){
        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for(DataSnapshot ds: snapshot.getChildren()){
                        String nombre = ds.child("Nombres").getValue().toString();
                        String numero = ds.child("Numero").getValue().toString();
                        String servicio = ds.child("Servicio").getValue().toString();
                        mUsuariosList.add(new Users(nombre,numero,servicio));

                    }

                    mAdapter = new AdapterUsuario(mUsuariosList, R.layout.buscar_servicio);
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



      /*  ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Users us = snapshot1.getValue(Users.class);
                        Log.e("","" + snapshot1.getValue());
                        list.add(us);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    /*private void buscar(String s){
        ArrayList<Users> milista = new ArrayList<>();
        for(Users obj: mUsuariosList){
            if (obj.getServicio().toLowerCase().contains(s.toLowerCase())){
                milista.add(obj);
            }

        }
        AdapterUsuario adapter1 = new AdapterUsuario(milista,R.layout.activity_buscar_servicio);
        mRecyclerView.setAdapter(adapter1);
    }*/
}
