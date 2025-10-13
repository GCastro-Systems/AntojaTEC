// ui/login/AuthRegisterFragment.java
package com.ccastro.antojatec.ui.auth;

import android.annotation.SuppressLint;
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
import androidx.navigation.fragment.NavHostFragment;

import com.ccastro.antojatec.R;
import com.ccastro.antojatec.viewmodel.AuthViewModel;

public class AuthRegisterFragment extends Fragment {

    private AuthViewModel authViewModel;
    private EditText etName, etLastNameMother, etLastNameFather, etEmail, etCellPhone,etPassword;
    private Button btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        // Variables del fragment.
        etName = root.findViewById(R.id.inputName);
        etLastNameFather = root.findViewById(R.id.inputLastNameFather);
        etLastNameMother = root.findViewById(R.id.inputLastNameMother);
        etEmail = root.findViewById(R.id.inputEmail);
        etCellPhone = root.findViewById(R.id.inputCellPhone);
        etPassword = root.findViewById(R.id.inputPassword);
        btnRegister = root.findViewById(R.id.buttonRegister);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        btnRegister.setOnClickListener(v -> {
            // Deshabilitamos el botón mientras se procesa el registro.
            btnRegister.setEnabled(false);

            String name = etName.getText().toString();
            String lastNameFather = etLastNameFather.getText().toString();
            String lastNameMother = etLastNameMother.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String cellphone = etCellPhone.getText().toString();

            // Validación para asegurarse que los campos no estén vacíos.
            if (name.isEmpty() || lastNameFather.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                btnRegister.setEnabled(true);
                return;
            }

            // Registro con validación de usuario existente y no existente.
            authViewModel.registerUser(name, lastNameFather, lastNameMother, email, cellphone, password)
                    .observe(getViewLifecycleOwner(), success -> {
                        if (success) {
                            Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            NavHostFragment.findNavController(this)
                                    .navigate(R.id.loginFragment);
                        } else {
                            Toast.makeText(getContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        }

                        // Habilitamos el botón después del proceso
                        btnRegister.setEnabled(true);
                    });
        });
        return root;
    }
}