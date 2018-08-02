package ado.edu.itla.taskapp.vista;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class CrearTareasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tareas);


        final CategoriaRepositorioDbImp categoriaDbImp  = new CategoriaRepositorioDbImp(this);
        UsuarioRepositorioDbImp usuarioDbImp = new UsuarioRepositorioDbImp(this);
        final Spinner spinnerCategoria =  findViewById(R.id.spinnerCategoria);
        final Spinner spinnerUsuario = findViewById(R.id.spinnerUsuario);
        Button buttonGuardar = findViewById(R.id.buttonGuardar);


        List<Categoria> categorias =  categoriaDbImp.buscar("null")  ;
        List<Usuario> usuarioTecnicos = usuarioDbImp.buscarTecnicos();

        if (categorias == null)
        {
            categorias = new ArrayList<Categoria>();

        }

        if (usuarioTecnicos == null)
        {
            usuarioTecnicos = new ArrayList<Usuario>();

        }

        ArrayAdapter<Categoria> arrayAdapterCategoria =  new ArrayAdapter<Categoria>(this, R.layout.support_simple_spinner_dropdown_item,categorias);
        ArrayAdapter<Usuario>   arrayAdapterUsuario = new ArrayAdapter<Usuario>(this,R.layout.support_simple_spinner_dropdown_item, usuarioTecnicos);

        spinnerCategoria.setAdapter(arrayAdapterCategoria);
        spinnerUsuario.setAdapter(arrayAdapterUsuario);




        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TareaRepositorioDbImp tareaDbImp = new TareaRepositorioDbImp(CrearTareasActivity.this);
                EditText txtDescripcion = findViewById(R.id.editTextDescripcion);

                Tarea tarea  = new Tarea();

                tarea.setDescripcion(txtDescripcion.getText().toString());
                tarea.setNombre("generico");
                tarea.setFecha( new Date(System.currentTimeMillis()));
                tarea.setFechaTerminado(null);
                tarea.setTareaEstado(Tarea.TareaEstado.PENDIENTE);
                tarea.setUsuarioCreadorId(LoginInfo.getInstance().usuario.getId());
                tarea.setUsuarioAsignadoId(   ((Usuario)spinnerUsuario.getSelectedItem()).getId() );
                tarea.setCategoriaId( ((Categoria)spinnerCategoria.getSelectedItem()).getId());



              if ( tareaDbImp.guardar(tarea) )
              {
                  Toast.makeText(CrearTareasActivity.this, "se ha creado la tarea con exito", Toast.LENGTH_SHORT).show();

              }

              else
                {
                    Toast.makeText(CrearTareasActivity.this, "hubo un problema creando la tarea", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
