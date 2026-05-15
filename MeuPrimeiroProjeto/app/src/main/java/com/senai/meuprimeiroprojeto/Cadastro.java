package com.senai.meuprimeiroprojeto;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro extends AppCompatActivity {

    TextInputLayout il_cadNome,  il_cadCpf, il_cadEmail;
    
    TextInputEditText et_cadNome, et_cadCpf, et_Email;
    
    Button btnConfirmarCadastrar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(toolbar, (v, insets) -> {
            int statusBarHeight = insets.getInsets(
                    WindowInsetsCompat.Type.statusBars()).top;

            v.setPadding(0, statusBarHeight, 0, 0);
            return insets;
        });
        
        il_cadNome = findViewById(R.id.il_cadNome);
        il_cadCpf = findViewById(R.id.il_cadCpf);
        il_cadEmail = findViewById(R.id.il_cadEmail);
        
        et_cadNome = findViewById(R.id.et_cadNome);
        et_cadCpf = findViewById(R.id.et_cadCpf);
        et_Email = findViewById(R.id.et_Email);
        
        btnConfirmarCadastrar = findViewById(R.id.btnConfirmarCadastrar);
        
        btnConfirmarCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = et_cadNome.getText().toString().trim();
                String email = et_Email.getText().toString().trim();
                
                il_cadNome.setError(null);
                
                if (nome.isEmpty()){
                    il_cadNome.setError("Informe o nome");
                    return;
                }
                
                if (email.contains("@")){
                    il_cadEmail.setError("E-mail Inválido!");
                    return;
                }

                Toast.makeText(Cadastro.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_home){
            Toast.makeText(this, "Configurando", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(item.getItemId() == R.id.menu_logout){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}