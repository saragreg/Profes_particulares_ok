package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Registro1Alumno extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText asignaturas;
    EditText año;

    String asig = "";
    String curso = "";
    String idiomas = "";
    String nom;
    int id;
    boolean entrar=true;
    String[] array_cursos = { "primaria", "ESO", "Bachiller"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1_alumno);
        nom = getIntent().getExtras().getString("nombre");
        id = getIntent().getExtras().getInt("id");

        //vamos a definir el spinner de los cursos a elegir
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,array_cursos);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);



        miBD GestorDB = new miBD(this, "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();

        Cursor cu = bd.rawQuery("SELECT * FROM Usuario",null);
        while(cu.moveToNext()){
            int i=0;
            while(i<6){
                System.out.println(cu.getString(i));
                i++;
            }

        }
        Cursor c = bd.rawQuery("SELECT * FROM Alumnos", null);
        //this.deleteDatabase("Profesores");

        while (c.moveToNext()) {
            System.out.println(c.getInt(0) + " " + c.getString(1));
        }

        Button regal = findViewById(R.id.registroA);
        regal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recogemos todos los valores de los edit text

                asignaturas = findViewById(R.id.editTextTextPersonName9);
                asig = asignaturas.getText().toString();

                año= findViewById(R.id.editTextNumber);
                String anio=año.getText().toString();
                if (anio.equals("")){
                    Toast.makeText(getApplicationContext(),"Debes rellenar el campo del año del curso",Toast.LENGTH_SHORT);
                    entrar=false;
                }else{
                    curso=curso+" "+anio;
                }
                if(asig.equals("")){
                    Toast.makeText(getApplicationContext(),"Debes rellenar el campo de asignaturas",Toast.LENGTH_SHORT);
                    entrar=false;
                }else if (idiomas.equals("")){
                    Toast.makeText(getApplicationContext(),"Debes rellenar el campo de idiomas",Toast.LENGTH_SHORT);
                    entrar=false;
                }
                if (entrar) {
                    ContentValues cv = new ContentValues();


                    //el valor de id se introduce solo porque se autoincrementa
                    cv.put("idUsu", id);
                    cv.put("nombre", nom);
                    cv.put("asignaturas", asig);
                    cv.put("cursos", curso);
                    cv.put("idiomas", idiomas);

                    bd.insert("Alumnos", null, cv);

                    Cursor c = bd.query(
                            "Alumnos",
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    );
                    while (c.moveToNext()) {
                        System.out.println(c.getString(0));
                        System.out.println(c.getString(1));
                        System.out.println(c.getString(2));
                        System.out.println(c.getString(3));
                        System.out.println(c.getString(4));
                    }

                    Intent intent = new Intent(Registro1Alumno.this, ListaProfes.class);
                    intent.putExtra("nombre", nom);
                    intent.putExtra("id", id);//enviamos el id del usuario para trabajar con el
                    startActivity(intent);
                }

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

    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        curso=array_cursos[pos];
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Toast.makeText(getApplicationContext(),"debe seleccionar un curso", Toast.LENGTH_LONG);
    }

    /*MIRAR SPIN PARA ELEGIR UN CURSO CONCRETO
//CAMBIAR EL RADIOBUTTON POR UN SPIN Y EL NIMERO DEL CURSO DE INTRODUCIR NUMERO Y HACER IFS
    public void onRadioButtonCursos(View view) {
// Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

// Check which radio button was clicked
        switch (view.getId()) {
            case R.id.castellano:
                if (checked)
                    curso = "primaria";
                break;
            case R.id.euskera:
                if (checked)
                    curso = "ESO";
                break;
            case R.id.ambos:
                if (checked)
                    curso = "bach";
                break;
        }
    }*/
}

