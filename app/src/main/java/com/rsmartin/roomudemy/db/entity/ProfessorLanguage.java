package com.rsmartin.roomudemy.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.rsmartin.roomudemy.constants.Constants;

/**
 * Relacionamos la tabla profesor con la lenguaje por los ids
 */

@Entity(tableName = Constants.NAME_TABLE_PROFESSOR_LANGUAGE,
        primaryKeys = {"profesorId", "languajeId"},
        foreignKeys = {
            @ForeignKey(entity = Professor.class,
                parentColumns = "id",
                childColumns = "profesorId"), //1* el mismo
            @ForeignKey(entity = Language.class,
                parentColumns = "id",
                childColumns = "languajeId")//2*
        })
public class ProfessorLanguage {
    @ColumnInfo(name = "profesorId")//1* el mismo
    public int professorId;

    @ColumnInfo(name = "languajeId") //2*
    public int languajeId;

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getLanguajeId() {
        return languajeId;
    }

    public void setLanguajeId(int languajeId) {
        this.languajeId = languajeId;
    }
}
