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

    private List<Top> top;
    private List<Top> topFull; // Lista completa para restaurar después de la búsqueda
    private Context context;

    public TopAdapter(List<Top> top, Context context) {
        this.top = top;
        this.topFull = new ArrayList<>(top); // Copia profunda de la lista
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Top currentTop = top.get(position);

        Glide.with(context).load(top.get(position).getFot_prof()).into(holder.pfot);
        holder.pcod.setText(top.get(position).getCod_prof());
        holder.pnomape.setText(top.get(position).getNombre_completo());
        holder.pcorreo.setText(top.get(position).getCorreo_prof());
        holder.pcarrera.setText(top.get(position).getCarrera_prof());
        holder.pcalificacion.setText(top.get(position).getCalificacion());

        /*

        holder.itemView.setOnClickListener(v -> {
            // Obtener el contexto desde la vista
            Context context = v.getContext();

            // Crear un intent para abrir DetailActivity
            Intent intent = new Intent(context, DetailActivity.class);

            // Pasar el objeto Top como extra en el intent
            intent.putExtra("top", currentTop);

            // Iniciar DetailActivity
            context.startActivity(intent);
        });*/
        holder.itemView.setOnClickListener(v -> {
            // Obtener el contexto desde la vista
            Context context = v.getContext();

            // Obtener el FragmentManager de la actividad
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

            // Crear una instancia del Fragment de detalle y pasar el objeto Top como argumento
            DetailFragment detailFragment = DetailFragment.newInstance(currentTop);

            // Reemplazar el contenido actual con el Fragment de detalle
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, detailFragment)
                    .addToBackStack(null)
                    .commit();



        });




    }

    @Override
    public int getItemCount() {
        return top.size();
    }
    // Método para filtrar la lista




    // Método para filtrar la lista
    public void filter(String query) {
        if (TextUtils.isEmpty(query)) {
            top = new ArrayList<>(topFull);
        } else {
            String normalizedQuery = normalizeString(query);
            List<Top> filteredList = new ArrayList<>();
            for (Top item : topFull) {
                if (normalizeString(item.getNombre_completo()).contains(normalizedQuery)) {
                    filteredList.add(item);
                }
            }
            top = filteredList;
        }
        notifyDataSetChanged();
    }

    // Método para normalizar cadenas
    private String normalizeString(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pfot;
        private TextView pcod;
        private TextView pnomape;
        private TextView pcorreo;
        private TextView pcarrera;
        private TextView pcalificacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pfot = itemView.findViewById(R.id.pfot);
            pcod = itemView.findViewById(R.id.pcod);
            pnomape = itemView.findViewById(R.id.pnomape);
            pcorreo = itemView.findViewById(R.id.pcorreo);
            pcarrera = itemView.findViewById(R.id.pcarrera);
            pcalificacion = itemView.findViewById(R.id.pcalificacion);
        }
    }
}