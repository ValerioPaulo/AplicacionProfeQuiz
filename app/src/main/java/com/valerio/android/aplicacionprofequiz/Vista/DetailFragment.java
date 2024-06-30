package com.valerio.android.aplicacionprofequiz.Vista;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.Vista.modelo.Top;

public class DetailFragment extends Fragment {



        public DetailFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_perfil_docente, container, false);
        }

   /* private static final String ARG_TOP = "top";

    // Método para crear una nueva instancia del fragmento con un objeto Top como argumento
    public static DetailFragment newInstance(Top top) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TOP, top);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_perfil_docente, container, false);

        // Obtener el objeto Top del argumento del fragmento
        Bundle args = getArguments();
        if (args != null) {
            Top top = (Top) args.getSerializable(ARG_TOP);

            // Asignar los valores del objeto Top a los elementos de la vista
            TextView nombreCompleto = view.findViewById(R.id.NombreDato);
            TextView nombreCompleto1 = view.findViewById(R.id.NombreCompletoDato);
            TextView correoProf = view.findViewById(R.id.CorreoDato);
            ImageView fotoProf = view.findViewById(R.id.fot);
            TextView carreraProf = view.findViewById(R.id.CarreraDato);
            TextView calificacion = view.findViewById(R.id.cal);

            nombreCompleto.setText(top.getNombre_completo());
            nombreCompleto1.setText(top.getNombre_completo());
            correoProf.setText(top.getCorreo_prof());
            Glide.with(requireContext()).load(top.getFot_prof()).into(fotoProf);
            carreraProf.setText(top.getCarrera_prof());
            calificacion.setText(top.getCalificacion());
        }

        return view;
    }

    */

}

