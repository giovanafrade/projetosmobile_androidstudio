package com.senai.aulacrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulacrud.DAO.AlunoDAO;
import com.senai.aulacrud.database.AppDataBase;
import com.senai.aulacrud.model.Aluno;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerAlunos;
    private Button btnNovoAluno;

    private AppDataBase db;
    private List<Aluno> listaAlunos;
    private AlunoAdapter alunoAdapter;

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
        //Liga os componentes do XML com o Java
        recyclerAlunos = findViewById(R.id.recyclerAlunos);
        btnNovoAluno = findViewById(R.id.btnNovoAluno);

        //pega a instancua do banco
        db = AppDataBase.getDatabase(this);

        //quando clicar no botão, abre a tela de cadastro
        btnNovoAluno.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        carregarAlunos();
    }

    private void carregarAlunos(){
        //busca os alunos salvos no banco de dados
        listaAlunos = db.alunoDAO().listarTodos();

        //criar o adapter e define o que acontece ao editar ou excluir
        alunoAdapter = new AlunoAdapter(listaAlunos, new AlunoAdapter.OnAlunoActionListener() {
            @Override
            public void onEditar(Aluno aluno) {
                //abra a tela de cadastro, mas enviando o ID do aluno
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                Intent.putExtra("alunoId", aluno.getId());
                startActivity(intent);
            }

            @Override
            public void onExcluir(Aluno aluno) {
                //exclui o aluno do banco
                db.alunoDAO().excluir(aluno);
                //recarrega a lista de alunos
                carregarAlunos();
            }
        });

        //Definir que a lista será vertical

        recyclerAlunos.setLayoutManager(new LinearLayoutManager(this));

        //Ligar o RecyclerView ao Adapter
        recyclerAlunos.setAdapter(alunoAdapter);

    }


    @Override
    protected void onResume(){
        super.onResume();
        //sempre que voltar para essa tela, recarrega a lista
        //isso é útil depois de cadastrar, editar ou excluir
        carregarAlunos();
    }
}