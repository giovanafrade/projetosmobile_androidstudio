package com.senai.meuprimeiroprojeto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Fragment homeFragment = new HomeFragment();
        Fragment perfilFragment = new PerfilFragment();
        Fragment sobreFragment = new SobreFragment();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//// Maneira não profissional
////        Definindo o Fragment Inicial
//        if (savedInstanceState == null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragmentContainer,homeFragment)
//                    .commit();
//        }

////        bottomNav.setOnItemSelectedListener(menuItem -> {
////
////            Fragment fragmentSelecionado = null;
////
////            if(menuItem.getItemId() == R.id.nav_home){
////                fragmentSelecionado = homeFragment;
////            } else if (menuItem.getItemId() == R.id.nav_perfil){
////                fragmentSelecionado = perfilFragment;
////            } else {
////                fragmentSelecionado = sobreFragment;
////            }
////
////            getSupportFragmentManager()
////                    .beginTransaction()
////                    .replace(R.id.fragmentContainer, fragmentSelecionado)
////                    .commit();
////
////            return true;
////        });


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNav, navController);



    }
}