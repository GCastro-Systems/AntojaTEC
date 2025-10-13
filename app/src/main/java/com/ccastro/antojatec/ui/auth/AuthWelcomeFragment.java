package com.ccastro.antojatec.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ccastro.antojatec.R;

public class AuthWelcomeFragment extends Fragment {

    private Button buttonRegister;
    private Button buttonLogin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar layout
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        // Referenciar botón
        buttonRegister = view.findViewById(R.id.btnWelcomeRegister);
        buttonLogin = view.findViewById(R.id.btnWelcomeLogin);

        // Navegación al fragment de registro
        buttonRegister.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.registerOptionFragment);
        });

        // Navegación al fragment de login
        buttonLogin.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.loginOptionFragment);
        });
        return view;
    }
}
