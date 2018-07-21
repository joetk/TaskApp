package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.UsuarioCategoria;

public interface UsuarioCategoriaRepositorio {

    List<Categoria> buscar (int id);
    boolean guardar (UsuarioCategoria nombre );


}
