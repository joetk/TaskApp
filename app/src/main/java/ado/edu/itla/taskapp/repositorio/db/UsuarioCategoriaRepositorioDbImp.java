package ado.edu.itla.taskapp.repositorio.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.UsuarioCategoria;
import ado.edu.itla.taskapp.repositorio.UsuarioCategoriaRepositorio;

public class UsuarioCategoriaRepositorioDbImp implements UsuarioCategoriaRepositorio {

   private Context context;
   private ConexionDb conexionDb ;
   private final  static String  TABLA_USUARIO_CATEGORIA = "usuario_categoria";
   private final static String  CAMPO_USUARIO_ID = "usuario_id";
   private final static String CAMPO_CATEGORIA_ID = "categoria_id";


     public UsuarioCategoriaRepositorioDbImp (Context context)
     {
         this.context = context;
         conexionDb = new ConexionDb(context);
     }


    @Override
    public List<Categoria> buscar(int id) {

       List<Categoria> categorias = new ArrayList<Categoria>();
       String[] columnas = new String []  {CAMPO_USUARIO_ID, CAMPO_CATEGORIA_ID};

       SQLiteDatabase db = conexionDb.getReadableDatabase();

       Cursor cr = db.query(TABLA_USUARIO_CATEGORIA,columnas, CAMPO_USUARIO_ID + " = ?", new String[] {String.valueOf(id)},null,null,null);

        if (!cr.moveToFirst())
        {
            return null;

        }


        while (!cr.moveToLast())
        {
            Categoria categoria;
            CategoriaRepositorioDbImp categoriaDb =  new CategoriaRepositorioDbImp(context);

            int categoriaId = cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

            categoria =  categoriaDb.buscar(categoriaId);

            categorias.add(categoria);


        }


        return categorias;
    }

    @Override
    public boolean guardar(UsuarioCategoria nombre) {
        return false;
    }
}
