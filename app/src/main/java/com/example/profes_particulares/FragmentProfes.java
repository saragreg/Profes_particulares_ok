package com.example.profes_particulares;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfes#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentProfes extends Fragment {


    public FragmentProfes() {
        // Required empty public constructor
    }

    public static FragmentHorario newInstance() {
        FragmentHorario fragment = new FragmentHorario();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profes, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView a=view.findViewById(R.id.textView17);
        String asignaturas=getArguments().getString("asig");
        a.setText(asignaturas);

        TextView c=view.findViewById(R.id.textView16);
        String curso=getArguments().getString("curso");
        c.setText(curso);

        TextView i=view.findViewById(R.id.textView15);
        String idiom=getArguments().getString("idioma");
        i.setText(idiom);

        TextView e=view.findViewById(R.id.textView14);
        int exp=getArguments().getInt("exp");

        e.setText(e.getText()+String.valueOf(exp));


    }
}