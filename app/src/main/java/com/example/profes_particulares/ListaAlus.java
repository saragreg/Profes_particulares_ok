package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaAlus extends AppCompatActivity {
    int id;
    String nom;
    ArrayList<String> nombres= new ArrayList<String>();
    ArrayList<String> cursos= new ArrayList<String>();
    ArrayList<Integer> ids=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alus);
        id=getIntent().getExtras().getInt("id");
        nom=getIntent().getExtras().getString("nombre");

        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();


        TextView nombre=findViewById(R.id.textView10);
        nombre.setText("bienvenida de nuevo "+nom+" aquí están tus profesores más cercanos");
        String[] col={"nombre","cursos","idUsu"};
        Cursor c = bd.query(
                "Alumnos",
                col,
                null,
                null,
                null,
                null,
                null
        );
        while (c.moveToNext()){
            nombres.add(c.getString(0));
            cursos.add(c.getString(1));
            ids.add(c.getInt(2));
        }


        ListView lisprofes= (ListView) findViewById(R.id.listView);
        AdaptadorAlu eladap= new AdaptadorAlu(getApplicationContext(),nombres,cursos);
        lisprofes.setAdapter(eladap);
        ListView lista= findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListaAlus.this, InfoAlus.class);
                intent.putExtra("id",ids.get(i));
                startActivity(intent);
            }
        });
    }


}