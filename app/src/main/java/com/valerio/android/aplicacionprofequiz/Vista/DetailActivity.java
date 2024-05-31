package com.valerio.android.aplicacionprofequiz.Vista;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.Vista.modelo.Top;

public class DetailActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Top top = (Top) getIntent().getSerializableExtra("top"); // Obtener el objeto Top del intent

        TextView nombreCompleto = findViewById(R.id.nombre_completo);
        TextView correoProf = findViewById(R.id.correo_prof);
        ImageView fotoProf = findViewById(R.id.foto_prof);
        TextView carreraProf = findViewById(R.id.carrera_prof);
        TextView calificacion = findViewById(R.id.calificacion);

        // Asignar valores a los elementos de la vista

        nombreCompleto.setText(top.getNombre_completo());
        correoProf.setText(top.getCorreo_prof());
        Glide.with(this).load(top.getFot_prof()).into(fotoProf);
        carreraProf.setText(top.getCarrera_prof());
        calificacion.setText(top.getCalificacion());
    }
}
