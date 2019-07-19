package com.rsmartin.roomudemy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rsmartin.roomudemy.R;

public class MainActivity extends AppCompatActivity {

    private Button btProfessor, btCursos, btLanguages, btProfessorLengage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
    }

    private void initButton(){
        btProfessor = findViewById(R.id.mainActivityProfesor);
        btProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfessorActivity.class));
            }
        });

        btCursos = findViewById(R.id.mainActivityCurso);
        btCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CourseActivity.class));
            }
        });

        btLanguages = findViewById(R.id.mainActivityLenguajes);
        btLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LanguagesActivity.class));
            }
        });
        btProfessorLengage = findViewById(R.id.mainActivityProfesorLenguajes);
        btProfessorLengage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfessorLenguagesActivity.class));
            }
        });

    }
}
