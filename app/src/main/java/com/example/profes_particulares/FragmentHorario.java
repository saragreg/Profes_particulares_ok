package com.example.profes_particulares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHorario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHorario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHorario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHorario.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHorario newInstance(String param1, String param2) {
        FragmentHorario fragment = new FragmentHorario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horario, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id=getArguments().getInt("id");
        System.out.println("el id del horario es:" + id);
        miBD GestorDB = new miBD(getContext(), "Profesores", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();
        String s[]={String.valueOf(id)};
        String selection = "idUsu LIKE ?"; // WHERE id LIKE ?

        Cursor c = bd.query(
                "Horario",
                null,
                selection,
                s,
                null,
                null,
                null
        );

        while (c.moveToNext()){
            //lunes
            if(c.getInt(2)==1){
                view.findViewById(R.id.lm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.lm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(3)==1){
                view.findViewById(R.id.lme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.lme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(4)==1){
                view.findViewById(R.id.lt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.lt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //martes
            if(c.getInt(5)==1){
                view.findViewById(R.id.mm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.mm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(6)==1){
                view.findViewById(R.id.mme).setBackgroundColor(0x008000);
            }else{
                view.findViewById(R.id.mme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(7)==1){
                view.findViewById(R.id.mt).setBackgroundColor(0x008000);
            }else{
                view.findViewById(R.id.mt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //miercoles
            if(c.getInt(8)==1){
                view.findViewById(R.id.xm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.xm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(9)==1){
                view.findViewById(R.id.xme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.xme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(10)==1){
                view.findViewById(R.id.xt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.xt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //jueves
            if(c.getInt(11)==1){
                view.findViewById(R.id.jm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.jm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(12)==1){
                view.findViewById(R.id.jme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.jme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(13)==1){
                view.findViewById(R.id.jt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.jt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //viernes
            if(c.getInt(14)==1){
                view.findViewById(R.id.vm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.vm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(15)==1){
                view.findViewById(R.id.vme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.vme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(16)==1){
                view.findViewById(R.id.vt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.vt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //sabado
            if(c.getInt(17)==1){
                view.findViewById(R.id.sm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.sm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(18)==1){
                view.findViewById(R.id.sme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.sme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(19)==1){
                view.findViewById(R.id.st).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.st).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            //domingo
            if(c.getInt(20)==1){
                view.findViewById(R.id.dm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.dm).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(21)==1){
                view.findViewById(R.id.dme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.dme).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
            if(c.getInt(22)==1){
                view.findViewById(R.id.dt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_rojo));
            }else{
                view.findViewById(R.id.dt).setBackgroundTintList(getContext().getResources().getColorStateList(R.color.boton_azul));
            }
        }


    }
}