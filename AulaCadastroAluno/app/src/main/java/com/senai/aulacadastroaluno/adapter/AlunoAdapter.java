package com.senai.aulacadastroaluno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulacadastroaluno.R;
import com.senai.aulacadastroaluno.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.ViewHolder> {

    public interface OnItemClick{
        void OnClick(Aluno aluno);
    }

    private List<Aluno> lista;

    private OnItemClick listener;

    public AlunoAdapter(List<Aluno> lista, OnItemClick listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlunoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoAdapter.ViewHolder holder, int position) {
        Aluno aluno = lista.get(position);

        holder.txtNome.setText(aluno.getNome());
        holder.txtCurso.setText(aluno.getCurso());
        holder.imgAluno.setImageResource(aluno.getImagem());

        holder.itemView.setOnClickListener(v -> {
            listener.OnClick(aluno);
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCurso;
        ImageView imgAluno;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome);
            txtCurso = itemView.findViewById(R.id.txtCurso);
            imgAluno = itemView.findViewById(R.id.imgAluno);
        }
    }
}
