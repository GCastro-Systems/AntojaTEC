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

public class AuthRegisterOptionFragment extends Fragment {

    private Button buttonRegisterOption;
    private Button buttonRegisterWithGoogle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar layout
        View view = inflater.inflate(R.layout.fragment_register_option, container, false);

        // Referenciar botones correctamente
        buttonRegisterOption = view.findViewById(R.id.buttonRegisterOptionEmail); // registrar por email
        //buttonRegisterWithGoogle = view.findViewById(R.id.buttonRegisterOptionGoogle); // si existe botón Google

        // Navegación al fragment de registro por email
        buttonRegisterOption.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.registerFragment)
        );

        // Opcional: registro con Google
        if (buttonRegisterWithGoogle != null) {
            buttonRegisterWithGoogle.setOnClickListener(v -> {
                // TODO: ...
            });
        }

        return view;
    }
}
