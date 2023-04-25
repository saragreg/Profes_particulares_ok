package com.example.profes_particulares;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {
    public miBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("se ha creado la base de datos");
        sqLiteDatabase.execSQL("CREATE TABLE Usuario ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'usu' VARCHAR(255), 'contraseña' VARCHAR(255), 'nombre' VARCHAR(255),'tel' VARCHAR(13),'perfil' VARCHAR(1))");
        //el valor de id se introduce solo porque se autoincrementa

        System.out.println("se ha creado la tabla usuario");
        sqLiteDatabase.execSQL("CREATE TABLE Profesor('idProfe' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'idUsu' INTEGER,'nombre' VARCHAR(255),'precio' VARCHAR(255),'asignaturas' VARCHAR(255),'cursos'VARCHAR(255),'idiomas' VARCHAR(255),'experiencia' INTEGER,'puntuacion' FLOAT DEFAULT(0.0),FOREIGN KEY ('idUsu') REFERENCES Usuarios('id'))");
        System.out.println("se ha creado la tabla profesore");
        sqLiteDatabase.execSQL("CREATE TABLE Alumnos('idAlu' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'idUsu' INTEGER,'nombre' VARCHAR(255),'asignaturas' VARCHAR(255),'cursos'VARCHAR(255),'idiomas' VARCHAR(255),FOREIGN KEY ('idUsu') REFERENCES Usuarios('id'))");
        System.out.println("se ha creado la tabla alumnos");
        sqLiteDatabase.execSQL("CREATE TABLE Horario('idHor' INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, 'idUsu' INTEGER,'lM' BOOLEAN DEFAULT (0) , 'lme' BOOLEAN DEFAULT (0), 'lt' BOOLEAN DEFAULT (0),'mM' BOOLEAN DEFAULT (0), 'mme' BOOLEAN DEFAULT (0), 'mt' BOOLEAN DEFAULT (0),'xM' BOOLEAN DEFAULT (0), 'xme' BOOLEAN DEFAULT (0), 'xt' BOOLEAN DEFAULT (0),'jM' BOOLEAN DEFAULT (0), 'jme' BOOLEAN DEFAULT (0), 'jt' BOOLEAN DEFAULT (0),'vM' BOOLEAN DEFAULT (0), 'vme' BOOLEAN DEFAULT (0), 'vt' BOOLEAN DEFAULT (0),'sM' BOOLEAN DEFAULT (0), 'sme' BOOLEAN DEFAULT (0), 'st' BOOLEAN DEFAULT (0),'dM' BOOLEAN DEFAULT (0), 'dme' BOOLEAN DEFAULT (0), 'dt' BOOLEAN DEFAULT (0),FOREIGN KEY ('idUsu') REFERENCES Usuarios('id'))");
        System.out.println("se ha creado la tabla horario");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
