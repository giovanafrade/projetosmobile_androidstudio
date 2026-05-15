package com.senai.aulacadastroaluno;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.senai.aulacadastroaluno.model.Aluno;


public class DetalheFragment extends Fragment {

    public DetalheFragment() {
        super(R.layout.fragment_detalhe);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nome = view.findViewById(R.id.txtNome);
        TextView curso = view.findViewById(R.id.txtCurso);
        ImageView img = view.findViewById(R.id.imgDetalhe);

        Aluno aluno = (Aluno) getArguments().getSerializable("aluno");

        if (aluno != null){
            nome.setText(aluno.getNome());
            curso.setText(aluno.getCurso());
            img.setImageResource(aluno.getImagem());
        }

    }

}