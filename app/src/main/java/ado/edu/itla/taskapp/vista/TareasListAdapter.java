package ado.edu.itla.taskapp.vista;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class TareasListAdapter extends BaseAdapter {

    private Context context;
    private  List<Tarea> tareas;
    private UsuarioRepositorioDbImp usuarioDbImp;
    private CategoriaRepositorioDbImp categoriaDbImp;
    private String labelAsignado;

    public TareasListAdapter (Context context, List<Tarea> tareas)
    {
        this.context = context;
        this.tareas = tareas;
        this.labelAsignado=  tareas.size() > 0 ? "Asignado A: " : "";

        usuarioDbImp = new UsuarioRepositorioDbImp(context);
        categoriaDbImp = new CategoriaRepositorioDbImp(context);


    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return tareas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



           LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View view = layoutInflater.inflate(R.layout.tarea_listview_row, parent, false);



       TextView txtDescripcion = view.findViewById(R.id.textViewDescripcion);
       TextView txtUsuarioAsignado = view.findViewById (R.id.textViewUsuarioAsignado);
       TextView txtCategoria  = view.findViewById (R.id.textViewCategoria);
       TextView txtFecha = view.findViewById(R.id.textViewFecha);
       TextView txtEstado = view.findViewById(R.id.textViewEstado);

       Tarea tarea  = tareas.get(position);
       Usuario usuario =  usuarioDbImp.buscar(tarea.getUsuarioAsignado() );
       Categoria categoria =  categoriaDbImp.buscar(tarea.getCategoriaId());

       txtDescripcion.setText(tarea.getDescripcion());
       txtUsuarioAsignado.setText(labelAsignado + usuario.getNombre());
       txtCategoria.setText( categoria.getNombre());
       txtFecha.setText(  new SimpleDateFormat("dd-MM-yyyy").format( tarea.getFecha()));


       switch (tarea.getTareaEstado())
       {

           case PENDIENTE:
               txtEstado.setTextColor(Color.rgb(236, 240, 14)); // amarillo
               break;

           case TERMINADO:
               txtEstado.setTextColor(Color.rgb(226,78,51)); // rojo
               break;


       }


       txtEstado.setText(tarea.getTareaEstado().toString());




        return view;
    }
}
