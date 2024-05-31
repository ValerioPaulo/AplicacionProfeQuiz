package com.valerio.android.aplicacionprofequiz.Vista.adaptadore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.Vista.modelo.Top;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private List<Top> top;
    private Context context;

    public TopAdapter(List<Top> top, Context context) {
        this.top = top;
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
        Glide.with(context).load(top.get(position).getFot_prof()).into(holder.pfot);
    holder.pcod.setText(top.get(position).getCod_prof());
    holder.pnomape.setText(top.get(position).getNombre_completo());
    holder.pcorreo.setText(top.get(position).getCorreo_prof());
    holder.pcarrera.setText(top.get(position).getCarrera_prof());
    holder.pcalificacion.setText(top.get(position).getCalificacion());
    }

    @Override
    public int getItemCount() {
        return top.size();
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

            pfot=itemView.findViewById(R.id.pfot);
            pcod=itemView.findViewById(R.id.pcod);
            pnomape=itemView.findViewById(R.id.pnomape);
            pcorreo=itemView.findViewById(R.id.pcorreo);
            pcarrera=itemView.findViewById(R.id.pcarrera);
            pcalificacion=itemView.findViewById(R.id.pcalificacion);
        }
    }
}
