package com.example.extraordinario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorGanadores extends RecyclerView.Adapter<AdaptadorGanadores.ViewHolder> {
    List<nombresGanadores> ganadores;
    Context context;

    public AdaptadorGanadores(List<nombresGanadores> ganadores) {
        this.ganadores = ganadores;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorGanadores.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_ganadores, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorGanadores.ViewHolder holder, int position) {
        holder.setData(ganadores.get(position));
    }

    @Override
    public int getItemCount() {
        return ganadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, numero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombre);
            numero = itemView.findViewById(R.id.txtNumero);
        }

        public void setData(nombresGanadores intent) {
            nombre.setText("" + intent.getNombre());
            numero.setText("" + intent.getNumero());
        }
    }
}
