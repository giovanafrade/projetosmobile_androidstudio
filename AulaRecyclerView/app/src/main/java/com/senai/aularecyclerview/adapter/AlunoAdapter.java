package com.senai.aularecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aularecyclerview.R;
import com.senai.aularecyclerview.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> listaAlunos;

    public AlunoAdapter (List<Aluno> listaAlunos){
        this.listaAlunos = listaAlunos;
    }

    @NonNull
    @Override
    public AlunoAdapter.AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);

        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoAdapter.AlunoViewHolder holder, int position) {

        Aluno aluno = listaAlunos.get(position);

        holder.txtNome.setText(aluno.getNome());
        holder.txtCurso.setText(aluno.getCurso());
        holder.imgAluno.setImageResource(aluno.getImagem());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Aluno" + aluno.getNome(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome;

        TextView txtCurso;

        ImageView imgAluno;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome);
            txtCurso = itemView.findViewById(R.id.txtCurso);
            imgAluno = itemView.findViewById(R.id.imgAluno);
        }
    }


}
