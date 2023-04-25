package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class Registro1Profe extends AppCompatActivity {
    CheckBox primaria;
    CheckBox ESO;
    CheckBox bach;
    EditText asignaturas;
    EditText preciop;
    EditText precioe;
    EditText preciob;
    EditText experiencia;
    RadioButton castellano;
    RadioButton euskera;
    boolean prim;
    boolean eso;
    boolean bachi;
    RadioButton ambos;

    String asig="";
    String cursos="";
    String precios="";
    String idiomas="";
    String exp;
    String nom;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1_profe);
        nom=getIntent().getExtras().getString("nombre");
        id=getIntent().getExtras().getInt("id");

        miBD GestorDB = new miBD(this, "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();
        Cursor cu = bd.rawQuery("SELECT * FROM Profesor", null);
        //this.deleteDatabase("Profesores");

        while (cu.moveToNext()){
            System.out.println(cu.getInt(0)+" "+cu.getString(1));
        }

        Button b =findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String [] s= {usu,contra,nom};
                //bd.execSQL("INSERT INTO Usuarios('Usuario','Contraseña','Nombre') VALUES (?)",s);

                //recogemos todos los valores de los edit text
                primaria = findViewById(R.id.primaria);
                ESO = findViewById(R.id.ESO);
                bach = findViewById(R.id.bach);
                if (primaria.isChecked()) {
                    cursos = "primaria,";
                    preciop = findViewById(R.id.editTextTextPersonName5);
                    precios=preciop.getText().toString()+",";
                }
                if (ESO.isChecked()) {
                    cursos = cursos + "ESO,";
                    precioe = findViewById(R.id.editTextTextPersonName6);
                    precios=precios+precioe.getText().toString()+",";
                }
                if (bach.isChecked()) {
                    cursos = cursos + "bachiller";
                    preciob = findViewById(R.id.editTextTextPersonName7);
                    precios=precios+preciob.getText().toString();
                }
                asignaturas = findViewById(R.id.editTextTextPersonName3);
                asig=asignaturas.getText().toString();

                experiencia = findViewById(R.id.editTextNumber2);
                exp = experiencia.getText().toString();


                ContentValues cv = new ContentValues();


                //el valor de id se introduce solo porque se autoincrementa
                cv.put("idUsu",id);
                cv.put("nombre", nom);
                cv.put("precio",precios);
                cv.put("asignaturas",asig);
                cv.put("cursos",cursos);
                cv.put("idiomas",idiomas);
                cv.put("experiencia",exp);

                bd.insert("Profesor", null, cv);

                Cursor c = bd.query(
                        "Profesor",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                while (c.moveToNext()){
                    System.out.println("id: "+c.getString(0));
                    System.out.println("idUsu: "+c.getString(1));
                    System.out.println("nom: "+c.getString(2));
                    System.out.println("precio: "+c.getString(3));
                    System.out.println("asig: "+c.getString(4));
                    System.out.println("curso: "+c.getString(5));
                    System.out.println("idioma: "+c.getString(6));
                    System.out.println("exp: "+c.getString(7));
                }

                Intent intent = new Intent(Registro1Profe.this, ListaAlus.class);
                intent.putExtra("nombre",nom);
                intent.putExtra("id",id);
                startActivity(intent);


            }
        });


    }
    public void onRadioButtonIdiomas (View v) {
// Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

// Check which radio button was clicked
        switch (v.getId()) {
            case R.id.castellano:
                if (checked)
                    idiomas = "castellano";
                break;
            case R.id.euskera:
                if (checked)
                    idiomas = "euskera";
                break;
            case R.id.ambos:
                if (checked)
                    idiomas = "ambos";
                break;
        }
    }
    /*public void clases_primaria(View v){
        primaria=v.findViewById(R.id.primaria);
        if (primaria.isSelected()){
            prim=true;
        }else{
            prim=false;
        }
    }
    public void clases_eso(View v){
        ESO=v.findViewById(R.id.ESO);
        if (ESO.isSelected()){
            eso=true;
        }else{
            eso=false;
        }
    }
    public void clases_bach(View v){
        bach=v.findViewById(R.id.bach);
        if (bach.isSelected()){
            bachi=true;
        }else{
            bachi=false;
        }
    }
    public void asig_primaria(View v){
        /*primaria=v.findViewById(R.id.primaria);
        if (primaria.isSelected()){
            DialogFragment dialogo= new DialogoAsignaturasPrimaria();
            dialogo.show(getSupportFragmentManager(),"primaria");

        /*}else{
            Toast.makeText(getApplicationContext(),"Seleciona la opcion primaria para poder ver las asignaturas",Toast.LENGTH_LONG);
        }
    }*/
    /*public void selAsigPrim(View v){
        DialogFragment dialogoprim= new DialogoAsignaturasPrimaria();
        dialogoprim.show(getSupportFragmentManager(), "primaria");
    }
    /*public void onPossitiveButtonClick(ArrayList<Integer> a){
        Integer aux;

        Iterator it = a.iterator();
        while(it.hasNext()){
            aux= (Integer) it.next();
            if (aux==0){
                asig=asig+"Lengua,";
            } else if (aux==1) {
                asig=asig+"Matemáticas,";
            }else if (aux==2){
                asig=asig+"Euskera,";
            } else if (aux==3) {
                asig=asig+"Inglés,";
            }else if (aux==4){
                asig=asig+"Ciencias de la naturaleza,";
            }else{
                asig=asig+"Ciencias sociales,";
            }

        }
    }*/
    /*public void registro_profe(View v){
        if(prim==true){
            System.out.println(asig);
        }
    }*/
}