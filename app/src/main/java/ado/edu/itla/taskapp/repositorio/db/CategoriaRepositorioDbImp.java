package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;

public class CategoriaRepositorioDbImp implements CategoriaRepositorio {

    private ConexionDb conexionDb;


    private static final String CAMPO_ID = "id";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String TABLA_CATEGORIA =  "categoria";


    public CategoriaRepositorioDbImp (Context context)
    {
        conexionDb  = new ConexionDb (context);

    }

    @Override
    public boolean guardar(Categoria categoria) {

     /*   if (categoria != null && categoria.getId() > 0)
             return actualizar(categoria); */

       ContentValues cv = new ContentValues();
       cv.put(CAMPO_NOMBRE, categoria.getNombre());


       SQLiteDatabase db = conexionDb.getWritableDatabase();
       Long id = db.insert(TABLA_CATEGORIA, null, cv);

       db.close();

       if (id.intValue() > 0) {
           categoria .setId( id.intValue() );
           return true;
       }

        return false;
    }

    @Override
    public boolean actualizar(Categoria categoria) {

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, categoria.getNombre());


        SQLiteDatabase db = conexionDb.getWritableDatabase();
        int cantidad =  db.update(TABLA_CATEGORIA, cv, CAMPO_ID+" = ?", new String[] {categoria.getId().toString()});

        db.close();


        return  cantidad > 0;

    }

    @Override
    public Categoria buscar(int id) {

       Categoria categoria = new Categoria();
       SQLiteDatabase db =  conexionDb.getReadableDatabase();

       String[] columnas = {CAMPO_ID, CAMPO_NOMBRE};

       Cursor cr  = db.query(TABLA_CATEGORIA,columnas, CAMPO_ID + " =?", new String[] {String.valueOf(id)},null,null,null);

       if (!cr.moveToFirst())
       {
           return null;

       }


       categoria.setId(cr.getInt(cr.getColumnIndex(CAMPO_ID)));
       categoria.setNombre(cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)));


        return categoria;
    }

    @Override
    public List<Categoria> buscar(String buscar) {


        //TODO: buscar las categoria por  su nombre
       List<Categoria> categorias = new ArrayList<Categoria>();

       SQLiteDatabase db = conexionDb.getReadableDatabase();
       String[] columnas = {CAMPO_ID,CAMPO_NOMBRE};

       Cursor cr = db.query(TABLA_CATEGORIA,columnas,null,null,null,null,null);


       if (!cr.moveToFirst())
       {
           return null;

       }


       while (!cr.isAfterLast()) {
           int id = cr.getInt(cr.getColumnIndex(CAMPO_ID));
           String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));


           categorias.add (new Categoria (id,nombre));

           cr.moveToNext();
       }



       cr.close();
       db.close();

       return categorias;
    }
}
