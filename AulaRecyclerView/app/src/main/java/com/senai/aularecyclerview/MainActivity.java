package com.senai.aularecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aularecyclerview.adapter.AlunoAdapter;
import com.senai.aularecyclerview.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerAlunos;

    private AlunoAdapter alunoAdapter;
    private List<Aluno> listaALunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerAlunos = findViewById(R.id.recyclerAlunos);

        listaALunos = new ArrayList<>();

        listaALunos.add(new Aluno("Giovana Frade", "Desenvolvimento de Sistemas", R.drawable.ic_launcher_foreground));
        listaALunos.add(new Aluno("Lara Moreira", "Desenvolvimento de Sistemas", R.drawable.ic_launcher_foreground));

        alunoAdapter = new AlunoAdapter(listaALunos);

        recyclerAlunos.setLayoutManager(new LinearLayoutManager(this));
        recyclerAlunos.setAdapter(alunoAdapter);

    }
}