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
import java.util.ArrayList;
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
    private  List<Tarea> tareasFiltradas;
    private UsuarioRepositorioDbImp usuarioDbImp;
    private CategoriaRepositorioDbImp categoriaDbImp;
    private String labelAsignado;

    public TareasListAdapter (Context context, List<Tarea> tareas)
    {
        this.context = context;
        this.tareas = tareas;
        this.tareasFiltradas = tareas;
        this.labelAsignado=  tareas.size() > 0 ? "Asignado A: " : "";

        usuarioDbImp = new UsuarioRepositorioDbImp(context);
        categoriaDbImp = new CategoriaRepositorioDbImp(context);


    }

    @Override
    public int getCount() {
        return tareasFiltradas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareasFiltradas.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return tareasFiltradas.get(position).getId();
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

       Tarea tarea  = tareasFiltradas.get(position);
       Usuario usuario =  usuarioDbImp.buscar(tarea.getUsuarioAsignado() );
       Categoria categoria =  categoriaDbImp.buscar(tarea.getCategoriaId());

       txtDescripcion.setText(tarea.getDescripcion());
       txtUsuarioAsignado.setText(labelAsignado + usuario.getNombre());
       txtCategoria.setText( categoria.getNombre());
       txtFecha.setText(  new SimpleDateFormat("dd-MM-yyyy").format( tarea.getFecha()));


       switch (tarea.getTareaEstado())
       {

           case PENDIENTE:
               txtEstado.setTextColor(Color.rgb(238,169,95)); // orange
               txtEstado.setText("PENDIENTE");
               break;


           case EN_PROCESO:
               txtEstado.setTextColor(Color.rgb(62,230, 135)); // verde
               txtEstado.setText("EN PROCESO");
               break;

           case TERMINADO:
               txtEstado.setTextColor(Color.rgb(226,78,51)); // rojo
               txtEstado.setText("TERMINADO");
               break;


       }



        return view;
    }

    public  void filtroTareaEstado (Tarea.TareaEstado tareaEstado)
    {

        tareasFiltradas = new ArrayList<Tarea>();

        for ( Tarea tarea : tareas)
        {
            if (tarea.getTareaEstado() == tareaEstado)
            {
                tareasFiltradas.add(tarea);

            }

        }

        notifyDataSetChanged();


    }

    public void filtroTodo ()
    {

        tareasFiltradas = tareas;
        notifyDataSetChanged();

    }




}
