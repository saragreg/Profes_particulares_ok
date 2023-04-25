package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoAlus extends AppCompatActivity {
    TextView nom;
    String asig;
    String nombre;
    String  curso;
    String idiom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alus);
        int id=getIntent().getExtras().getInt("id");
        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();


        String s[]={String.valueOf(id)};
        String columns[] = {"Asignaturas","cursos","idiomas","nombre"};
        String selection = "idUsu LIKE ?"; // WHERE id LIKE ?
        String res[];
        System.out.println("el id es: "+String.valueOf(id));


        Cursor c = bd.query(
                "Alumnos",
                columns,
                selection,
                s,
                null,
                null,
                null
        );
        while (c.moveToNext()){
            asig=c.getString(0);
            curso=c.getString(1);
            idiom=c.getString(2);
            nombre=c.getString(3);
            System.out.println("las asignaturas al hacer select dandole el id son: "+idiom);
        }
        nom=findViewById(R.id.textView11);
        nom.setText(nombre);

        Bundle bundle=new Bundle();
        bundle.putString("asig",asig);
        bundle.putString("curso",curso);
        bundle.putString("idioma",idiom);

        FragmentAlus fragmentAlus=new FragmentAlus();
        fragmentAlus.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedorFragmentos,fragmentAlus)
                .commit();

        Button info=findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("asig",asig);
                bundle.putString("curso",curso);
                bundle.putString("idioma",idiom);

                FragmentAlus fragmentAlus=new FragmentAlus();
                fragmentAlus.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedorFragmentos,fragmentAlus)
                        .commit();
            }
        }

        );
        Button hor=findViewById(R.id.horario);
        hor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Bundle bundle=new Bundle();
                   bundle.putInt("id",id);
                   FragmentHorario fragHorario=new FragmentHorario();
                   fragHorario.setArguments(bundle);
                   getSupportFragmentManager()
                           .beginTransaction()
                           .replace(R.id.contenedorFragmentos,fragHorario)
                           .commit();
               }
           }

        );


    }

}