package com.ccastro.antojatec.ui.auth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ccastro.antojatec.R;

public class RegisterOptionFragment extends Fragment {

    private Button buttonRegisterOption;
    private Button buttonRegisterWithGoogle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar layout
        View view = inflater.inflate(R.layout.fragment_register_option, container, false);

        // Referenciar botón
        buttonRegisterOption = view.findViewById(R.id.buttonRegisterOption);
        buttonRegisterWithGoogle = view.findViewById(R.id.buttonRegisterOptionEmail);

        // Navegación al fragment de registro
        buttonRegisterOption.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.registerFragment);
        });
        return view;
    }
}