package com.rsmartin.roomudemy.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.roomudemy.db.entity.Language;

import java.util.List;

@Dao
public interface LanguageDAO {
    @Insert
    void insert(Language language);

    @Query("SELECT * FROM language")
    List<Language> findAllLanguages();

    @Update
    void updateLanguageById(Language language);

    @Delete
    void deleteLanguageById(Language language);
}
