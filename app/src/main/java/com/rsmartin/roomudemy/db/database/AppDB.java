package com.rsmartin.roomudemy.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rsmartin.roomudemy.constants.Constants;
import com.rsmartin.roomudemy.db.dao.CourseDAO;
import com.rsmartin.roomudemy.db.dao.LanguageDAO;
import com.rsmartin.roomudemy.db.dao.ProfessorDAO;
import com.rsmartin.roomudemy.db.dao.ProfessorLanguageDAO;
import com.rsmartin.roomudemy.db.entity.Course;
import com.rsmartin.roomudemy.db.entity.Language;
import com.rsmartin.roomudemy.db.entity.Professor;
import com.rsmartin.roomudemy.db.entity.ProfessorLanguage;

/**
 * Clase abstracta donde le pasamos las entidades
 * La hacemos en forma de singleton
 *
 * Al cambiar la DB ya sea metien tablas o algo hay que subir la version
 * Al subir la version hay que hacer una migracion (o borrar la BD que ya tienes)
 */

@Database(entities = {Professor.class, Course.class, Language.class, ProfessorLanguage.class}, version = 4)
public abstract class AppDB extends RoomDatabase {

    private static AppDB INSTANCE;

    public abstract ProfessorDAO professorDAO();
    public abstract CourseDAO courseDAO();
    public abstract LanguageDAO languageDAO();
    public abstract ProfessorLanguageDAO professorLanguageDAO();

    public static AppDB getAppDB(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDB.class,
                    Constants.NAME_DATABASE)
                    .allowMainThreadQueries() //Permite ejecutar querys en el hilo principal, es peligroso nOOO
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE course (id INTEGER PRIMARY KEY NOT NULL, name TEXT, duration TEXT, professorId INTEGER NOT NULL, foreign key (professorId) references professor(id) ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE language (id INTEGER PRIMARY KEY NOT NULL, name TEXT)");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE profesorlanguage (profesorId INTEGER NOT NULL, languajeId INTEGER NOT NULL, PRIMARY KEY (profesorId, languajeId), foreign key (profesorId) references professor(id), foreign key (languajeId) references language(id))");

        }
    };

}
