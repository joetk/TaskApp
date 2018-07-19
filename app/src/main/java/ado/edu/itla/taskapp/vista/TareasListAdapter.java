package ado.edu.itla.taskapp.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;

public class TareasListAdapter extends BaseAdapter {

    private Context context;
    List<Tarea> tareas;

    public TareasListAdapter (Context context, List<Tarea> tareas)
    {
        this.context = context;
        this.tareas = tareas;

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

       if (convertView == null)
       {
           LayoutInflater layoutInflater = LayoutInflater.from(context);
          convertView = layoutInflater.inflate(R.layout.tarea_listview_row, parent);

       }

       TextView txtDescripcion = convertView.findViewById(R.id.textViewDescripcion);
       TextView txtUsuarioCreador = convertView.findViewById (R.id.textViewUsuarioCreador);
       TextView txtCategoria  = convertView.findViewById (R.id.textViewCategoria);

       TextView txtFecha = convertView.findViewById(R.id.textViewFecha);
       TextView txtEstado = convertView.findViewById(R.id.textViewEstado);

       Tarea tarea  = tareas.get(position);

        txtDescripcion.setText(tarea.getDescripcion());
        txtUsuarioCreador.setText(tarea.getUsuarioCreador().getNombre());
       //TODO: terminar de Agregar las informaciones de tareas

        return null;
    }
}
