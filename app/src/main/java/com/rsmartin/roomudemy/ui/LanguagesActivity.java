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

import java.util.ArrayList;
import java.util.List;

public class LanguagesActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btSalvar, btLeer, btActualizar, btBorrar;

    private Language language;
    private List<Language> languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        initViews();
    }

    private void initViews(){
        language = new Language();
        languageList = new ArrayList<>();

        etNombre = findViewById(R.id.languageActivityName);
        btSalvar = findViewById(R.id.languageActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setName(etNombre.getText().toString());
                AppDB.getAppDB(getApplicationContext()).languageDAO().insert(language);
            }
        });
        btLeer = findViewById(R.id.languageActivityBtLeerAllLengauges);
        btLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageList = AppDB.getAppDB(getApplicationContext()).languageDAO().findAllLanguages();
                for(Language aux : languageList){
                    Log.d("TAG", "id: " + aux.getId() + " Name: " + aux.getName() +"\n");
                }
            }
        });
        btActualizar = findViewById(R.id.languageActivityBtActualizarCursosPorId);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setId(1);
                language.setName("Java 8");
                AppDB.getAppDB(getApplicationContext()).languageDAO().updateLanguageById(language);
            }
        });
        btBorrar = findViewById(R.id.languageActivityBtBorrarCursosPorId);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setId(Integer.valueOf(etNombre.getText().toString()));
                AppDB.getAppDB(getApplicationContext()).languageDAO().deleteLanguageById(language);
            }
        });
    }
}
