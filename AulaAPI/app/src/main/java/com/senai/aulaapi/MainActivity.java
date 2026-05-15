package com.senai.aulaapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulaapi.adapter.UsuarioAdapter;
import com.senai.aulaapi.data.AppDatabase;
import com.senai.aulaapi.model.Usuario;
import com.senai.aulaapi.service.ApiService;
import com.senai.aulaapi.service.RetrofitClient;
import com.senai.aulaapi.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private UsuarioAdapter adapter;
    private List<Usuario> lista = new ArrayList<>();

    private AppDatabase db;
    private ProgressBar progress;

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

        recycler = findViewById(R.id.recyclerUsuarios);
        progress = findViewById(R.id.progress);

        private SharedPreferences prefs;

        db = AppDatabase.getDatabase(this);

        prefs = getSharedPreferences("config", MODE_PRIVATE);

        adapter = new UsuarioAdapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        carregarDoBanco();

        if (NetworkUtil.temInternet(this)){
            if (deveAtualizar()){
                buscarDaApi();
            } else {
                Toast.makeText(this, "Dados recentes (cache)",
                Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Sem Internet - usando dados locais", Toast.LENGTH_SHORT).show();)
        }


    }

    private void buscarDaApi(){

        progress.setVisibility(View.VISIBLE);

        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        Call<List<Usuario>> call = api.listarUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    List<Usuario> dados = response.body();

                    db.usuarioDAO().limparTabela();
                    db.usuarioDAO().inserirTodos(dados);

                    salvarMomentoAtualizacao();

                    carregarDoBanco();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Erro ao carregar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void carregarDoBanco(){
        List<Usuario> dados = db.usuarioDAO().listarTodos();

        lista.clear();
        lista.addAll(dados);
        adapter.notifyDataSetChanged();
    }

    private boolean deveAtualizar(){
        long ultimoUpdate = prefs.getLong("ultimo_uptade", 0);
        long agora = System.currentTimeMillis();
        long intervalo = 60000; //1min
        return (agora - ultimoUpdate) > intervalo;
    }

    private void salvarMomentoAtualizacao(){

        prefs.edit().putLong("ultimo_update", System.currentTimeMillis()).apply();
    }
}