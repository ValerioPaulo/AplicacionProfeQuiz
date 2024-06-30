package com.valerio.android.aplicacionprofequiz.Vista.adaptadore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.Vista.DetailActivity;
import com.valerio.android.aplicacionprofequiz.Vista.DetailFragment;
import com.valerio.android.aplicacionprofequiz.Vista.MainActivity;
import com.valerio.android.aplicacionprofequiz.Vista.modelo.Top;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {
    private  List<Top> top;
    private  Context context;

    public TopAdapter(List<Top> top, Context context) {
        this.top = top;
        this.context = context;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        holder.cod.setText(top.get(position).getCod_prof());
        holder.nom.setText(top.get(position).getNombre_completo());
        holder.correo.setText(top.get(position).getCorreo_prof());
       // holder.curso.setText(top.get(position).getCurso_prof());
        holder.calificacion.setText(top.get(position).getCalificacion());

    }

    @Override
    public int getItemCount() {
        return top.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cod;
        private TextView nom;
        private TextView correo;
        private TextView curso;
        private TextView calificacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cod=itemView.findViewById(R.id.pcod);
            nom=itemView.findViewById(R.id.pnomape);
            correo=itemView.findViewById(R.id.pcorreo);
          //  curso=itemView.findViewById(R.id.pcurso);
            calificacion=itemView.findViewById(R.id.pcalificacion);
        }
    }
}