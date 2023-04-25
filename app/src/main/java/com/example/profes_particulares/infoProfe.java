package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.Navigation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class infoProfe extends AppCompatActivity {
    int id;
    TextView nom;
    String asig;
    String nombre;
    String  curso;
    String idiom;
    int exp;
    Float val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_profe);
        nombre=getIntent().getExtras().getString("nombre");
        id=getIntent().getExtras().getInt("id");
        val=getIntent().getExtras().getFloat("val");
        System.out.println("la valoracion es: "+val);
        nom=findViewById(R.id.textView11);
        nom.setText(nombre);

        RatingBar barra= (RatingBar) findViewById(R.id.ratingBar3);
        barra.setRating(val);

        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();


        String s[]={String.valueOf(id)};
        String columns[] = {"Asignaturas","cursos","idiomas","experiencia"};
        String selection = "idUsu LIKE ?"; // WHERE id LIKE ?
        String res[];
        System.out.println("el id es: "+String.valueOf(id));


        Cursor c = bd.query(
                "Profesor",
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
            exp=c.getInt(3);
            System.out.println("las asignaturas al hacer select dandole el id son: "+idiom);
        }
        FragmentProfes fragInfo= (FragmentProfes) getSupportFragmentManager().findFragmentById(R.id.contenedorFragmentos);
        Bundle bundle=new Bundle();
        bundle.putString("asig",asig);
        bundle.putString("curso",curso);
        bundle.putString("idioma",idiom);
        bundle.putInt("exp",exp);
        fragInfo.setArguments(bundle);
        Button info=findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("asig",asig);
                bundle.putString("curso",curso);
                bundle.putString("idioma",idiom);
                bundle.putInt("exp",exp);
                FragmentProfes fragmentProfes=new FragmentProfes();
                fragmentProfes.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedorFragmentos,fragmentProfes)
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

    public void llamar_fragmentprofes(View v){
        System.out.println("las asignaturas son: "+asig);
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        FragmentHorario fragHorario=new FragmentHorario();
        fragHorario.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragmentos,fragHorario)
                .commit();

    }
    public void llamar_fragmentHorario(View v) {
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