package com.senai.atividaderecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.atividaderecyclerview.R;
import com.senai.atividaderecyclerview.model.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private List<Curso> listaCursos;

    public CursoAdapter(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }


    @NonNull
    @Override
    public CursoAdapter.CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, parent, false);

        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoAdapter.CursoViewHolder holder, int position) {

        Curso curso = listaCursos.get(position);


        holder.txtNome.setText(curso.getCurso());
        holder.txtCarga.setText(curso.getCargaHoraria());
        holder.txtNomeProf.setText(curso.getNomeProf());


    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        TextView txtCarga;
        TextView txtNomeProf;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome);
            txtCarga = itemView.findViewById(R.id.txtCarga);
            txtNomeProf = itemView.findViewById(R.id.txtNomeProf);
        }
    }
}
