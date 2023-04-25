package com.example.profes_particulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistroHorario extends AppCompatActivity {
    Button horario;
    boolean lm;
    boolean lme;
    boolean lt;
    boolean mm;
    boolean mme;
    boolean mt;
    boolean xm;
    boolean xme;
    boolean xt;
    boolean jm;
    boolean jme;
    boolean jt;
    boolean vm;
    boolean vme;
    boolean vt;
    boolean sm;
    boolean sme;
    boolean st;
    boolean dm;
    boolean dme;
    boolean dt;
    String nom;
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_horario);
        nom=getIntent().getExtras().getString("nombre");
        id=getIntent().getExtras().getInt("id");
        System.out.println("al horario ha llegado el id: " + id);


    }
    public void registrarse(View v){
        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //el valor de id se introduce solo porque se autoincrementa
        //poner de default el valor false en labase de datos
        cv.put("idUsu",id);
        //lunes
        if(lm==true){
            cv.put("lm",true);
        }
        if(lme==true){
            cv.put("lme",true);
        }
        if(lt==true){
            cv.put("lt",true);
        }
        //martes
        if(mm==true){
            cv.put("mm",true);
        }
        if(mme==true){
            cv.put("mme",true);
        }
        if(mt==true){
            cv.put("mt",true);
        }
        //miercoles
        if(xm==true){
            cv.put("xm",true);
        }
        if(xme==true){
            cv.put("xme",true);
        }
        if(xt==true){
            cv.put("xt",true);
        }
        //jueves
        if(jm==true){
            cv.put("jm",true);
        }
        if(jme==true){
            cv.put("jme",true);
        }
        if(jt==true){
            cv.put("jt",true);
        }
        //viernes
        if(vm==true){
            cv.put("vm",true);
        }
        if(vme==true){
            cv.put("vme",true);
        }
        if(vt==true){
            cv.put("vt",true);
        }
        //sabado
        if(sm==true){
            cv.put("sm",true);
        }
        if(sme==true){
            cv.put("sme",true);
        }
        if(st==true){
            cv.put("st",true);
        }
        //domingo
        if(dm==true){
            cv.put("dm",true);
        }
        if(dme==true){
            cv.put("dme",true);
        }
        if(dt==true){
            cv.put("dt",true);
        }


        bd.insert("Horario", null, cv);

        //miramos la base de datos que lo haya creado bien
        Cursor c = bd.query(
                "Horario",
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (c.moveToNext()) {
            System.out.println("id: "+c.getString(0));
            System.out.println("idUsu: "+c.getString(1));
            System.out.println("lm: "+c.getString(2));
            System.out.println("lme: "+c.getString(3));
            System.out.println("lt: "+c.getString(4));
            System.out.println("mm: "+c.getString(5));
            System.out.println("mme: "+c.getString(6));
        }

        Intent intent = new Intent(RegistroHorario.this, RegisterOpciones.class);
        intent.putExtra("nombre",nom);
        intent.putExtra("id",id);
        startActivity(intent);

    }
    public void onclickLun(View v){
        switch(v.getId()) {
            case R.id.lm:
                if (lm == true) {
                    //poner fondo azul porque no puede
                    findViewById(R.id.lm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    lm = false;
                } else {
                    findViewById(R.id.lm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    //poner fondo verde porque puede
                    lm = true;
                }
                break;
            case R.id.lme:
                if (lme == true){
                    lme = false;
                    findViewById(R.id.lme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                }
                else {
                    lme = true;
                    findViewById(R.id.lme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));

                }
                break;
            case R.id.lt:
                if (lt == true) {
                    lt = false;
                    findViewById(R.id.lt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));

                }
                else {
                    lt = true;
                    findViewById(R.id.lt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));

                }
                break;
        }
    }
    public void onclickMar(View v){
        switch(v.getId()) {
            case R.id.mm:
                if (mm == true){
                    mm = false;
                    findViewById(R.id.mm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));

                }else {
                    mm = true;
                    findViewById(R.id.mm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));

                }break;
            case R.id.mme:
                if (mme==true){
                    findViewById(R.id.mme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    mme=false;
                }
                else {
                    findViewById(R.id.mme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    mme = true;
                }break;
            case R.id.mt:
                if (mt==true) {
                    findViewById(R.id.mt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));

                    mt = false;
                }else {
                    findViewById(R.id.mt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    mt = true;
                }break;
        }
    }
    public void onclickMie(View v){
        switch(v.getId()) {
            case R.id.xm:
                if (xm==true){

                    findViewById(R.id.xm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    xm=false;
                }else {

                    findViewById(R.id.xm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    xm = true;
                }break;
            case R.id.xme:
                if (xme==true){

                    findViewById(R.id.xme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    xme=false;
                }else {
                    findViewById(R.id.xme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    xme = true;
                }break;
            case R.id.xt:
                if (xt==true){
                    findViewById(R.id.xt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    xt=false;
                }else {
                    findViewById(R.id.xt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    xt = true;
                }break;
        }
    }
    public void onclickJue(View v){
        switch(v.getId()) {
            case R.id.jm:
                if (jm==true) {
                    findViewById(R.id.jm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    jm = false;
                }else {
                        findViewById(R.id.jm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        jm = true;
                }break;
            case R.id.jme:
                if (jme==true){
                    findViewById(R.id.jme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    jme=false;
                }else {
                    findViewById(R.id.jme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    jme = true;
                }break;
            case R.id.jt:
                if (jt==true){
                    findViewById(R.id.jt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                    jt=false;
                }else {
                    findViewById(R.id.jt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                    jt = true;
                }break;
            }
        }
        public void onclickVie(View v){
            switch(v.getId()) {
                case R.id.vm:
                    if (vm==true){
                        findViewById(R.id.vm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        vm=false;
                    }else {
                        findViewById(R.id.vm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        vm = true;
                    }break;
                case R.id.vme:
                    if (vme==true){
                        findViewById(R.id.vme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        vme=false;
                    }else {
                        findViewById(R.id.vme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        vme = true;
                    }break;
                case R.id.vt:
                    if (vt==true){
                        findViewById(R.id.vt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        vt=false;
                    }else {
                        findViewById(R.id.vt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        vt = true;
                    }break;
            }
        }
        public void onclickSab(View v){
            switch(v.getId()) {
                case R.id.sm:
                    if (sm==true){
                        findViewById(R.id.sm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        sm=false;
                    }else {
                        findViewById(R.id.sm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        sm = true;
                    }break;
                case R.id.sme:
                    if (sme==true){
                        findViewById(R.id.sme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        sme=false;
                    }else {
                        findViewById(R.id.sme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        sme = true;
                    }break;
                case R.id.st:
                    if (st==true){
                        findViewById(R.id.st).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        st=false;
                    }else {
                        findViewById(R.id.st).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        st = true;
                    }break;
            }
        }

        public void onclickDom(View v){
            switch(v.getId()) {
                case R.id.dm:
                    if (dm==true){
                        findViewById(R.id.dm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        dm=false;
                    }else {
                        findViewById(R.id.dm).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        dm = true;
                    }break;
                case R.id.dme:
                    if (dme==true){
                        findViewById(R.id.dme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        dme=false;
                    }else {
                        findViewById(R.id.dme).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        dme = true;
                    }break;
                case R.id.dt:
                    if (dt==true){
                        findViewById(R.id.dt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_azul));
                        dt=false;
                    }else {
                        findViewById(R.id.dt).setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.boton_rojo));
                        dt = true;
                    }break;
            }
        }
    }
