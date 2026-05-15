package com.senai.atividaderecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.atividaderecyclerview.adapter.CursoAdapter;
import com.senai.atividaderecyclerview.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerCursos;

    private CursoAdapter cursoAdapter;

    private List<Curso> listaCursos;

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

        recyclerCursos = findViewById(R.id.recyclerCurso);

        listaCursos = new ArrayList<>();

        listaCursos.add(new Curso("Desenvolvimento de Sistemas", "1200 horas", "Estevão Ferreira"));
        listaCursos.add(new Curso("Mecatrônica", "1200 horas", "Cleisson"));
        listaCursos.add(new Curso("Automação Industrial","1200 horas","Jairo"));

        cursoAdapter = new CursoAdapter(listaCursos);
        recyclerCursos.setLayoutManager(new LinearLayoutManager(this));
        recyclerCursos.setAdapter(cursoAdapter);

    }


}