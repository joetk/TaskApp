package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;

public class UsuarioRepositorioDbImp implements UsuarioRepositorio {


    private  ConexionDb conexionDb;
    private final String ID = "Id";
    private final String TABLA_USUARIO = "usuario";
    private final String CAMPO_NOMBRE =  "nombre";
    private final String CAMPO_CONTRASENA = "contrasena";
    private final String CAMPO_EMAIL = "email";
    private final String CAMPO_TIPOUSUARIO = "tipoUsuario";


    public UsuarioRepositorioDbImp (Context context)
    {
        conexionDb =  new ConexionDb(context);


    }

    @Override
    public boolean guardar(Usuario usuario) {



        SQLiteDatabase db = conexionDb.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put (CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put (CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_TIPOUSUARIO, usuario.getTipoUsuario().toString());

         Long id = db.insert(TABLA_USUARIO, null, cv );

        db.close();

         if (id > 0)
         {
             usuario.setId(id.intValue());
             return true;

         }

        return false;
    }

    @Override
    public boolean actualizar(Usuario usuario) {

        SQLiteDatabase db = conexionDb.getWritableDatabase();
        ContentValues cv =  new  ContentValues();

        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put (CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put (CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_TIPOUSUARIO, usuario.getTipoUsuario().toString());

        int cantidad = db.update(TABLA_USUARIO, cv,ID +" = ?" , new String [] {usuario.getId().toString()});


        db.close();

        return cantidad > 0;
    }

    @Override
    public Usuario buscar(int id) {

        Usuario usuario =  new Usuario();
        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {ID , CAMPO_NOMBRE, CAMPO_CONTRASENA, CAMPO_EMAIL, CAMPO_TIPOUSUARIO};

        Cursor cr = db.query(TABLA_USUARIO,columnas,ID + " = ?" ,  new String [] {String.valueOf(id)},null ,null,null);

        if (!cr.moveToFirst())
            return null;

        String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
        String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA));
        String email = cr.getString( cr.getColumnIndex(CAMPO_EMAIL));
        Usuario.TipoUsuario tipoUsuario = Usuario.TipoUsuario.valueOf( cr.getString(cr.getColumnIndex(CAMPO_TIPOUSUARIO)) );

        usuario.setId(id);
        usuario.setContrasena(contrasena);
        usuario.setEmail(email);
        usuario.setTipoUsuario(tipoUsuario);

        return usuario;


    }

    @Override
    public Usuario buscar(String Email) {

        Usuario usuario = new Usuario ();
        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {ID , CAMPO_NOMBRE, CAMPO_CONTRASENA, CAMPO_EMAIL, CAMPO_TIPOUSUARIO};

        Cursor cr = db.query(TABLA_USUARIO,columnas, CAMPO_EMAIL + " =?" ,  new String [] {Email},null ,null,null);

        if (!cr.moveToFirst())
            return null;


             int id = cr.getInt(cr.getColumnIndex(ID));

             String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
             String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA));
             String email = cr.getString( cr.getColumnIndex(CAMPO_EMAIL));
             Usuario.TipoUsuario tipoUsuario = Usuario.TipoUsuario.valueOf( cr.getString(cr.getColumnIndex(CAMPO_TIPOUSUARIO)) );


             usuario.setId(id);
             usuario.setContrasena(contrasena);
             usuario.setEmail(email);
             usuario.setTipoUsuario(tipoUsuario);


        cr.close();
        db.close();

        return usuario;
    }
}
