package com.rsmartin.roomudemy.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.roomudemy.db.entity.Professor;

import java.util.List;

/**
 * DAO = Data Access Object -> para que room lo sepa @Dao
 * Responsable de definir todos los metodos que acceden a la base de datos CRUD.
 *
 */

@Dao
public interface ProfessorDAO {

    @Insert
    void insertProfessor(Professor professor);

    @Query("SELECT * FROM professor")
    List<Professor> findAllProfessor();

    @Query("SELECT * FROM professor where name LIKE :name")
    Professor findProfessorByName(String name);

    @Query("SELECT * FROM professor where id LIKE :id")
    Professor findProfessorById(int id);

    @Update
    void updateProfessorById(Professor professor);

    @Query("DELETE FROM professor")
    void deleteAllProfesor();

    @Delete
    void deleteProfessorById(Professor professor);

}
