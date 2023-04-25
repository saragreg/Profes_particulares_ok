package com.example.profes_particulares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorAlu extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<String> nombres;
    private ArrayList<String> cursos;
    public AdaptadorAlu(Context pcontext, ArrayList<String> pnombres, ArrayList<String> pcursos)
    {
        contexto = pcontext;
        nombres = pnombres;
        cursos=pcursos;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int i) {
        return nombres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.adaptador_alus,null);
        TextView nombre= (TextView) view.findViewById(R.id.nombre);
        TextView curso=(TextView) view.findViewById(R.id.curso);
        nombre.setText(nombres.get(i));
        curso.setText(cursos.get(i));
        return view;
    }
}
