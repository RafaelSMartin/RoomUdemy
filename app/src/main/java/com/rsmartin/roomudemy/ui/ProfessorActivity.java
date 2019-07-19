package com.rsmartin.roomudemy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rsmartin.roomudemy.R;
import com.rsmartin.roomudemy.db.database.AppDB;
import com.rsmartin.roomudemy.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Room no permite usarlo en la interfaz de usuario
 * podemos usar AsynTask, Thread, RxJava, LiveData.
 */
public class ProfessorActivity extends AppCompatActivity {

    private EditText etNombre, etEmail;
    private Button btSalvar, btLeerTodo, btLeerPorNombre, btLeerPotId, btActualizarPorId, btBorrarPorId, btBorrarAll;

    private Professor professor;
    private List<Professor> listProfessors;

    private EscribirBaseDatosTask escribirBaseDatosTask;
    private LeerBasesDatosTask leerBasesDatosTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        initViews();
    }

    private void initViews(){
        professor = new Professor();
        listProfessors = new ArrayList<>();

        etNombre = findViewById(R.id.professorActivityEtNombre);
        etEmail = findViewById(R.id.professorActivityEtEmail);

        btSalvar = findViewById(R.id.professorActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor.setName(etNombre.getText().toString());
                professor.setEmail(etEmail.getText().toString());

                escribirBaseDatosTask = new EscribirBaseDatosTask();
                escribirBaseDatosTask.execute(professor);
            }
        });

        btLeerTodo = findViewById(R.id.professorActivityBtLeer);
        btLeerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leerBasesDatosTask = new LeerBasesDatosTask();
                leerBasesDatosTask.execute();
            }
        });

        btLeerPorNombre = findViewById(R.id.professorActivityBtFindByName);
        btLeerPorNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor = AppDB.getAppDB(getApplicationContext()).professorDAO().findProfessorByName(etNombre.getText().toString());
                showProfesorUnit(professor);
            }
        });

        btLeerPotId = findViewById(R.id.professorActivityFindById);
        btLeerPotId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor = AppDB.getAppDB(getApplicationContext()).professorDAO().findProfessorById(1);
                showProfesorUnit(professor);
            }
        });

        btActualizarPorId = findViewById(R.id.professorActivityBtUpdateById);
        btActualizarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(1);
                professor.setName("Manuel");
                professor.setEmail("otro@otro.es");
                AppDB.getAppDB(getApplicationContext()).professorDAO().updateProfessorById(professor);
            }
        });

        btBorrarPorId = findViewById(R.id.professorActivityBtDeleteById);
        btBorrarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(2);

                AppDB.getAppDB(getApplicationContext()).professorDAO().deleteProfessorById(professor);
            }
        });

        btBorrarAll = findViewById(R.id.professorActivityBtDelete);
        btBorrarAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDB.getAppDB(getApplicationContext()).professorDAO().deleteAllProfesor();
            }
        });
    }

    private class EscribirBaseDatosTask extends AsyncTask<Professor, Void, Void>{

        @Override
        protected Void doInBackground(Professor... professors) {
            AppDB.getAppDB(getApplicationContext()).professorDAO().insertProfessor(professors[0]);
            return null;
        }
    }

    private class LeerBasesDatosTask extends AsyncTask<Void, Void, List<Professor>>{

        @Override
        protected List<Professor> doInBackground(Void... voids) {
            listProfessors = AppDB.getAppDB(getApplicationContext()).professorDAO().findAllProfessor();
            return listProfessors;
        }
        @Override
        protected void onPostExecute(List<Professor> professors){
            showProfesor(professors);
        }
    }

    private void showProfesor(List<Professor> professors){
        TextView tv = findViewById(R.id.result);
        String aux = "";

        for(Professor professor : professors){
            Log.d("TAG", "Nombre: "+professor.getName().toString() + " Email: "+professor.getEmail().toString() + "\n");
            aux += professor.toString();
            tv.setText(aux);
        }
    }

    private void showProfesorUnit(Professor professor){
        TextView tv = findViewById(R.id.result);
        String aux = "";

        Log.d("TAG", "Nombre: "+professor.getName().toString() + " Email: "+professor.getEmail().toString() + "\n");
        aux = professor.toString();
        tv.setText(aux);

    }

}
