package com.example.home2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




    }
    public void onClickHomeHelp(View view){
        Intent onClickHomeHelp = new Intent(this, Ayuda.class);
        startActivity(onClickHomeHelp);
    }
}
