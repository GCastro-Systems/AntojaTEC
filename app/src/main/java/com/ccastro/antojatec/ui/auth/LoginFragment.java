// ui/login/LoginFragment.java
package com.ccastro.antojatec.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.ccastro.antojatec.R;
import com.ccastro.antojatec.ui.user.UserContainerFragment;
import com.ccastro.antojatec.viewmodel.AuthViewModel;

public class LoginFragment extends Fragment {

    private AuthViewModel authViewModel;
    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        // Referencias UI
        etEmail = root.findViewById(R.id.inputEmail);
        etPassword = root.findViewById(R.id.inputPassword);
        btnLogin = root.findViewById(R.id.btnLogin);

        // ViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Listener botón login
        btnLogin.setOnClickListener(v -> handleLogin());

        return root;
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Ingresa tus credenciales", Toast.LENGTH_SHORT).show();
            return;
        }

        authViewModel.loginUsers(email, password).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String fullName = user.getName() + " " + user.getLastNameFather() + " " + user.getLastNameMother();
                Toast.makeText(getContext(), "Bienvenido " + fullName, Toast.LENGTH_SHORT).show();

                // --- Redirección al flujo de usuario ---
                FragmentManager fm = requireActivity().getSupportFragmentManager();

                // Limpiar todo el back stack de Auth para que no pueda regresar
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // Reemplazar el AuthContainerFragment por UserContainerFragment
                fm.beginTransaction()
                        .replace(R.id.main_container, new UserContainerFragment())
                        .commit();
            } else {
                Toast.makeText(getContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
