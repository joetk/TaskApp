package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;

public class TareaRepositorioDbImp implements TareaRepositorio {

   private ConexionDb conexionDb;

   public final static String  TABLA_TAREA = "tarea";
   public final static String CAMPO_ID = "id";
   public final static String CAMPO_NOMBRE = "nombre";
   public final static String CAMPO_DESCRIPCION = "descripcion";
   public final static String CAMPO_FECHA = "fecha";
   public final static String CAMPO_FECHA_COMPLETADO = "fecha_completado";
   public final static String CAMPO_ESTADO = "estado";
   public final static String CAMPO_USUARIO_CREADOR_ID = "usuario_creador_id";
   public final static String CAMPO_USUARIO_ASIGNADO_ID  = "usuario_asignado_id";
   public final static String  CAMPO_CATEGORIA_ID = "categoria_id";


    public TareaRepositorioDbImp (Context context)
    {

        conexionDb = new ConexionDb(context);

    }

    @Override
    public boolean guardar(Tarea tarea) {

        SQLiteDatabase db = conexionDb.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, tarea.getNombre());
        cv.put (CAMPO_DESCRIPCION,tarea.getDescripcion());
        cv.put (CAMPO_FECHA, tarea.getFecha().getTime());
        cv.put (CAMPO_FECHA_COMPLETADO, tarea.getFechaTerminado().getTime());
        cv.put (CAMPO_ESTADO, tarea.getTareaEstado().toString());
        cv.put (CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreadorId());
        cv.put (CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado());
        cv.put (CAMPO_CATEGORIA_ID,  tarea.getCategoriaId());

        long cantidad = db.insert(TABLA_TAREA, null, cv);

        db.close();

        return cantidad > 0;
    }

    @Override
    public Tarea buscar(int id) {

         SQLiteDatabase db = conexionDb.getReadableDatabase();
         String[] columnas =   new String[] {CAMPO_ID, CAMPO_NOMBRE, CAMPO_DESCRIPCION, CAMPO_FECHA,CAMPO_FECHA_COMPLETADO,
                                             CAMPO_ESTADO,CAMPO_USUARIO_CREADOR_ID, CAMPO_USUARIO_ASIGNADO_ID,CAMPO_CATEGORIA_ID};
         Tarea tarea = new Tarea ();


         Cursor cr = db.query(TABLA_TAREA,columnas,CAMPO_ID +  " = ?", new String [] { String.valueOf(id)},null,null,null );


          if (!cr.moveToFirst())
          {
              return null;
          }


         tarea.setId(cr.getInt(cr.getColumnIndex(CAMPO_ID)));
         tarea.setNombre(cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)));
         tarea.setDescripcion(cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION)));
         tarea.setFecha( new Date( cr.getLong(cr.getColumnIndex(CAMPO_FECHA))));
         tarea.setFechaTerminado(new Date (cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO))));
         tarea.setTareaEstado( Tarea.TareaEstado.valueOf(cr.getString(cr.getColumnIndex(CAMPO_ESTADO))));
         tarea.setUsuarioCreadorId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID)));
         tarea.setUsuarioAsignadoId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID)));
         tarea.setCategoriaId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));



        return tarea;


    }

    @Override
    public List<Tarea> buscarAsignadaA(Usuario usuario) {

        SQLiteDatabase db  =  conexionDb.getReadableDatabase();
        List<Tarea> tareas = new ArrayList<Tarea>();
        String[] columnas = new String[] {CAMPO_ID,CAMPO_NOMBRE,CAMPO_DESCRIPCION,CAMPO_FECHA,CAMPO_FECHA,CAMPO_FECHA_COMPLETADO,
                CAMPO_ESTADO,CAMPO_USUARIO_CREADOR_ID,CAMPO_USUARIO_ASIGNADO_ID,CAMPO_CATEGORIA_ID};


        Cursor cr = db.query( TABLA_TAREA, columnas, CAMPO_USUARIO_ASIGNADO_ID + " = ?", new String[] { String.valueOf(usuario.getId())},null,null,null);

        if (!cr.moveToFirst())
        {
            return null;

        }


        while (!cr.isAfterLast())
        {

            Tarea tarea = new Tarea();

            tarea.setId(cr.getInt(cr.getColumnIndex(CAMPO_ID)));
            tarea.setNombre(cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)));
            tarea.setDescripcion(cr.getString((cr.getColumnIndex(CAMPO_DESCRIPCION))));
            tarea.setFecha( new Date (cr.getLong(cr.getColumnIndex(CAMPO_FECHA))) );
            tarea.setFechaTerminado(new Date (cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO))));
            tarea.setTareaEstado(Tarea.TareaEstado.valueOf(cr.getString(cr.getColumnIndex(CAMPO_ESTADO))));
            tarea.setUsuarioCreadorId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID)));
            tarea.setUsuarioAsignadoId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID)));
            tarea.setCategoriaId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));

            tareas.add(tarea);

            cr.moveToNext();



        }

        db.close();
        cr.close();

        return tareas;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        List<Tarea> tareas = new ArrayList<Tarea>();
        String[] columnas = new String[] {CAMPO_ID,CAMPO_NOMBRE,CAMPO_DESCRIPCION,CAMPO_FECHA,CAMPO_FECHA,CAMPO_FECHA_COMPLETADO,
                                          CAMPO_ESTADO,CAMPO_USUARIO_CREADOR_ID,CAMPO_USUARIO_ASIGNADO_ID,CAMPO_CATEGORIA_ID};

        Cursor cr = db.query (TABLA_TAREA,columnas, CAMPO_USUARIO_CREADOR_ID + " = ?",
                              new String [] { String.valueOf(usuario.getId())},null,null,null);


        if (!cr.moveToFirst())
        {
            return null;
        }


        while (!cr.isAfterLast())
        {
            Tarea tarea = new Tarea();

            tarea.setId(cr.getInt(cr.getColumnIndex(CAMPO_ID)));
            tarea.setNombre(cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)));
            tarea.setDescripcion(cr.getString((cr.getColumnIndex(CAMPO_DESCRIPCION))));
            tarea.setFecha( new Date (cr.getLong(cr.getColumnIndex(CAMPO_FECHA))) );
            tarea.setFechaTerminado(new Date (cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO))));
            tarea.setTareaEstado(Tarea.TareaEstado.valueOf(cr.getString(cr.getColumnIndex(CAMPO_ESTADO))));
            tarea.setUsuarioCreadorId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID)));
            tarea.setUsuarioAsignadoId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID)));
            tarea.setCategoriaId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));

            tareas.add(tarea);

            cr.moveToNext();

        }

        db.close();
        cr.close();

        return tareas;
    }
}
