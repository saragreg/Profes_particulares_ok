package com.example.profes_particulares;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroGeneral extends AppCompatActivity {
    EditText u;
    EditText c;
    EditText c2;
    EditText n;
    EditText t;
    String usu;
    String contra;
    String contra2;
    String nom;
    String tel;
    int id;
    int i=0;
    boolean entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        miBD GestorDB = new miBD(this, "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();
        //this.deleteDatabase("Profesores");


        Button b =findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar=true;
                //String [] s= {usu,contra,nom};
                //bd.execSQL("INSERT INTO Usuarios('Usuario','Contraseña','Nombre') VALUES (?)",s);

                //recogemos todos los valores de los edit text
                u=findViewById(R.id.editTextTextPersonName2);
                c=findViewById(R.id.editTextTextPassword2);
                c2=findViewById(R.id.editTextTextPassword4);
                n=findViewById(R.id.editTextTextPersonName4);
                t=findViewById(R.id.editTextPhone);
                usu=u.getText().toString();
                contra=c.getText().toString();
                contra2=c2.getText().toString();
                nom=n.getText().toString();
                tel=t.getText().toString();

                //comprobamos que no hay ninguno vacio
                if (usu.equals("")){
                    Toast.makeText(getApplicationContext(),"El Usuario no puede estar vacio",Toast.LENGTH_SHORT).show();
                    entrar=false;
                } else if (contra.equals("") || contra.length()<8 || contra2.equals("")){
                    Toast.makeText(getApplicationContext(), "Las contraseñas son incorrectas", Toast.LENGTH_SHORT).show();
                    entrar = false;
                }else if(!contra.equals(contra2)){//comprobamos que las dos contraseñas son iguales
                    Toast.makeText(getApplicationContext(),"Las contraseñas son distintas",Toast.LENGTH_SHORT).show();
                    entrar=false;
                } else if (nom.equals("")) {
                    Toast.makeText(getApplicationContext(),"El nombre no puede estar vacio",Toast.LENGTH_SHORT).show();
                    entrar=false;
                } else if (tel.equals("")) {
                    Toast.makeText(getApplicationContext(),"El telefono no puede estar vacio",Toast.LENGTH_SHORT).show();
                    entrar=false;
                }else if(tel.length()!=9){
                    Toast.makeText(getApplicationContext(),"El número de telefono es incorrecto",Toast.LENGTH_SHORT).show();
                    entrar=false;
                }
                if (entrar==true) {
                    //es correcto el registro y se insertan los valores
                    ContentValues cv = new ContentValues();
                    //el valor de id se introduce solo porque se autoincrementa
                    cv.put("usu", usu);
                    cv.put("contraseña", contra);
                    cv.put("nombre", nom);
                    cv.put("tel", tel);
                    cv.put("perfil","?");
                    bd.insert("Usuario", null, cv);
                    String s[]={tel,usu,contra};
                    String columns[] = {"id","nombre"};
                    String selection = "tel LIKE ? AND usu LIKE ? AND contraseña LIKE ?"; // WHERE id LIKE ?//el telefono puede repetirse

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
                        System.out.println(id);
                        nom=c.getString(1);
                    }
                    Cursor cu = bd.rawQuery("SELECT * FROM Usuario",null);
                    while(cu.moveToNext()){
                        int i=0;
                        while(i<6){
                            System.out.println(cu.getString(i));
                            i++;
                        }

                    }

                    Intent intent = new Intent(RegistroGeneral.this, RegistroHorario.class);
                    intent.putExtra("id",id);//enviamos el id del usuario para trabajar con el
                    intent.putExtra("nombre",nom);
                    startActivity(intent);
                    bd.close();
                    c.close();
                    cu.close();
                    //vamos a mandar una notificacion de bienvenida por haberse registrado como profe o alumno
                    //pedimos permiso para enviar notificaciones
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        //PEDIR EL PERMISO
                        ActivityCompat.requestPermissions(RegistroGeneral.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 11);
                    }
                    NotificationManager elManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(getApplicationContext(), "IdCanal");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal", NotificationManager.IMPORTANCE_DEFAULT);
                        elManager.createNotificationChannel(elCanal);
                        elBuilder.setSmallIcon(R.drawable.elefante_fondo)
                                .setContentTitle("Bienvenido a elliProfes!")
                                .setContentText("Gracias por registrarte para buscar tus profes favoritos.")
                                .setSubText("Información extra")
                                .setVibrate(new long[]{0, 1000, 500, 1000})
                                .setAutoCancel(true);
                        elCanal.setDescription("Descripción del canal");
                        elCanal.enableLights(true);
                        elCanal.setLightColor(Color.RED);
                        elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                        elCanal.enableVibration(true);
                    }
                    elManager.notify(1, elBuilder.build());
                }
            }
        });

    }
    public void infoContra(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información sobre contraseña");
        builder.setMessage("La contraseña debe tener una longitud mínima de 8 caracteres.");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void infoTel(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información sobre el número de teléfono");
        builder.setMessage("El número debe tener una longitud de 9 dígitos. Solo se incluirán dígitos. No se debe poner el prefijo.");
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}