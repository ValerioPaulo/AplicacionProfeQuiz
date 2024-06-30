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
import android.widget.Filter;
import android.widget.Filterable;
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
    private List<Top> topFiltered;
    private Context context;

    public TopAdapter(List<Top> top, Context context) {
        this.top = top;
        this.topFiltered = new ArrayList<>(top);
        this.context = context;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        Top item = topFiltered.get(position);
        holder.cod.setText(item.getCod_prof());
        holder.nom.setText(item.getNombre_completo());
        holder.correo.setText(item.getCorreo_prof());
        holder.calificacion.setText(item.getCalificacion());
    }

    @Override
    public int getItemCount() {
        return topFiltered.size();
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Top> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Si no hay filtro, muestra todos los datos
                    filteredList.addAll(top);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Top item : top) {
                        String itemName = item.getNombre_completo().toLowerCase();
                        if (itemName.contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                topFiltered.clear();
                if (results.values != null) {
                    topFiltered.addAll((List<Top>) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cod;
        private TextView nom;
        private TextView correo;
        private TextView calificacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cod = itemView.findViewById(R.id.pcod);
            nom = itemView.findViewById(R.id.pnomape);
            correo = itemView.findViewById(R.id.pcorreo);
            calificacion = itemView.findViewById(R.id.pcalificacion);
        }
    }
}