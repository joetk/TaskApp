package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class UsuarioTecnicoNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tecnico_notas);

        final Tarea tarea = (Tarea) getIntent().getSerializableExtra("Tarea");
        CategoriaRepositorioDbImp categoriaRepo = new CategoriaRepositorioDbImp( this);
        UsuarioRepositorioDbImp usuarioRepo = new UsuarioRepositorioDbImp(this);
        final Button buttonListo = findViewById(R.id.buttonListo);



        TextView txtFecha = findViewById(R.id.textViewFechaNota);
        TextView txtCategoria = findViewById(R.id.textViewCategoriaNota);
        TextView txtUsuario = findViewById(R.id.textViewUsuarioNota);
        TextView txtDescripcion = findViewById(R.id.textViewDescripcionNota);




        txtFecha.setText( new SimpleDateFormat("dd-MM-yyyy").format(tarea.getFecha()));
        txtCategoria.setText( categoriaRepo.buscar(tarea.getCategoriaId()).getNombre());
        txtUsuario.setText(usuarioRepo.buscar(tarea.getUsuarioAsignado()).getNombre());
        txtDescripcion.setText(tarea.getDescripcion());

        switch(tarea.getTareaEstado())
        {

            case EN_PROCESO:
                buttonListo.setText("TERMINADO");
                buttonListo.setBackgroundColor(Color.rgb(226,78,51));
                break;

            case TERMINADO:
                buttonListo.setEnabled(false);
                break;


        }


        buttonListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            if (tarea.getTareaEstado() != Tarea.TareaEstado.TERMINADO) {

                Tarea tareaActualizada = new Tarea();

                tareaActualizada.setId(tarea.getId());
                tareaActualizada.setNombre(tarea.getNombre());
                tareaActualizada.setDescripcion(tarea.getDescripcion());
                tareaActualizada.setFecha(tarea.getFecha());
                tareaActualizada.setFechaTerminado(new Date(System.currentTimeMillis()));
                tareaActualizada.setTareaEstado(Tarea.TareaEstado.PENDIENTE == tarea.getTareaEstado()
                        ? Tarea.TareaEstado.EN_PROCESO : Tarea.TareaEstado.TERMINADO);
                tareaActualizada.setCategoriaId(tarea.getCategoriaId());
                tareaActualizada.setUsuarioCreadorId(tarea.getUsuarioCreadorId());
                tareaActualizada.setUsuarioAsignadoId(tarea.getUsuarioAsignado());

                TareaRepositorioDbImp tareaRepo = new TareaRepositorioDbImp(UsuarioTecnicoNotasActivity.this);

                if (tareaRepo.actualizar(tareaActualizada)) {

                    Toast.makeText(UsuarioTecnicoNotasActivity.this, "se a cambiado el estado de la tarea", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UsuarioTecnicoNotasActivity.this, "hubo un problema Cambiando el estado de la tarea", Toast.LENGTH_SHORT).show();

                }


                Intent intent  = new Intent (UsuarioTecnicoNotasActivity.this, UsuarioTecnicoActivity.class );
                startActivity(intent);


            }



            }
        });



    }
}
