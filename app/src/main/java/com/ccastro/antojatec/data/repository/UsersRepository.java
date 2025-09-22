package com.ccastro.antojatec.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.ccastro.antojatec.data.local.AppDatabase;
import com.ccastro.antojatec.data.local.UsersDao;
import com.ccastro.antojatec.data.model.Users;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsersRepository {

    private UsersDao UsersDao;
    private ExecutorService executorService;

    public UsersRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        UsersDao = db.UsersDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Registro de paciente
    public void registerPatient(Users user) {
        executorService.execute(() -> UsersDao.registerPatients(user));
    }

    // Inicio de sesión de un paciente
    public LiveData<Users> loginPatients(String email, String password) {
        return UsersDao.login(email, password);
    }

    // Buscar paciente por e-mail (esto para el login y validaciones).
    public Users findUserByEmail(String email) {
        return UsersDao.findUserByEmail(email);
    }
}
