package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;

public class CategoriaListaActivity extends AppCompatActivity {


    CategoriaRepositorioDbImp categoriaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_lista);


        categoriaRepositorio  = new  CategoriaRepositorioDbImp(this);


        List<Categoria> categorias = categoriaRepositorio.buscar(null);

        final ListView catListView = findViewById(R.id.categoriaListView);

        catListView.setAdapter( new CategoriaListAdapter(this,categorias));

        catListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Categoria cat =  (Categoria)parent.getItemAtPosition(position);

                Intent regCatIntent =  new Intent (CategoriaListaActivity.this,CategoriaActivity.class);
                regCatIntent.putExtra("categoria",cat);
                startActivity(regCatIntent);

            }
        });


            }

    }


