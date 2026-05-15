package com.senai.aulacadastroaluno;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.senai.aulacadastroaluno.adapter.AlunoAdapter;
import com.senai.aulacadastroaluno.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recycler;

    private List<Aluno> lista = new ArrayList<>();

    private AlunoAdapter adapter;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler = view.findViewById(R.id.recyclerAlunos);
        Button btnNovo = view.findViewById(R.id.btnNovo);

        adapter = new AlunoAdapter(lista, aluno ->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("aluno", aluno);
            Navigation.findNavController(view)
                    .navigate(R.id.action_home_to_detalhe, bundle);
        });

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        btnNovo.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_home_to_cadastro);
        });

        NavBackStackEntry backStackEntry = NavHostFragment.findNavController(this).getCurrentBackStackEntry();

        if (backStackEntry != null){

            SavedStateHandle savedStateHandle = backStackEntry.getSavedStateHandle();

            savedStateHandle.getLiveData("novoAluno").observe(getViewLifecycleOwner(), aluno -> {
                if (aluno != null){
                    lista.add((Aluno) aluno);
                    adapter.notifyDataSetChanged();

                    savedStateHandle.remove("novoAluno");
                }
            });

        }
    }
}