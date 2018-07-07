package ado.edu.itla.taskapp.vista;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;

public class CategoriaListAdapter extends BaseAdapter {

    private Context context;
    private List<Categoria> categorias;

    public CategoriaListAdapter(Context context, List<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position) {

        return categorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


         if (convertView == null) {
             LayoutInflater inflater = LayoutInflater.from(context);
             convertView =  inflater.inflate(R.layout.categoria_listview_row,parent);
         }


         TextView lbCategoriaId = convertView.findViewById(R.id.lbId);
         TextView lbCategoriaNombre = convertView.findViewById (R.id.lbNombreCategoria);



         Categoria cat =  categorias.get(position);

          lbCategoriaId.setText(cat.getId().toString());
          lbCategoriaNombre.setText (cat.getNombre());


        return null;
    }
}
