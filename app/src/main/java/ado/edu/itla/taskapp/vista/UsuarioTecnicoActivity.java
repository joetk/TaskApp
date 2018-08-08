package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;

public class UsuarioTecnicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tecnico);


        ListView listView = findViewById(R.id.ListViewTareas);
        List<Tarea> tareas;
        TareaRepositorioDbImp tareaRepo = new TareaRepositorioDbImp(this);

        tareas = tareaRepo.buscarAsignadaA(LoginInfo.getInstance().usuario);


        if (tareas == null)
        {
            tareas = new ArrayList<Tarea>();

        }

        TareasListAdapter adapter = new TareasListAdapter(this, tareas);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener( new ListView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tarea tarea = (Tarea) parent.getItemAtPosition(position);

                Intent intent = new Intent (UsuarioTecnicoActivity.this, UsuarioTecnicoNotasActivity.class);

                intent.putExtra("Tarea", tarea);

                startActivity(intent);

            }
        });


    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {



    }


}
