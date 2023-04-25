package com.example.profes_particulares;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button comprobar;
    EditText u;
    EditText c;
    String usu;
    String contra;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();
        comprobar=findViewById(R.id.button);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u=findViewById(R.id.editTextTextPersonName);
                c=findViewById(R.id.editTextTextPassword);
                usu=u.getText().toString();
                contra=c.getText().toString();
                String s[]={usu};
                String columns[] = {"contraseña","id","nombre","perfil"};
                String selection = "usu LIKE ?"; // WHERE id LIKE ?

                Cursor c = bd.query(
                        "Usuario",
                        columns,
                        selection,
                        s,
                        null,
                        null,
                        null
                );
                while (c.moveToNext()){
                    id=c.getInt(0);
                    String nom=c.getString(2);
                    if (contra.equals(c.getString(0))){
                        if (c.getString(3).equals("a")) {
                            Intent intent = new Intent(Login.this, ListaProfes.class);
                            intent.putExtra("id", id);
                            intent.putExtra("nombre", nom);
                            startActivity(intent);
                        } else if (c.getString(3).equals("p")) {
                            Intent intent = new Intent(Login.this, ListaAlus.class);
                            intent.putExtra("id", id);
                            intent.putExtra("nombre", nom);
                            startActivity(intent);
                        }

                    }else{
                        Toast borrar = Toast.makeText(getApplicationContext(),"el usuario y contraseña son incorrectos",Toast.LENGTH_SHORT);
                        borrar.show();
                    }

                }

            }
        });
    }

}