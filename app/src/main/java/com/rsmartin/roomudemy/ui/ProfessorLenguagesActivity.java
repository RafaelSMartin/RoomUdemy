package com.rsmartin.roomudemy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rsmartin.roomudemy.R;
import com.rsmartin.roomudemy.db.database.AppDB;
import com.rsmartin.roomudemy.db.entity.Language;
import com.rsmartin.roomudemy.db.entity.Professor;
import com.rsmartin.roomudemy.db.entity.ProfessorLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Relacion de muchos a nuchos, esta clase relaciona los lenguajes con los profesores
 */

public class ProfessorLenguagesActivity extends AppCompatActivity {

    private EditText etIdProfesor, etIdLenguaje;
    private Button btSalvar, btGetProfesor, btGetLenguaje;

    private ProfessorLanguage professorLanguage;
    private List<Professor> professors;
    private List<Language> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_lenguages);

        initViews();
    }

    private void initViews(){
        professorLanguage = new ProfessorLanguage();
        professors = new ArrayList<>();
        languages = new ArrayList<>();

        etIdProfesor = findViewById(R.id.professorLanguageActivityEtProfesorId);
        etIdLenguaje = findViewById(R.id.professorLanguageActivityEtLenguajeId);
        btSalvar = findViewById(R.id.professorLanguageActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorLanguage.setLanguajeId(Integer.valueOf(etIdLenguaje.getText().toString()));
                professorLanguage.setProfessorId(Integer.valueOf(etIdProfesor.getText().toString()));

                AppDB.getAppDB(getApplicationContext()).professorLanguageDAO().insert(professorLanguage);
            }
        });
        btGetProfesor = findViewById(R.id.professorLanguageActivityBtLeerProfesores);
        btGetProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lenguajeId = Integer.valueOf(etIdLenguaje.getText().toString());
                professors = AppDB.getAppDB(getApplicationContext()).professorLanguageDAO().getProfessorForRepository(lenguajeId);
                for (Professor professor: professors){
                    Log.d("TAG", professor.getName() +"\n");
                }
            }
        });
        btGetLenguaje = findViewById(R.id.professorLanguageActivityBtLeerLenguajes);
        btGetLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int profesorId = Integer.valueOf(etIdProfesor.getText().toString());
                languages = AppDB.getAppDB(getApplicationContext()).professorLanguageDAO().getLanguagesForRepository(profesorId);
                for (Language language: languages){
                    Log.d("TAG", language.getName() +"\n");
                }
            }
        });
    }
}
