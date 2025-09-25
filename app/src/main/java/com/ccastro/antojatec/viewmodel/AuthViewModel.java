// viewmodel/AuthViewModel.java
package com.ccastro.antojatec.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ccastro.antojatec.data.repository.UsersRepository;
import com.ccastro.antojatec.data.model.Users;
import com.ccastro.antojatec.data.model.Sellers;
//import com.ccastro.antojatec.data.repository.SellersRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AuthViewModel extends AndroidViewModel {

    private final UsersRepository userRepository;
    //private final DoctorsRepository doctorsRepository;
    private final Executor executor;

    public AuthViewModel(Application application) {
        super(application);
        userRepository = new UsersRepository(application);
        //doctorsRepository = new DoctorsRepository(application);
        executor = Executors.newSingleThreadExecutor();
    }

    // 🔹 Login de usuarios.
    public LiveData<Users> loginUser(String email, String password) {
        return userRepository.loginUser(email, password);
    }

    // Registrar paciente solo si no existe
    public LiveData<Boolean> registerUser(String name, String lastNameFather, String lastNameMother,
                                             String email, String cellphone, String password) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        executor.execute(() -> {
            // Verificar si el correo ya existe.
            Users existing = userRepository.findUserByEmail(email);

            if (existing != null) {
                result.postValue(false); // Usuario existente.
            } else {
                Users patient = new Users(name, lastNameFather, lastNameMother, email, cellphone, password);
                userRepository.registerUser(patient);
                result.postValue(true); // Registro exitoso.
            }
        });

        return result;
    }

    // 🔹 Login de vendedores.
    /*public LiveData<Doctors> loginDoctor(String email, String password) {
        MutableLiveData<Doctors> result = new MutableLiveData<>();
        executor.execute(() -> {
            Doctors doctor = doctorsRepository.loginDoctor(email, password);
            result.postValue(doctor);
        });
        return result;
    }*/
}