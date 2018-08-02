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
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class UsuarioNormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_normal);


        Button crearTareas = findViewById(R.id.buttonTareas);
        ListView listView =  findViewById(R.id.listasTareas);
        TareaRepositorioDbImp TareaDbImp = new TareaRepositorioDbImp(this);

        List<Tarea> listaTareas = TareaDbImp.buscarCreadaPor(LoginInfo.getInstance().usuario);

        if (listaTareas == null)
        {
            listaTareas = new ArrayList<Tarea>();
        }




        TareasListAdapter tareaAdapter = new TareasListAdapter(this, listaTareas );


        listView.setAdapter(tareaAdapter);


        crearTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (UsuarioNormalActivity.this, CrearTareasActivity.class);
                startActivity(intent);

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  Tarea tarea  = (Tarea)parent.getItemAtPosition(position);


                  Intent intent = new Intent(UsuarioNormalActivity.this, UsuarioNormalTareasDetalleActivity.class);

                  intent.putExtra("Tarea", tarea);

                  startActivity(intent);
            }
        });

    }
}
