package com.ccastro.antojatec.ui.user;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccastro.antojatec.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserContainerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_container, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // NavController del NavHostFragment del container
        NavHostFragment navHostFragment = (NavHostFragment)
                getChildFragmentManager().findFragmentById(R.id.user_nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            // Conectar BottomNavigationView con NavController
            BottomNavigationView bottomNav = view.findViewById(R.id.user_bottom_nav);
            NavigationUI.setupWithNavController(bottomNav, navController);

            // Manejo del botón back
            requireActivity().getOnBackPressedDispatcher().addCallback(
                    getViewLifecycleOwner(),
                    new OnBackPressedCallback(true) {
                        @Override
                        public void handleOnBackPressed() {
                            if (!navController.popBackStack()) {
                                setEnabled(false);
                                requireActivity().onBackPressed();
                            }
                        }
                    });
        }
    }
}
