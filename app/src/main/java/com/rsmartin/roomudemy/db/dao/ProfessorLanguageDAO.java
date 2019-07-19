package com.rsmartin.roomudemy.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rsmartin.roomudemy.db.entity.Language;
import com.rsmartin.roomudemy.db.entity.Professor;
import com.rsmartin.roomudemy.db.entity.ProfessorLanguage;

import java.util.List;

@Dao
public interface ProfessorLanguageDAO {

    @Insert
    void insert(ProfessorLanguage professorLanguage);

    @Query("SELECT * FROM professor INNER JOIN profesorlanguage ON professor.id=profesorlanguage.profesorId WHERE profesorlanguage.languajeId=:lenguajeId")
    List<Professor> getProfessorForRepository(int lenguajeId);

    @Query("SELECT * FROM language INNER JOIN profesorlanguage ON language.id=profesorlanguage.languajeId WHERE profesorlanguage.profesorId=:profesorId")
    List<Language> getLanguagesForRepository(int profesorId);
}
