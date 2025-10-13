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

public class AuthLoginOptionFragment extends Fragment {

    private Button buttonLoginWithEmail;
    private Button buttonLoginWithGoogle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar layout
        View view = inflater.inflate(R.layout.fragment_login_option, container, false);

        // Referenciar botón
        buttonLoginWithEmail = view.findViewById(R.id.buttonLoginOptionEmail);
        buttonLoginWithGoogle = view.findViewById(R.id.buttonLoginWithGoogle);

        // Navegación al fragment de registro
        buttonLoginWithEmail.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.loginFragment);
        });
        return view;
    }
}
