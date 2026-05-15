package com.senai.aulacrud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.senai.aulacrud.database.AppDataBase;
import com.senai.aulacrud.model.Aluno;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtCurso, edtIdade;
    private Button btnSalvar;
    private AppDataBase db;
    private Aluno alunoEditando = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //liga os components do xml com o java
        edtNome = findViewById(R.id.edtNome);
        edtCurso = findViewById(R.id.edtCurso);
        edtIdade = findViewById(R.id.edtIdade);
        btnSalvar = findViewById(R.id.btnSalvar);

        //pegar instância do banco
        db = AppDataBase.getDatabase(this);

        //verificar se veio um id pela intent
        int alunoId = getIntent().getIntExtra("alunoId", -1);


        //se veio um ID válido, significa que estamos editando
        if (alunoId != -1){
            for (Aluno a: db.alunoDAO().listarTodos()){
                if (a.getId() == alunoId){
                    alunoEditando = a;
                    break;
                }
            }
        }

        //preeenche os campos com dados do aluno caso esteja editando
        if (alunoEditando != null){
            edtNome.setText(alunoEditando.getNome());
            edtCurso.setText(alunoEditando.getCurso());
            edtIdade.setText(String.valueOf(alunoEditando.getIdade()));
        }

        btnSalvar.setOnClickListener(v->{
            String nome = edtNome.getText().toString().trim();
            String curso = edtCurso.getText().toString().trim();
            String idadeTexto = edtIdade.getText().toString().trim();

            //validação simples
            if (nome.isEmpty()){
                edtNome.setError("Informe o nome:");
                return;
            }

            if (curso.isEmpty()){
                edtCurso.setError("Informe o curso:");
                return;
            }

            if (idadeTexto.isEmpty()){
                edtIdade.setError("Informe a idade:");
                return;
            }

            int idade = Integer.valueOf(idadeTexto);

            //se alunoEditando = null, é cadastro novo
            if (alunoEditando == null){
                Aluno novoAluno = new Aluno(nome, curso, idade);

                db.alunoDAO().inserir(novoAluno);

                Toast.makeText(this, "Aluno cadastrando com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                //caso contrário, é edição
                alunoEditando.setNome(nome);
                alunoEditando.setCurso(curso);
                alunoEditando.setIdade(idade);

                db.alunoDAO().atualizar(alunoEditando);

                Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            //fecha a tela e volta para a lista
            finish();
        });

    }
}