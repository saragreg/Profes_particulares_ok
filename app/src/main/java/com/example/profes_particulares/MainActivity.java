package com.example.profes_particulares;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void registarse(View v){
        //vamos a la pantalla de registro
        Intent intent = new Intent(MainActivity.this, RegistroGeneral.class);
        startActivity(intent);
    }
    public void iniciar_sesion(View v){

        //vamos a la pantalla de logeo
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

}