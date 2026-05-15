package com.senai.meuprimeiroprojeto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PerfilTabsAdapter extends FragmentStateAdapter {

    public PerfilTabsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 : return new PerfilContaTabFragment();
            case 1 : return new PerfilSobreTabFragment();
        }
        return new PerfilContaTabFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
