package com.ccastro.antojatec.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.ccastro.antojatec.data.local.AppDatabase;
import com.ccastro.antojatec.data.local.UsersDao;
import com.ccastro.antojatec.data.model.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsersRepository {

    private UsersDao userDao;
    private ExecutorService executorService;

    public UsersRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.usersDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Registro del usuario.
    public void registerUsers(Users user) {
        executorService.execute(() -> userDao.registerUsers(user));
    }

    // Inicio de sesión de un usuario.
    public LiveData<Users> loginUsers(String email, String password) {
        return userDao.login(email, password);
    }

    // Buscar usuario por e-mail (esto para el login y validaciones).
    public Users findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
