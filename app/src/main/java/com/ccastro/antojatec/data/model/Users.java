package com.ccastro.antojatec.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(
        tableName = "users",
        indices = {@Index(value = {"email"}, unique = true)} // evita emails duplicados
)
public class Users {

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
    public Users() {
        this.createdAt = LocalDateTime.now().toString();
    }

    // Constructor completo solo para uso de la app
    @Ignore // <- Esto indica a Room que ignore este constructor
    public Users(@NonNull String name, @NonNull String lastNameFather,
                 @NonNull String lastNameMother, @NonNull String email,
                 @NonNull String cellphone, @NonNull String password) {

        this.name = truncate(name, 50);
        this.lastNameFather = truncate(lastNameFather, 50);
        this.lastNameMother = truncate(lastNameMother, 50);
        this.email = truncate(email, 100);
        this.cellphone = truncate(cellphone, 15);
        this.password = password; // validar mínimo 6 caracteres en la app
        this.createdAt = LocalDateTime.now().toString();
    }

    // Método auxiliar para truncar cadenas
    private String truncate(String value, int maxLength) {
        if (value == null) return "";
        return value.length() > maxLength ? value.substring(0, maxLength) : value;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = truncate(name, 50); }

    @NonNull
    public String getLastNameFather() { return lastNameFather; }
    public void setLastNameFather(@NonNull String lastNameFather) { this.lastNameFather = truncate(lastNameFather, 50); }

    @NonNull
    public String getLastNameMother() { return lastNameMother; }
    public void setLastNameMother(@NonNull String lastNameMother) { this.lastNameMother = truncate(lastNameMother, 50); }

    @NonNull
    public String getEmail() { return email; }
    public void setEmail(@NonNull String email) { this.email = truncate(email, 100); }

    @NonNull
    public String getPassword() { return password; }
    public void setPassword(@NonNull String password) { this.password = password; }

    @NonNull
    public String getCellphone() { return cellphone; }
    public void setCellphone(@NonNull String cellphone) { this.cellphone = truncate(cellphone, 15); }

    @NonNull
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(@NonNull String createdAt) { this.createdAt = createdAt; }
}
