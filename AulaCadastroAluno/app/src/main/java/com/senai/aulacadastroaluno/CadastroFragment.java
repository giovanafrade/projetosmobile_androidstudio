package com.senai.aulacadastroaluno;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.senai.aulacadastroaluno.model.Aluno;

public class CadastroFragment extends Fragment {

    public CadastroFragment(){
        super(R.layout.fragment_cadastro);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edtNome = view.findViewById(R.id.edtNome);
        EditText edtCurso = view.findViewById(R.id.edtCurso);
        Button btnSalvar = view.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            Aluno aluno = new Aluno(edtNome.getText().toString(),
                    edtCurso.getText().toString(),
                    R.drawable.ic_launcher_foreground);

            NavController navController = Navigation.findNavController(view);

            navController.getPreviousBackStackEntry().getSavedStateHandle().set("novoAluno", aluno);
            //Mandando para a última tela que foi aberta, e vai mandar as informações (do aluno)

            navController.popBackStack();
        });
    }
}