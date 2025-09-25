package com.ccastro.antojatec.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ccastro.antojatec.data.model.Users;
import com.ccastro.antojatec.data.repository.UsersRepository;
//import com.ccastro.saludintegral.data.repository.DoctorsRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AuthViewModel extends AndroidViewModel {

    private final UsersRepository usersRepository;
    //private final DoctorsRepository doctorsRepository;
    private final Executor executor;

    public AuthViewModel(Application application) {
        super(application);
        usersRepository = new UsersRepository(application);
        //doctorsRepository = new DoctorsRepository(application);
        executor = Executors.newSingleThreadExecutor();
    }


    // 🔹 Login de usuarios.
    public LiveData<Users> usersPatient(String email, String password) {
        return usersRepository.loginUsers(email, password);
    }

    // Registrar usuario (solo si no existe).
    public LiveData<Boolean> registerUser(String name, String lastNameFather, String lastNameMother,
                                          String email, String cellphone, String password) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        executor.execute(() -> {
            // Verificar si el correo ya existe.
            Users existing = usersRepository.findUserByEmail(email);

            if (existing != null) {
                result.postValue(false); // Usuario existente.
            } else {
                Users patient = new Users(name, lastNameFather, lastNameMother, email, cellphone, password);
                usersRepository.registerUsers(patient);
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