package com.ccastro.antojatec.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ccastro.antojatec.data.model.Users;

@Dao
public interface UsersDao {

    @Insert
    void registerPatients(Users users);

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password LIMIT 1")
    LiveData<Users> login(String email, String password);

    @Query("SELECT * FROM Users WHERE email = :email LIMIT 1")
    Users findUserByEmail(String email);
}
