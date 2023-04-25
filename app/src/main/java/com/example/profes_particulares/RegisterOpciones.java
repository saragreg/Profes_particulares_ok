package com.example.profes_particulares;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterOpciones extends AppCompatActivity {
    Button profe;
    Button alu;
    String s[]=new String[1];
    String nom;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_opciones);

        id=getIntent().getExtras().getInt("id");
        nom=getIntent().getExtras().getString("nombre");

        s[0]=String.valueOf(id);

        miBD GestorDB = new miBD(this, "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();

        profe=findViewById(R.id.profesor);
        profe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues modificacion = new ContentValues();
                modificacion.put("perfil","p");
                String s[]={String.valueOf(id)};
                bd.update("Usuario", modificacion, "id=?", s);

                Cursor cur = bd.query(
                        "Usuario",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                while (cur.moveToNext()){
                    System.out.println("id"+cur.getString(0));
                    System.out.println("perfil "+cur.getString(5));
                }
                Intent intent = new Intent(RegisterOpciones.this, Registro1Profe.class);
                intent.putExtra("id",id);
                intent.putExtra("nombre",nom);
                startActivity(intent);
                bd.close();
                cur.close();
            }
        });
        alu=findViewById(R.id.alumno);
        alu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues modificacion = new ContentValues();
                modificacion.put("perfil","a");
                String s[]={String.valueOf(id)};
                bd.update("Usuario", modificacion, "id=?", s);
                Cursor cur = bd.query(
                        "Usuario",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                while (cur.moveToNext()){
                    System.out.println("id"+cur.getString(0));
                    System.out.println("perfil "+cur.getString(5));
                }
                Cursor cu = bd.rawQuery("SELECT * FROM Usuario",null);
                while(cu.moveToNext()){
                    int i=0;
                    while(i<6){
                        System.out.println(cu.getString(i));
                        i++;
                    }

                }

                Intent intent = new Intent(RegisterOpciones.this, Registro1Alumno.class);
                intent.putExtra("perfil","a");
                intent.putExtra("id",id);
                intent.putExtra("nombre",nom);
                startActivity(intent);
                bd.close();
                cur.close();
                cu.close();
            }
        });

    }

}