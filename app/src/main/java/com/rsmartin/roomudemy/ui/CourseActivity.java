package com.rsmartin.roomudemy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rsmartin.roomudemy.R;
import com.rsmartin.roomudemy.db.database.AppDB;
import com.rsmartin.roomudemy.db.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private EditText etIdProfesor, etNombre, etDuraction;
    private Button btSalvar, btLeerCursosProfesor, btActualizarPorId, btBorrarPorId;
    private Course course;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initViews();
    }

    public void initViews(){
        course = new Course();
        courses = new ArrayList<>();
        etIdProfesor = findViewById(R.id.courseActivityIdProfesor);
        etNombre = findViewById(R.id.courseActivityName);
        etDuraction = findViewById(R.id.courseActivityIdDuracion);

        btSalvar = findViewById(R.id.courseActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setDuration(etDuraction.getText().toString());
                course.setName(etNombre.getText().toString());
                course.setProfessorId(Integer.parseInt(etIdProfesor.getText().toString()));

                AppDB.getAppDB(getApplicationContext()).courseDAO().insert(course);
            }
        });

        btLeerCursosProfesor = findViewById(R.id.courseActivityBtLeerCursosPorProfesor);
        btLeerCursosProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDB.getAppDB(getApplicationContext()).courseDAO().findCoursesForProfessor(Integer.parseInt(etIdProfesor.getText().toString()));
                for(Course course : courses) {
                    Log.d("TAG", "id: " + course.getId() + " Nombre: " + course.getName() + " Duration: " + course.getDuration() + " IdProfesor: " + course.getProfessorId());
                }
            }
        });

        btActualizarPorId = findViewById(R.id.courseActivityBtActualizarCursosPorId);
        btActualizarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(1);
                course.setDuration("10");
                course.setName("caca");
                course.setProfessorId(5);

                AppDB.getAppDB(getApplicationContext()).courseDAO().updateCourseById(course);
            }
        });

        btBorrarPorId = findViewById(R.id.courseActivityBtBorrarCursosPorId);
        btBorrarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(1);
                AppDB.getAppDB(getApplicationContext()).courseDAO().deleteCourseById(course);
            }
        });

    }
}
