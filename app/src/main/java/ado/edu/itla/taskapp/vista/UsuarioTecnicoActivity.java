package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;

public class UsuarioTecnicoActivity extends AppCompatActivity {

    private static final int OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tecnico);


        ListView listView = findViewById(R.id.ListViewTareas);

        Button buttonPendiente = findViewById(R.id.buttonPendiente);
        Button buttonProceso = findViewById(R.id.buttonProceso);
        Button buttonTodos = findViewById(R.id.buttonTodos);



        final TareasListAdapter adapter = getAdapter();


        listView.setAdapter(adapter);


        listView.setOnItemClickListener( new ListView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tarea tarea = (Tarea) parent.getItemAtPosition(position);

                Intent intent = new Intent (UsuarioTecnicoActivity.this, UsuarioTecnicoNotasActivity.class);

                intent.putExtra("Tarea", tarea);

                startActivityForResult(intent, OK);

            }
        });

        buttonPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.filtroTareaEstado(Tarea.TareaEstado.PENDIENTE);


            }
        });


        buttonProceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.filtroTareaEstado(Tarea.TareaEstado.EN_PROCESO);
            }
        });

        buttonTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.filtroTodo();

            }
        });




    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {

          ListView listView =  findViewById(R.id.ListViewTareas);



           if (requestCode == OK)
           {
               if (resultCode == RESULT_OK)
               {

                   listView.setAdapter( getAdapter());


               }

           }



    }

    private TareasListAdapter getAdapter ()
    {

        TareaRepositorioDbImp tareaRepo = new TareaRepositorioDbImp(this);
        List<Tarea> tareas;

        tareas = tareaRepo.buscarAsignadaA(LoginInfo.getInstance().usuario);


        if (tareas == null)
        {
            tareas = new ArrayList<Tarea>();

        }


        return  new TareasListAdapter(this, tareas);

    }




}
