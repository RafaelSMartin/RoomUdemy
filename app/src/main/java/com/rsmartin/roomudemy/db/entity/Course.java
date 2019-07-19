package com.rsmartin.roomudemy.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.rsmartin.roomudemy.constants.Constants;

import static androidx.room.ForeignKey.CASCADE;


/**
 * Tabla con relacion uno a muchos, la table professor tiene muchos Course relacionadas con ForeingnKey
 * Un curso solo puede pertenecer a un profesor. Un profesor puede tener 0, 1 o varios cursos
 * Se relaciona el id con el professorId
 *
 *  parentColumns = "id" es el private int id de la clase Professor
 *  que lo relaciona con childColumns = "professorId", por lo que
 *  public int professorId va a ser igual a private int id de Professor
 *
 * onDelete = CASCADE si borrar el professor se borrara tb los Crouses asociados a el
 */


@Entity(tableName = Constants.NAME_TABLE_COURSE,
        foreignKeys = @ForeignKey(entity = Professor.class,
                parentColumns = "id",
                childColumns = "professorId",
                onDelete = CASCADE))
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "duration")
    public String duration;
    @ColumnInfo(name = "professorId")
    public int professorId; //igual a id de Professor

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
