// ui/login/LoginFragment.java
package com.ccastro.antojatec.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ccastro.antojatec.R;
import com.ccastro.antojatec.viewmodel.AuthViewModel;

public class LoginFragment extends Fragment {

    // Variables de la clase.
    private AuthViewModel authViewModel;
    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = root.findViewById(R.id.inputEmail);
        etPassword = root.findViewById(R.id.inputPassword);
        btnLogin = root.findViewById(R.id.btnLogin);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            authViewModel.loginUser(email, password).observe(getViewLifecycleOwner(), patient -> {
                if (patient != null) {
                    String fullName = patient.getName() + " " + patient.getLastNameFather() + " " + patient.getLastNameMother();
                    Toast.makeText(getContext(), "Bienvenido " + fullName, Toast.LENGTH_SHORT).show();
                    // Navegar a pantalla de pacientes
                } else {
                    Toast.makeText(getContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            });
        });
        return root;
    }
}
