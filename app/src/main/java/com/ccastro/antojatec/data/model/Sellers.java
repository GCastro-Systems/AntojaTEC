package com.ccastro.antojatec.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "sellers",
        indices = {@Index(value = {"email"}, unique = true)} // evita emails duplicados
)
public class Sellers {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name; // máximo 50 caracteres

    @NonNull
    @ColumnInfo(name = "last_name_father")
    private String lastNameFather; // máximo 50 caracteres

    @NonNull
    @ColumnInfo(name = "last_name_mother")
    private String lastNameMother; // máximo 50 caracteres

    @NonNull
    @ColumnInfo(name = "email")
    private String email; // máximo 100 caracteres

    @NonNull
    @ColumnInfo(name = "cellphone")
    private String cellphone; // máximo 15 caracteres

    @NonNull
    @ColumnInfo(name = "password")
    private String password; // mínimo 6 caracteres (validar en la app)

    @NonNull
    @ColumnInfo(name = "created_at")
    private String createdAt; // fecha de creación

    // Constructor vacío requerido por Room
    public Sellers() {
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    // Constructor completo solo para la app
    public Sellers(@NonNull String name, @NonNull String lastNameFather,
                 @NonNull String lastNameMother, @NonNull String email,
                 @NonNull String cellphone, @NonNull String password) {

        this.name = name.length() > 50 ? name.substring(0, 50) : name;
        this.lastNameFather = lastNameFather.length() > 50 ? lastNameFather.substring(0, 50) : lastNameFather;
        this.lastNameMother = lastNameMother.length() > 50 ? lastNameMother.substring(0, 50) : lastNameMother;
        this.email = email.length() > 100 ? email.substring(0, 100) : email;
        this.cellphone = cellphone.length() > 15 ? cellphone.substring(0, 15) : cellphone;
        this.password = password; // validar mínimo 6 caracteres en la app
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) {
        this.name = name.length() > 50 ? name.substring(0, 50) : name;
    }

    @NonNull
    public String getLastNameFather() { return lastNameFather; }
    public void setLastNameFather(@NonNull String lastNameFather) {
        this.lastNameFather = lastNameFather.length() > 50 ? lastNameFather.substring(0, 50) : lastNameFather;
    }

    @NonNull
    public String getLastNameMother() { return lastNameMother; }
    public void setLastNameMother(@NonNull String lastNameMother) {
        this.lastNameMother = lastNameMother.length() > 50 ? lastNameMother.substring(0, 50) : lastNameMother;
    }

    @NonNull
    public String getEmail() { return email; }
    public void setEmail(@NonNull String email) {
        this.email = email.length() > 100 ? email.substring(0, 100) : email;
    }

    @NonNull
    public String getPassword() { return password; }
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getCellphone() { return cellphone; }
    public void setCellphone(@NonNull String cellphone) {
        this.cellphone = cellphone.length() > 15 ? cellphone.substring(0, 15) : cellphone;
    }

    @NonNull
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(@NonNull String createdAt) { this.createdAt = createdAt; }
}
