package ado.edu.itla.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class UsuarioNormalTareasDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_normal_tareas_detalle);

        Tarea tarea = (Tarea)getIntent().getSerializableExtra("Tarea");
        CategoriaRepositorioDbImp categoriaRepo = new CategoriaRepositorioDbImp(this);
        UsuarioRepositorioDbImp usuarioRepo = new UsuarioRepositorioDbImp(this);

        TextView txtFecha = findViewById(R.id.textViewFecha);
        TextView txtCategoria = findViewById(R.id.textViewCategoria);
        TextView txtUsuarioAsignado = findViewById(R.id.textViewUsuarioAsignado);
        TextView txtEstado = findViewById(R.id.textViewEstado);
        TextView txtDescripcion = findViewById(R.id.textViewDescripcion);



        txtFecha.setText( new SimpleDateFormat("dd-MM-yyyy").format(tarea.getFecha()));
        txtCategoria.setText(categoriaRepo.buscar(tarea.getCategoriaId()).getNombre());
        txtUsuarioAsignado.setText(usuarioRepo.buscar(tarea.getUsuarioAsignado()).getNombre());
        txtEstado.setText(tarea.getTareaEstado().toString());
        txtDescripcion.setText(tarea.getDescripcion());

    }
}
