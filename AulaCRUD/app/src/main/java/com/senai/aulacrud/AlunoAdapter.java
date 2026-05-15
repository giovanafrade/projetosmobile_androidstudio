package com.senai.aulacrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulacrud.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {


    public interface OnAlunoActionListener{

        void onEditar(Aluno aluno);
        void onExcluir(Aluno aluno);
    }

    private List<Aluno> listaAlunos;
    private OnAlunoActionListener listener;
    public AlunoAdapter(List<Aluno> listaAlunos, OnAlunoActionListener listener) {
        this.listaAlunos = listaAlunos;
        this.listener = listener;
    }


    //Cria um ViewHolder
    @NonNull
    @Override
    public AlunoAdapter.AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false)

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoAdapter.AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.txtNome.setText(aluno.getNome());
        holder.txtCurso.setText((aluno.getCurso()));
        holder.txtIdade.setText(String.valueOf(aluno.getIdade()));

        holder.btnEditar.setOnClickListener(v ->{
            listener.onEditar(aluno);
        });

        holder.btnExcluir.setOnClickListener(v ->{
            listener.onExcluir(aluno);
        });
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome;
        TextView txtCurso;
        TextView txtIdade;
        Button btnEditar;
        Button btnExcluir;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome=itemView.findViewById(R.id.txtNome);
            txtCurso=itemView.findViewById(R.id.txtCurso);
            txtIdade=itemView.findViewById(R.id.txtIdade);
            btnEditar=itemView.findViewById(R.id.btnEditar);
            btnExcluir=itemView.findViewById(R.id.btnExcluir);
        }
    }

}
