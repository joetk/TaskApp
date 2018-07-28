package ado.edu.itla.taskapp.vista;

import android.content.Context;
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

    public TareasListAdapter (Context context, List<Tarea> tareas)
    {
        this.context = context;
        this.tareas = tareas;
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
       TextView txtUsuarioCreador = view.findViewById (R.id.textViewUsuarioAsignado);
       TextView txtCategoria  = view.findViewById (R.id.textViewCategoria);
       TextView txtFecha = view.findViewById(R.id.textViewFecha);
       TextView txtEstado = view.findViewById(R.id.textViewEstado);

       Tarea tarea  = tareas.get(position);
       Usuario usuario =  usuarioDbImp.buscar(tarea.getUsuarioCreadorId() );
       Categoria categoria =  categoriaDbImp.buscar(tarea.getCategoriaId());

       txtDescripcion.setText(tarea.getDescripcion());
       txtUsuarioCreador.setText(usuario.getNombre());
       txtCategoria.setText( categoria.getNombre());
       txtFecha.setText(  new SimpleDateFormat("dd-MM-yyyy").format( tarea.getFecha()));
       txtEstado.setText(tarea.getTareaEstado().toString());



        return view;
    }
}
