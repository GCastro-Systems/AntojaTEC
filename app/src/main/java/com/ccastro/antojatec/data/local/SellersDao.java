package com.ccastro.antojatec.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ccastro.antojatec.data.model.Sellers;

@Dao
public interface SellersDao {

    @Insert
    void registerPatients(Sellers sellers);

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password LIMIT 1")
    LiveData<Sellers> login(String email, String password);

    @Query("SELECT * FROM Users WHERE email = :email LIMIT 1")
    Sellers findUserByEmail(String email);
}
