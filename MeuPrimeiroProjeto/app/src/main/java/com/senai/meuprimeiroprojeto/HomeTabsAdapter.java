package com.senai.meuprimeiroprojeto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeTabsAdapter extends FragmentStateAdapter {
    public HomeTabsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 : return new  HomeInicioTabFragment();
            case 1 : return new HomeFavoritosTabFragment();
            case 2 : return new HomeStatsTabFragment();
        }

        return new HomeInicioTabFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
