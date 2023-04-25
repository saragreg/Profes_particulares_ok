package com.example.profes_particulares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAlus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAlus extends Fragment {


    public FragmentAlus() {
        // Required empty public constructor
    }


    public static FragmentAlus newInstance() {
        FragmentAlus fragment = new FragmentAlus();
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
        return inflater.inflate(R.layout.fragment_alus, container, false);
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

    }
}