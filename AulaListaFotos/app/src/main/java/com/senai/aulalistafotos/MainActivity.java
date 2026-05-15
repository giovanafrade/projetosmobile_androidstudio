package com.senai.aulalistafotos;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulalistafotos.adapter.FotoAdapter;
import com.senai.aulalistafotos.model.FotoItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPreview;
    private RecyclerView recyclerFotos;
    private List<FotoItem> listaFotos;
    private FotoAdapter fotoAdapter;

    private Uri uriAtual;

    private ActivityResultLauncher<String> galeriaLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;

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

        Button btnGaleria = findViewById(R.id.btnGaleria);
        Button btnCamera = findViewById(R.id.btnCamera);
        imgPreview = findViewById(R.id.imgPreview);
        recyclerFotos = findViewById(R.id.recyclerFotos);

        listaFotos = new ArrayList<>();

        fotoAdapter = new FotoAdapter(listaFotos, position -> {
            listaFotos.remove(position);
            fotoAdapter.notifyItemRemoved(position);
        });

        recyclerFotos.setLayoutManager(new LinearLayoutManager(this));
        recyclerFotos.setAdapter(fotoAdapter);

        galeriaLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), uri -> {
                    if(uri != null) {
                        imgPreview.setImageURI(uri);

                        listaFotos.add(new FotoItem(uri));
                        fotoAdapter.notifyItemInserted(listaFotos.size() -1);
                    }
                }
        );

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                sucesso -> {
                    if(sucesso){
                        imgPreview.setImageURI(uriAtual);

                        listaFotos.add(new FotoItem(uriAtual));
                        fotoAdapter.notifyItemInserted(listaFotos.size());

                    }
                }
        );

        btnGaleria.setOnClickListener(v -> {
            galeriaLauncher.launch("image/*");
        });

        btnCamera.setOnClickListener(v -> {
            uriAtual = criarUriImagem();
            cameraLauncher.launch(null);
        });
    }

    private Uri criarUriImagem(){

        File pasta = new File(getExternalFilesDir("Pictures"), "fotos");

        if (!pasta.exists()){
            pasta.mkdirs();
        }

        String nomeArquivo = "foto" + System.currentTimeMillis() + ".jpg";
        File arquivo = new File(pasta, nomeArquivo);

        return FileProvider.getUriForFile(this, getPackageName()+".provider", arquivo);

    }
}