package com.example.profes_particulares;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaProfes extends AppCompatActivity {
    int id;
    String nom;
    ArrayList<String> noms=new ArrayList<String>();
    ArrayList<String> precios=new ArrayList<String>();
    ArrayList<String> asig=new ArrayList<String>();
    ArrayList<Integer> ids=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profes);
        id=getIntent().getExtras().getInt("id");
        nom=getIntent().getExtras().getString("nombre");

        miBD GestorDB = new miBD(getApplicationContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();

        TextView nombre=findViewById(R.id.textView10);
        nombre.setText("bienvenida de nuevo "+nom+" aquí están tus profesores más cercanos");

        String[] col={"nombre","precio","idUsu"};
        Cursor c = bd.query(
                "Profesor",
                col,
                null,
                null,
                null,
                null,
                null
        );
        while (c.moveToNext()){
            noms.add(c.getString(0));
            precios.add(c.getString(1));
            ids.add(c.getInt(2));
        }

        Float[] valoracion={3.2f,2.4f,4.6f,4.9f,3.0f,2.5f,3.7f,4.0f,4.3f,2.4f,3.6f};


        ListView lisprofes= (ListView) findViewById(R.id.listView);
        AdaptadorProfesLista eladap= new AdaptadorProfesLista(getApplicationContext(),noms,precios,valoracion);
        lisprofes.setAdapter(eladap);

        ListView lista= findViewById(R.id.listView);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                noms.remove(i);
                precios.remove(i);
                eladap.notifyDataSetChanged();
                return true;
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListaProfes.this, infoProfe.class);
                System.out.println("la val que se manda es: "+valoracion[i]);
                intent.putExtra("val",valoracion[i]);
                intent.putExtra("nombre",noms.get(i));
                intent.putExtra("id",ids.get(i));
                startActivityIntent.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            if (result.getResultCode() == RESULT_OK) {
            }
        }
    });

}