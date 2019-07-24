package com.rsmartin.roomudemy.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.rsmartin.roomudemy.constants.Constants;

/**
 * Es una entidad que room crea una tabla con lo que pongamos aqui.
 * Cada profesor tiene una primary key para permitirnos identificarlo con un valor autogenerado.
 * Creamos tres colummnas en la tabla, id, name y email.
 *
 * Cada clase con @Entity room nos crea una tabla con tantas columnas como campos tenga la clase.
 */

@Entity(tableName = Constants.NAME_TABLE_PROFESSOR)
public class Professor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n" + "Professor{" + "\n" +
                "\t" + "id=" + id + "," + "\n" +
                "\t" + "name='" + name +  '\'' + "," + "\n" +
                "\t" + "email='" + email + '\'' + "\n" +
                '}';
    }
}
